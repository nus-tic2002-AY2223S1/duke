public class Item {
    private String name;
    private boolean isDone;
    private String type;

    private String time;

    public Item(String n, String t){
        this.isDone = false;
        this.type = t;
        switch (t){
            case "T":
                this.name = (n.replaceAll("todo","")).trim();
                this.time = "";
                break;
            case "E":
            case "D":
                n = n.replaceAll("deadline","");
                n = n.replaceAll("event","");
                String[] f = formatPassedName(n);
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

    public String getType(){
        return this.type;
    }
    public void setType(String t){
        this.type = t;
    }

    public String getTime(){
        return this.time;
    }
    public void setTime(String t){
        this.time = t;
    }

    private String[] formatPassedName(String n){
        String[] formatted = new String[2];
        Integer idx = n.indexOf("/");
        formatted[0] = n.substring(0, idx-1).trim();
        String x = n.substring(idx+1,idx+3);
        if(x.equals("at") || x.equals("by")){
            formatted[1] = n.substring(idx+3).trim();
        } else {
            formatted[1] = n.substring(idx+1).trim();
        }
        return formatted;
    }
}
