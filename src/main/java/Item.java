public class Item {
    private String name;
    private boolean isDone;

    public Item(String n){
        this.name = n;
        this.isDone = false;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String n){
        this.name = n;
    }

    public boolean getIsDone(){
        return this.isDone;
    }
    public void setIsDone(boolean d){
        this.isDone = d;
    }
}
