package memento;

//CareTaker or Memento Holder

import java.util.LinkedList;
import java.util.List;

public class Storage {
    private List<Point.MementoPoint> ls = new LinkedList<>();
    
    public void put(Point.MementoPoint mem){
        ls.add(0,mem);
    }
    
    public Point.MementoPoint get(){
        return ls.remove(0);
    }
    
    public boolean isEmpty(){
        return ls.isEmpty();
    }
}
