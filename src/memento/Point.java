package memento;

public class Point {

    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public String toString() {
        return String.format("[%d, %d]", x, y);
    }

    public MementoPoint create(){
        return new MementoPoint(x, y);
    }
    
    public void install(MementoPoint mem){
        x = mem.x;
        y = mem.y;
    }
    
    public static class MementoPoint {
        private int x, y;

        private MementoPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
