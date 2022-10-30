public class DukeOutput {
    protected String format ;

    public DukeOutput(String format) {
        this.format = format;
    }

    public void Output(String message){
        System.out.format(this.format, message);
    }

    public void Output(StringBuilder message){
        System.out.format(this.format, message);
    }

    public void Warning(String message){
        System.out.format(this.format, "⚠️ "+message);
    }

    public void Error(String message){
        System.out.format(this.format, "🚫️ "+message);
    }
}
