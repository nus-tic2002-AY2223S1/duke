import Helpers.CommonHelper;

public class Item {
    private String name;
    private boolean isDone;
    private TaskTypeEnumeration.TaskType type;

    private String time;

    public Item(String n, TaskTypeEnumeration.TaskType t){
        this.isDone = false;
        this.type = t;
        switch (t){
            case T:
                this.name = (n.replaceAll(t.getType(),"")).trim();
                this.time = "";
                break;
            case E:
            case D:
                n = n.replaceAll(t.getType(),"");
                n = n.replaceAll(t.getType(),"");
                String[] f = CommonHelper.formatPassedName(n);
                this.name = f[0];
                this.time = f[1];
                break;
        }
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

    public TaskTypeEnumeration.TaskType getType(){
        return this.type;
    }
    public void setType(TaskTypeEnumeration.TaskType t){
        this.type = t;
    }

    public String getTime(){
        return this.time;
    }
    public void setTime(String t){
        this.time = t;
    }


}
