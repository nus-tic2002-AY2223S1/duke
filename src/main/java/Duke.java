import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;


public class Duke {

    //public static String taskname(int num) {

    //}
    static void validate(String s, String tas) throws DukeException {
        if (s.equals(tas)) {

            // throw an object of user defined exception
            throw new DukeException(); //tas+ " cannot be empty. Please try again");
        }
        //else {
          //  System.out.println("Correct input!");
       // }
    }

    static void validateDate(int ind) throws DukeException2 {
        if (ind==-1) {throw new DukeException2();}
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello there! I'm Duke\nWhat's on your mind today?");
        //String[] com= new String[100];
        Task[] t= new Task[10];
        ArrayList<Task>tasks= new ArrayList<>();
        String temp;
        String ts="null";
        int i=1;
        int j=0;
        while(i==1){
            Scanner in = new Scanner(System.in);
            temp = in.nextLine();

            try {                                           //try new



                if (temp.equals("list")) {
                    System.out.println("\nContent of your List\n");

            /*        for (int k = 0; k < j; k++) {
                        //    System.out.println(k+1 + ". ["+t[k].getStatusIcon()+"] " + t[k].getDescription() +"\n");
                        System.out.println(k + 1 + ". " + t[k]);

                    }
                    System.out.println("\nEnd of list\n");*/

                    // below new array list***************************************
                    //System.out.println("\nBELOW IS THE NEW LIST ARRAY\n");
                    int k=0;
                    while(k<j){

                        System.out.println(k + 1 + ". " + tasks.get(k));
                        k++;
                    }
                    System.out.println("\nEnd of list\n");

                }

                else if (temp.equals("bye")) {
                    i = 0;
                }

                else if (temp.substring(0, 3).equals("unm")) {
                    //unmark
                    int num = Integer.parseInt(temp.substring(7));
                //    t[num - 1].markAsUndone();
                //    System.out.println(t[num - 1] + "\n");

                    //below array list****************************************

                    tasks.get(num-1).markAsUndone();
                    System.out.println(tasks.get(num - 1) + "\n");
                }

                else if (temp.substring(0, 3).equals("mar")) {
                    //mark
                    int num = Integer.parseInt(temp.substring(5));
                //    t[num - 1].markAsDone();
                //    System.out.println(t[num - 1] + "\n");

                    //below array list****************************************
                //    System.out.println("\nBELOW IS THE NEW LIST ARRAY\n");
                    tasks.get(num-1).markAsDone();
                    System.out.println(tasks.get(num - 1) + "\n");

                }

                else if (temp.substring(0, 4).equals("todo")) {

                    ts="todo";
                    validate(temp, temp.substring(0, 4)); //Check if todo is empty

                //    t[j] = new Todo(temp.substring(5));
                    j++;

                //    System.out.println(t[j - 1]);


                    //below array list****************************************
                //    System.out.println("\nBELOW IS THE NEW LIST ARRAY\n");
                    tasks.add(new Todo(temp.substring(5)));
                    System.out.println("The task has been successfully added to your list!");
                    System.out.println(tasks.get(j-1));
                    System.out.println("Now you have " + j + " tasks in your list\n");

                }

                else if (temp.substring(0, 5).equals("event")) {

                    ts="event";
                    validate(temp, temp.substring(0, 5));

                    int index = temp.indexOf('/');
                    validateDate(index);


                    //t[j] = new Event(temp.substring(6, index), temp.substring(index + 1));

                    j++;

                //    System.out.println("The task has been successfully added to your list!");
                //    System.out.println(t[j - 1]);
                //    System.out.println("Now you have " + j + " tasks in your list\n");

                    //below array list****************************************
                //    System.out.println("\nBELOW IS THE NEW LIST ARRAY\n");

                    tasks.add(new Event(temp.substring(6, index), temp.substring(index + 1)));
                    System.out.println("The task has been successfully added to your list!");
                    System.out.println(tasks.get(j-1));
                    System.out.println("Now you have " + j + " tasks in your list\n");
                }

                else if (temp.substring(0, 6).equals("delete")) {

                    int num = Integer.parseInt(temp.substring(7));
                    Task g= tasks.get(num-1);
                    tasks.remove(num-1);
                    j--;
                    System.out.println("Noted. The task has been removed");
                    System.out.println(g);
                    System.out.println("Now you have " + j + " tasks in your list");
                    System.out.println("BELOW IS THE NEW LIST after delete\n");
                    int k=0;
                    while(k<j){

                        System.out.println(k + 1 + ". " + tasks.get(k));
                        k++;
                    }
                    System.out.println("\nEnd of list\n");

                }


                else if (temp.substring(0, 8).equals("deadline")) {

                    ts="deadline";
                    validate(temp, temp.substring(0, 8));

                    int index = temp.indexOf('/');
                    validateDate(index);

                //    t[j] = new Deadline(temp.substring(9, index), temp.substring(index + 1));

                    j++;

                //    System.out.println("The task has been successfully added to your list!");
                //    System.out.println(t[j - 1]);
                //    System.out.println("Now you have " + j + " tasks in your list\n");

                    //below array list****************************************
                //    System.out.println("\nBELOW IS THE NEW LIST ARRAY\n");
                    tasks.add(new Deadline(temp.substring(9, index), temp.substring(index + 1)));
                    System.out.println("The task has been successfully added to your list!");
                    System.out.println(tasks.get(j-1));
                    System.out.println("Now you have " + j + " tasks in your list\n");
                }


                else {
                    System.out.println("Type bye to exit or check your input and try again.\n");
                }

            } catch (IndexOutOfBoundsException e){              //Catch
                System.out.println("oopss!! Please check your input and try again or type bye to exit.\n");
            } catch (DukeException e){
                System.out.println("Error : "+ ts + " cannot be empty. Please try again\n");//+ e);
            }catch (DukeException2 e){
                System.out.println("Error : For "+ ts + " Please enter by/at after / and try again\n");//+ e);
            }

        }
        System.out.println("Byeee!! See you Again!!");
    }
}

