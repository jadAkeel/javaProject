package mvcs;

import static org.assertj.db.api.Assertions.assertThat;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;

import org.assertj.db.type.AssertDbConnection;
import org.assertj.db.type.AssertDbConnectionFactory;

import org.assertj.db.type.Table;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mvcs.services.*;
import mvcs.models.Book;
import mvcs.controllers.BookController;
import org.assertj.db.type.Request;
import org.sqlite.SQLiteDataSource;

public class AppIntegrationTest {

    private static final String DB_PATH = "database/book.db";
    private static final String JDBC_URL = "jdbc:sqlite:" + DB_PATH;

    private SQLiteDataSource datasource;
    private BookService service;
    private BookController controller;

    // AssertJ-DB 3 connection (reused inside tests to snapshot tables/queries)
    private AssertDbConnection conn;

    @BeforeClass
    public static void ensureDbDir() {
        new File("database").mkdirs();
        // Prevent any UI requirements in tests
        System.setProperty("java.awt.headless", "true");
    }

    @Before
    public void resetSchema() throws Exception {

        datasource = new SQLiteDataSource();
        datasource.setUrl(JDBC_URL);        

        try (Connection con = DriverManager.getConnection(JDBC_URL); Statement st = con.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS book");
            st.executeUpdate("CREATE TABLE book (code TEXT PRIMARY KEY, title TEXT, dop TEXT)");
            st.executeUpdate("INSERT INTO book(code,title,dop) VALUES('S001','Seed One','2020-01-01')");
            st.executeUpdate("INSERT INTO book(code,title,dop) VALUES('S002','Seed Two','2021-02-02')");
        }
        service = new BookService();
        controller = new BookController();

        // Build AssertJ-DB connection (3.x API)
        conn = AssertDbConnectionFactory.of(datasource).create();
    }

    // -------------------------------------------------------------------------
    // SERVICE on real DB
    // -------------------------------------------------------------------------
    @Test
    public void service_findAll_readsSeedRows() throws Exception {
        List<Book> list = service.findAll();
        Assertions.assertThat(list)
                .hasSize(2)
                .extracting(Book::getCode, Book::getTitle)
                .containsExactly(
                        Assertions.tuple("S001", "Seed One"),
                        Assertions.tuple("S002", "Seed Two")
                );

        // AssertJ-DB snapshot of table state
        Table table = conn.table("book").build();
        assertThat(table).hasNumberOfRows(2);
        assertThat(table).column("code")
                .value(0).isEqualTo("S001")
                .value(1).isEqualTo("S002");
    }

    @Test
    public void service_create_addsRow_and_assertjdb_seesIt() throws Exception {
        service.create(new Book("N003", "New Three", LocalDate.parse("2022-03-03")));

        // Table snapshot: expect 3 rows (two seeds + new)
        Table table = conn.table("book").build();
        assertThat(table).hasNumberOfRows(3);

        // Request snapshot: check new row by query (safer than relying on table order)
        Request req = conn.request("SELECT code, title, dop FROM book WHERE code='N003'").build();
        assertThat(req).hasNumberOfRows(1);
        assertThat(req).row(0)
                .value("code").isEqualTo("N003")
                .value("title").isEqualTo("New Three")
                .value("dop").isEqualTo("2022-03-03");
    }

    // -------------------------------------------------------------------------
    // CONTROLLER + CALLBACK + AssertJ-DB
    // -------------------------------------------------------------------------
    @Test
    public void controller_findAll_returnsList_viaCallback() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        final List<Book>[] out = new List[1];

        controller.findAll(new BookController.Callback() {
            @Override
            public void onSuccess(String msg) {
                /* optional */ }

            @Override
            public void onError(String msg) {
                Assertions.fail("Unexpected error: " + msg);
            }

            @Override
            public void onFindAll(List<Book> list) {
                out[0] = list;
                latch.countDown();
            }

            @Override
            public void onFindByCode(Book book) {
                /* not used */ }
        });

        Assertions.assertThat(latch.await(3, TimeUnit.SECONDS)).isTrue();
        Assertions.assertThat(out[0]).isNotNull().hasSize(2);

        // Verify DB snapshot matches
        Table table = conn.table("book").build();
        assertThat(table).hasNumberOfRows(2);
    }

    @Test
    public void controller_create_addsRow_callback_success_and_db_hasRow() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        final String[] statusMsg = new String[1];

        Book b = new Book("C010", "Controller Add", LocalDate.parse("2024-10-10"));

        controller.create(b, new BookController.Callback() {
            @Override
            public void onSuccess(String msg) {
                statusMsg[0] = msg;
                latch.countDown();
            }

            @Override
            public void onError(String msg) {
                Assertions.fail("Unexpected error: " + msg);
            }

            @Override
            public void onFindAll(List<Book> list) {
                /* unused */ }

            @Override
            public void onFindByCode(Book book) {
                /* unused */ }
        });

        Assertions.assertThat(latch.await(3, TimeUnit.SECONDS)).isTrue();
        Assertions.assertThat(statusMsg[0]).contains("saved successfully");

        // AssertJ-DB: table now has 3 rows; query exact row values
        Table table = conn.table("book").build();
        assertThat(table).hasNumberOfRows(3);

        Request r = conn.request("SELECT code,title,dop FROM book WHERE code='C010'").build();
        assertThat(r).hasNumberOfRows(1);
        assertThat(r).row(0)
                .value("code").isEqualTo("C010")
                .value("title").isEqualTo("Controller Add")
                .value("dop").isEqualTo("2024-10-10");
    }

    @Test
    public void controller_update_changesRow_and_db_reflectsUpdate() throws Exception {
        // Seed a row to be updated
        try (Connection con = DriverManager.getConnection(JDBC_URL); Statement st = con.createStatement()) {
            st.executeUpdate("INSERT INTO book(code,title,dop) VALUES('U200','Before','2020-12-12')");
        }

        CountDownLatch latch = new CountDownLatch(1);
        final String[] statusMsg = new String[1];

        Book updated = new Book("U200", "After", LocalDate.parse("2025-01-01"));

        controller.update(updated, new BookController.Callback() {
            @Override
            public void onSuccess(String msg) {
                statusMsg[0] = msg;
                latch.countDown();
            }

            @Override
            public void onError(String msg) {
                Assertions.fail("Unexpected error: " + msg);
            }

            @Override
            public void onFindAll(List<Book> list) {
                /* unused */ }

            @Override
            public void onFindByCode(Book book) {
                /* unused */ }
        });

        Assertions.assertThat(latch.await(3, TimeUnit.SECONDS)).isTrue();
        Assertions.assertThat(statusMsg[0]).contains("saved successfully");

        // AssertJ-DB: verify updated values via a Request
        Request req = conn.request("SELECT code,title,dop FROM book WHERE code='U200'").build();
        assertThat(req).hasNumberOfRows(1);
        assertThat(req).row(0)
                .value("code").isEqualTo("U200")
                .value("title").isEqualTo("After")
                .value("dop").isEqualTo("2025-01-01");
    }
}
