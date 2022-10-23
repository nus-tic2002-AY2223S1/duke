package exceptions;

public class DoWithinPeriodWrongFormatException extends DukeException {

    /**
     * DoWithinPeriodWrongFormatException constructor
     */
    public DoWithinPeriodWrongFormatException(){
        super(" ☹ OOPS!!! Input has wrong format. DoWithinPeriod command should be: dowithinperiod {description} /between {start date} /and {end date}");
    }

}
