package exercise.week3;

public class Main {

    private static Shape[] shapes = new Shape[100];
    private static int shapeCount = 0;

    private static void task1() {
        // create a todo task and print details
        Todo t = new Todo("Read a good book");
        System.out.println(t.getDescription());
        System.out.println(t.isDone());

        // change todo fields and print again
        t.setDone(true);
        System.out.println(t.isDone());

        // create a deadline task and print details
        Deadline d = new Deadline("Read textbook", "Nov 16");
        System.out.println(d.getDescription());
        System.out.println(d.isDone());
        System.out.println(d.getBy());

        // change deadline details and print again
        d.setDone(true);
        d.setBy("Postponed to Nov 18th");
        System.out.println(d.isDone());
        System.out.println(d.getBy());
    }

    private static void task2() {
        // create a todo task and print it
        Todo t = new Todo("Read a good book");
        System.out.println(t);

        // change todo fields and print again
        t.setDone(true);
        System.out.println(t);

        // create a deadline task and print it
        Deadline d = new Deadline("Read textbook", "Nov 16");
        System.out.println(d);

        // change deadline details and print again
        d.setDone(true);
        d.setBy("Postponed to Nov 18th");
        System.out.println(d);
    }

    private static void addShape(Shape shape) {
        shapes[shapeCount] = shape;
        shapeCount++;
    }

    private static void printAreas() {
        for (int i = 0; i < shapeCount; i++) {
            System.out.println(shapes[i].area());
        }
    }

    private static void task3() {
        addShape(new Circle(5));
        addShape(new Rectangle(3, 4));
        addShape(new Circle(10));
        printAreas();
        addShape(new Rectangle(4, 4));
        printAreas();
    }

    public static void main(String[] args) {
        // task1();
        // task2();
        task3();
    }
}
