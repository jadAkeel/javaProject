package src.proxy;

public class RealService implements Service {
    public RealService() {
        System.out.println("RealService created");
    }

    public String fetchData(String request) {
        System.out.println("Fetching data for: " + request);
        return "Data for " + request;
    }
}