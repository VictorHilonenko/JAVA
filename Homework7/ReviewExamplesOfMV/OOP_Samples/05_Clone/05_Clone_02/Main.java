package ua.kpi.tef;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Point p1 = new Point(3,4);
        p1.setAllParameters(4,5);
        Point p2 = p1.clone();

        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        System.out.println(p1.hashCode() == p2.hashCode() );
        System.out.println("==============================");

        p1.getHistory().add(new Point(5,6));

        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        System.out.println(p1.hashCode() == p2.hashCode() );

    }
}
