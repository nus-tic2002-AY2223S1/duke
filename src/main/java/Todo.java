import java.util.ArrayList;

public class Todo {
    public ArrayList<Item> items;

    public Todo(){
        items = new ArrayList<>(100);
    }

    public void addItem(String itemName){
        items.add(new Item(itemName));
    }

    public ArrayList<Item> getItems(){
        return this.items;
    }
}
