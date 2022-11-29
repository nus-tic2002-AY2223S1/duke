import java.time.LocalDate;

public class Event extends Task
{
    protected String my_at;
    protected LocalDate my_Event;

    public Event(String description, String taskType, String by)
    {
        super(description, taskType);
        this.my_at = by;
        my_Event = ParseDate(my_at);
        super.my_td = my_at;
        super.my_TaskDate = my_Event;
    }

    public void setEvent(String date)
    {
        my_at = date;
        my_Event = ParseDate(my_at);
    }

    public String getEvent()
    {
        return my_at;
    }

    @Override
    public String toString()
    {
        return "[E]" + super.toString() + " (at: " + my_at + ")";
    }
}
