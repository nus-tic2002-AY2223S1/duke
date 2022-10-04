public abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String n){
        this.name = n;
        this.isDone = false;
    }

    public abstract void printItem(Integer n);

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
