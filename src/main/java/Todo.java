import java.util.ArrayList;

public class Todo {
    public ArrayList<Item> items;

    public Todo(){
        items = new ArrayList<>(100);
    }

    private void printList(){
        for(int i = 0; i < items.size(); i++){
            printItem(i+1, items.get(i));
        }
    }

    private void printItem(Integer n, Item i){
        String t = i.getType();
        System.out.format("\t%d.[%s][%s] %s %s\n", n, t, i.getIsDone() ? "X":" ", i.getName(), t == "T" ? "" : "(" + (t == "E" ? "at: " : "by: ") + i.getTime() + ")");
    }

    public void addItem(String itemName, String type){
        System.out.println("Got it. I've added this task:");
        Item item = new Item(itemName, type);
        items.add(item);
        Integer n = items.size();
        printItem(n, item);
        System.out.format("Now you have %d tasks in the list.\n", n);
    }

    public void showList(){
        System.out.println("\tHere are the tasks in your list:");
        printList();
    }

    public void updateDoneState(int n, boolean isDone){
        if(n > 0 && n <= items.size()) {
            Item item = items.get(n-1);
            item.setIsDone(isDone);
            if (isDone) {
                System.out.println("\tNice! I've marked this task as done:");
            } else {
                System.out.println("\tOK, I've marked this task as not done yet:");
            }
            printItem(n, item);
        } else {
            System.out.println("\tOops! I could not find this task. Here are the tasks again:");
            printList();
        }
    }
}
