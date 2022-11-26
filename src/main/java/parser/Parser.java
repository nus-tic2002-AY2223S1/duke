package parser;

import exceptions.DukeException;
import tasklist.Tasklist;
import ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.EmptyStackException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    public static boolean error;

    /**
     * Parse command entered by the user.
     *
     * @throws EmptyStackException If command enter by user is blank
     * @throws IllegalArgumentException If command entered is not recognised
     * @throws NullPointerException If command entered is not recognised
     *
     * Find Command:
     * @throws DukeException If command entered is not in correct format
     *
     * Due Command:
     * Accepts both datetime eg. 2019-02-02 and text eg. today
     * @throws DukeException If command entered is not in correct format
     * @throws DateTimeException If duedate is supposedly enter as datetime but not in correct format
     *
     * Event Command:
     * Required format: event - description - /at - text/datetime
     * eg. event read book /at 2019-02-02
     * eg. event read book /at today
     * @throws DukeException If command entered is not in correct format
     * @throws IndexOutOfBoundsException If command entered is not in correct format
     * @throws DateTimeException If eventAtDate is supposedly enter as datetime but not in correct format
     *
     * Deadline Command:
     * Required format: deadline - description - /by - text/datetime
     * eg. deadline read book /by 2019-02-02
     * eg. deadline read book /by today
     * @throws DukeException If command entered is not in correct format
     * @throws IndexOutOfBoundsException If command entered is not in correct format
     * @throws DateTimeException If deadlineByDate is supposedly enter as datetime but not in correct format
     *
     * Mark/Unmark/Delete Command
     * Required format: command - index
     * eg. mark 2
     * @throws DukeException If command entered is not in correct format
     * @throws IndexOutOfBoundsException If index is larger than number of existing tasks in tasklist
     * @throws NumberFormatException If index is not numeric
     *
     * Postpone Command
     * (Only for time sensitive tasks ie deadline, event)
     * Required format: postpone - index - /to - text/datetime
     * eg. postpone 2 to 2019-02-02
     * eg. postpone 2 to today
     * @throws DukeException If command entered is not in correct format
     * @throws IndexOutOfBoundsException If index is larger than number of existing tasks in tasklist
     * @throws NullPointerException If index is larger than number of existing tasks in tasklist
     * @throws NumberFormatException If index is not numeric
     * @throws DateTimeException If postponeToDate is supposedly enter as datetime but not in correct format
     * @throws IllegalArgumentException If task matching index provided is not acceptable task type (event/deadline)
     */

    public static Command parse(String input) {
        Command parsed = null;

        do {
            try {
                if(input.isBlank()){
                    throw new EmptyStackException();
                }

                parsed = null;
                error = false;
                ArrayList<String> splitArray;
                ArrayList<String> inputArray = new ArrayList<String>(Arrays.asList(input.split(" ", 2)));

                String command = inputArray.get(0);
                switch (command.trim().toLowerCase()) {
                    /** Command with <S>command */
                    case "list":
                    case "bye":
                        parsed = new Command(inputArray.get(0).trim().toLowerCase());
                        break;
                    /** Command with <S>command - <S>des */
                    case "find":
                        try {
                            if (inputArray.size() != 2){
                                throw new DukeException();
                            }
                            parsed = new Command(inputArray.get(0).trim().toLowerCase(), inputArray.get(1).trim());
                        } catch (DukeException e) {
                            System.out.println(Ui.ERROR_INVALID_FIND + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        }
                        break;
                    /** Command with <S>command - <S>datetime */
                    case "due":
                        try {
                            if (inputArray.size() != 2){
                                throw new DukeException();
                            }
                            parsed = new Command(inputArray.get(0).trim().toLowerCase(), formatDateTime(inputArray.get(1).trim()));
                        } catch(DukeException e) {
                            System.out.println(Ui.ERROR_INVALID_DUE + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        } catch (DateTimeException e) {
                            System.out.println(Ui.ERROR_INVALID_DATETIME + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        }
                        break;
                    /** Command with <S>command - <S>des - <C>type */
                    case "todo":
                        try {
                            if (inputArray.size() != 2){
                                throw new DukeException();
                            }
                            parsed = new Command(inputArray.get(0).trim().toLowerCase(), inputArray.get(1).trim(), 'T');
                        } catch (DukeException | IndexOutOfBoundsException e) {
                            System.out.println(Ui.ERROR_INVALID_TODO + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        }
                        break;
                    /** Command with <S>command - <S>des - <S>datetime - <C>type */
                    case "event":
                        try {
                            if (inputArray.size() != 2){
                                throw new DukeException();
                            }
                            splitArray = splitAt(inputArray.get(1));

                            parsed = new Command(inputArray.get(0).trim().toLowerCase(), splitArray.get(0).trim(), formatDateTime(splitArray.get(1).trim()), 'E');
                        } catch (DukeException | IndexOutOfBoundsException e ) {
                            System.out.println(Ui.ERROR_INVALID_EVENT + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        } catch (DateTimeException e) {
                            System.out.println(Ui.ERROR_INVALID_DATETIME + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        }
                        break;
                    case "deadline":
                        try {
                            if (inputArray.size() != 2){
                                throw new DukeException();
                            }
                            splitArray = splitBy(inputArray.get(1));

                            parsed = new Command(inputArray.get(0).trim().toLowerCase(), splitArray.get(0).trim(), formatDateTime(splitArray.get(1).trim()), 'D');
                        } catch (DukeException | IndexOutOfBoundsException e) {
                            System.out.println(Ui.ERROR_INVALID_DEADLINE + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        } catch (DateTimeException e) {
                            System.out.println(Ui.ERROR_INVALID_DATETIME + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        }
                        break;
                    /** Command with <S>command - <I>index */
                    case "mark":
                    case "unmark":
                    case "delete":
                        try {
                            if(inputArray.size() != 2){
                                throw new DukeException();
                            } else if(Integer.parseInt(inputArray.get(1))-1 >= Tasklist.getListcount()){
                                throw new IndexOutOfBoundsException();
                            } else if (Integer.parseInt(inputArray.get(1)) < 1) {
                                throw new NumberFormatException();
                            }
                            parsed = new Command(inputArray.get(0).trim().toLowerCase(), Integer.parseInt(inputArray.get(1).trim()));
                        } catch(DukeException e) {
                            System.out.println(Ui.ERROR_INVALID_MARKDEL + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        } catch (IndexOutOfBoundsException | NullPointerException e) {
                            System.out.println(Ui.ERROR_INDEXOUTOFBOUNDS + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        } catch (NumberFormatException e) {
                            System.out.println(Ui.ERROR_INVALID_INDEX + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        }
                        break;
                    /** Command with <S>command - <I>index - <S>datetime */
                    case "postpone":
                        try {
                            if (inputArray.size() != 2){
                                throw new DukeException();
                            }
                            splitArray = splitTo(inputArray.get(1));

                            if(splitArray.get(1).isBlank()) {
                                throw new DukeException();
                            } else if (!Tasklist.tasklist.get(Integer.parseInt(splitArray.get(0))-1).getTypeTask().equals("D")
                                    && !Tasklist.tasklist.get(Integer.parseInt(splitArray.get(0))-1).getTypeTask().equals("E")) {
                                throw new IllegalArgumentException();
                            }
                            parsed = new Command(inputArray.get(0).trim().toLowerCase(), Integer.parseInt(splitArray.get(0).trim()), formatDateTime(splitArray.get(1).trim()));
                        } catch (DukeException e) {
                            System.out.println(Ui.ERROR_INVALID_POSTPONE + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        } catch (IndexOutOfBoundsException | NullPointerException e) {
                            System.out.println(Ui.ERROR_INDEXOUTOFBOUNDS + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        } catch (NumberFormatException e) {
                            System.out.println(Ui.ERROR_INVALID_INDEX + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        } catch (DateTimeException e) {
                            System.out.println(Ui.ERROR_INVALID_DATETIME + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        } catch (IllegalArgumentException e) {
                            System.out.println(Ui.ERROR_INVALID_POSTPONETYPE + "\n" + Ui.UI_DIVIDER);
                            input = reEnter();
                        }
                    default:
                        break;
                }
            } catch (EmptyStackException | IllegalArgumentException | NullPointerException e) {
                System.out.println(Ui.ERROR_INVALID_COMMAND + "\n" + Ui.UI_DIVIDER);
                input = reEnter();
            }
        } while (error);

        assert parsed.command != null : "Valid command must be parsed from input";
        return parsed;
    }

    public static ArrayList<String> splitAt(String input) {
        ArrayList<String> split = new ArrayList<String>(Arrays.asList(input.split("/at ",2)));
        return split;
    }

    public static ArrayList<String> splitBy(String input) {
        ArrayList<String> split = new ArrayList<String>(Arrays.asList(input.split("/by ",2)));
        return split;
    }

    public static ArrayList<String> splitTo(String input) {
        ArrayList<String> split = new ArrayList<String>(Arrays.asList(input.split(" /to ",2)));
        return split;
    }

    public static String formatDateTime(String datetime){
        /** Input contains date & time eg. 1986-04-08T12:30 */
        if(datetime.length() == 16){
            LocalDateTime formattedDatetime = LocalDateTime.parse(datetime);
            DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("EEE MMM dd, yyyy HH:mm");
            return formattedDatetime.format(dateformatter);

        /** Input contains date only eg. 1986-04-08 */
        } else if(datetime.length() == 10){
            LocalDate formattedDate = LocalDate.parse(datetime);
            DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("EEE MMM dd, yyyy");
            return formattedDate.format(dateformatter);
        } else {
            return datetime;
        }
    }

    public static String reEnter (){
        error = true;
        return Ui.readCommand();
    }
}