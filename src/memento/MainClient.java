/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package memento;

/**
 *
 * @author orifi
 */
public class MainClient {

    public static void main(String[] args) {
        Point p = new Point(1, 1);
        Storage st = new Storage();

        p.move(1, 2);
        System.out.println(p);
        st.put(p.create());
        
        p.move(1, 1);
        p.move(2, 1);
        System.out.println(p);
        
        p.install(st.get());
        System.out.println(p);
    }

}
