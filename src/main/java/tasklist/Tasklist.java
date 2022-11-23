package tasklist;

import ui.Ui;

import java.util.*;

public class Tasklist {

    public static ArrayList<Task> tasklist;
    public static int listCount;

    public Tasklist(){
        tasklist = new ArrayList<Task>();
        listCount = 0;
    }
    public Tasklist(Tasklist load){
        this.tasklist = load.tasklist;
    }

    public static int getListcount(){
        return listCount;
    }
    public static void setListCount(int count) { listCount = count; }

    public static ArrayList<Task> getTasklist () { return tasklist; }

    public void printTasklist(){
        try {
            if(listCount == 0){
                throw new NoSuchElementException();
            }

            System.out.println(Ui.REPLY_LIST);

            for (int i = 0; i < listCount; i++){
                System.out.println("No " + (i + 1) + ".[" + tasklist.get(i).getTypeTask() + "]" +
                        "[" + tasklist.get(i).getDone() + "] " +
                        tasklist.get(i).getDescription() + " " +
                        tasklist.get(i).getString());
            }
        } catch (NoSuchElementException e) {
            System.out.println(Ui.ERROR_EMPTYTASKLIST);
        }
    }

    public void printTask(int i){
        System.out.println("  [" + tasklist.get(i).getTypeTask() + "]" +
                "[" + tasklist.get(i).getDone() + "] " +
                tasklist.get(i).getDescription() + " " +
                tasklist.get(i).getString());
    }

    public void findTasklist(String find){
        boolean printed = false;

        try {
            for (int i = 0; i < listCount; i++){
                if(tasklist.get(i).matchFind(find)){
                    if(!printed) {
                        System.out.println(Ui.REPLY_FIND);
                        printed = true;
                    }

                    System.out.println("No " + (i + 1) + ".[" + tasklist.get(i).getTypeTask() + "]" +
                            "[" + tasklist.get(i).getDone() + "] " +
                            tasklist.get(i).getDescription() + " " +
                            tasklist.get(i).getString());
                }
            }
            if (!printed) {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            System.out.println(Ui.ERROR_NOMATCHES + "\n" + Ui.UI_DIVIDER);
        }
    }

    public void dueTasklist(String due){
        boolean printed = false;

        try {
            for (int i = 0; i < listCount; i++){
                if(tasklist.get(i).matchDue(due)){
                    if(!printed) {
                        System.out.println(Ui.REPLY_DUE);
                        printed = true;
                    }
                    System.out.println("No " + (i + 1) + ".[" + tasklist.get(i).getTypeTask() + "]" +
                            "[" + tasklist.get(i).getDone() + "] " +
                            tasklist.get(i).getDescription() + " " +
                            tasklist.get(i).getString());
                }
            }
            if (!printed) {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            System.out.println(Ui.ERROR_NOMATCHES);
        }
    }

    public void printAdded(int index){
        System.out.println(Ui.REPLY_ADD_TASK);
        printTask(index);
        System.out.println("Now you have " + (index+1) + " tasks in the list");
    }

    public void printDeleted(int index){
        System.out.println(Ui.REPLY_REMOVE_TASK);
        printTask(index-1);
        System.out.println("Now you have " + (index) + " tasks in the list");
    }

    public static void printLoaded(int index){
        System.out.println("No " + (index + 1) + ".[" + tasklist.get(index).getTypeTask() + "]" +
                "[" + tasklist.get(index).getDone() + "] " +
                tasklist.get(index).getDescription() + " " +
                tasklist.get(index).getString());
    }

    public void addTodo(String description){
        tasklist.add(new Todo(description));
        printAdded(listCount);
        listCount++;
    }

    public static void addTodo(Tasklist load, String description){
        tasklist.add(new Todo(description));
        listCount++;
    }

    public void addEvent(String description, String at){
        tasklist.add(new Event(description, at));
        printAdded(listCount);
        listCount++;
    }

    public static void addEvent(Tasklist load, String description, String at){
        tasklist.add(new Event(description, at));
        listCount++;
    }

    public void addDeadline(String description, String by){
        tasklist.add(new Deadline(description, by));
        printAdded(listCount);
        listCount++;
    }

    public static void addDeadline(Tasklist load, String description, String by){
        tasklist.add(new Deadline(description, by));
        listCount++;
    }

    public void removeTask(int index){
        printDeleted(listCount-1);
        tasklist.remove(index-1);
        listCount--;
    }

    public void markDone(int index){
        tasklist.get(index).markDone();
        System.out.println(Ui.REPLY_MARK_DONE);
        printTask(index);
    }

    public static void markDone(Tasklist load, int index){
        tasklist.get(index).markDone();
    }

    public void unmarkDone(int index){
        tasklist.get(index).unmarkDone();
        System.out.println(Ui.REPLY_UNMARK_DONE);
        printTask(index);
    }

    public void postponeTask(int index, String to){
          System.out.println(Ui.REPLY_POSTPONE);
          tasklist.get(index).setDate(to);
          printTask(index);

    }
}
