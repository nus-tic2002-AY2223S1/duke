package exercise;

import java.awt.*;

public class Week2 {

    public static void testCircleMaxRedis() {
        Circle c = new Circle();
        System.out.println("max radius used so far : " + Circle.getMaxRadius());
        c = new Circle(0, 0, 10);
        System.out.println("max radius used so far : " + Circle.getMaxRadius());
        c = new Circle(0, 0, -15);
        System.out.println("max radius used so far : " + Circle.getMaxRadius());
        c.setRadius(12);
        System.out.println("max radius used so far : " + Circle.getMaxRadius());
    }

    public static void testCircleRadius() {
        Circle c = new Circle(1,2, 5);

        c.setX(4);
        c.setY(5);
        c.setRadius(6);
        System.out.println("x      : " + c.getX());
        System.out.println("y      : " + c.getY());
        System.out.println("radius : " + c.getRadius());
        System.out.println("area   : " + c.getArea());

        c.setRadius(-5);
        System.out.println("radius : " + c.getRadius());
        c = new Circle(1, 1, -4);
        System.out.println("radius : " + c.getRadius());
    }

    public static void testCircle() {
        Circle c = new Circle();
        System.out.println(c.getArea());
        c = new Circle(1, 2, 5);
        System.out.println(c.getArea());
    }

    public static Point move(Point p, Rectangle r) {
        if (p == null || r == null) {
            return null;
        }
        Point newPoint = new Point(r.x, r.y);
        r.x = p.x;
        r.y = p.y;
        return newPoint;
    }

    public static void testMove() {
        Point p1 = new Point(0, 0);
        Rectangle r1 = new Rectangle(2, 3, 5, 6);
        System.out.println("arguments: " + p1 + ", " + r1);

        Point p2 = move(p1, r1);
        System.out.println(
                "argument point after method call: " + p1);
        System.out.println(
                "argument rectangle after method call: " + r1);
        System.out.println(
                "returned point: " + p2);

        System.out.println(move(null, null));
    }

    public static void useRectangle() {
        Rectangle r = new Rectangle(0, 0, 4, 6);
        System.out.println(r);

        int area;
        //TODO: add a line below to calculate the area using
        //  width and height properties of r
        //  and assign it to the variable area
        area = (int) (r.getHeight() * r.getWidth());
        System.out.println("Area: " + area);

        //TODO: add a line here to set the size of r to
        //  8x10 (width x height)
        //Recommended: use the setSize(int width, int height)
        //  method of the Rectangle object
        r.setSize(8, 10);
        System.out.println(r);
    }

    public static void createRectangle() {
        Rectangle r = new Rectangle(0, 0, 5, 10);
        // TODO: create a Rectangle object that has the
        // properties x=0, y=0, width=5, height=10
        // and assign it to r
        System.out.println(r);
    }

    public static void main(String[] args) {
        // createRectangle();
        // testMove();
        // testCircle();
        testCircleMaxRedis();
    }
}

class Circle {
    private int x;
    private int y;
    private double radius;
    private static double maxRadius;

    public Circle() {
        this(0, 0, 0);
    }

    public Circle(int x, int y, double radius) {
        setX(x);
        setY(y);
        setRadius(radius);
    }

    public int getArea() {
        double area = Math.PI * Math.pow(radius, 2);
        return (int) area;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = Math.max(radius, 0);
        if (maxRadius < this.radius){
            maxRadius = radius;
        }
    }

    public static double getMaxRadius() {
        return maxRadius;
    }
}
