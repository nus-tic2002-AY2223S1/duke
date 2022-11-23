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
    private static String input;
    public static Command parse(String input) {
        Command parsed = null;

        do {
            try {
                if(input.isBlank()){
                    throw new EmptyStackException();
                }

                parsed = null;
                error = false;
                ArrayList<String> splitArray = null;
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
                        parsed = new Command(inputArray.get(0).trim().toLowerCase(), inputArray.get(1).trim());
                        break;
                    /** Command with <S>command - <S>datetime */
                    case "due":
                        parsed = new Command(inputArray.get(0).trim().toLowerCase(), formatDateTime(inputArray.get(1).trim()));
                        break;
                    /** Command with <S>command - <S>des - <C>type */
                    case "todo":
                        parsed = new Command(inputArray.get(0).trim().toLowerCase(), inputArray.get(1).trim(), 'T');
                        break;
                    /** Command with <S>command - <S>des - <S>datetime - <C>type */
                    case "event":
                        try {
                            if (inputArray.get(1).isBlank()){
                                throw new DukeException();
                            }
                            splitArray = splitAt(inputArray.get(1));

                            if(splitArray.get(1).isBlank()) {
                                throw new DukeException();
                            }
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
                            if (inputArray.get(1).isBlank()){
                                throw new DukeException();
                            }
                            splitArray = splitBy(inputArray.get(1));

                            if(splitArray.get(1).isBlank()) {
                                throw new DukeException();
                            }
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
                            if(Integer.parseInt(inputArray.get(1)) > Tasklist.getListcount()){
                                throw new IndexOutOfBoundsException();
                            } else if (Integer.parseInt(inputArray.get(1)) < 1) {
                                throw new NumberFormatException();
                            }
                            parsed = new Command(inputArray.get(0).trim().toLowerCase(), Integer.parseInt(inputArray.get(1).trim()));
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
                            if (inputArray.get(1).isBlank()){
                                throw new DukeException();
                            }
                            splitArray = splitTo(inputArray.get(1));

                            if(splitArray.get(1).isBlank()) {
                                throw new DukeException();
                            } else if (!Tasklist.tasklist.get(Integer.parseInt(splitArray.get(0))).getTypeTask().equals("D")
                                    || !Tasklist.tasklist.get(Integer.parseInt(splitArray.get(0))).getTypeTask().equals("E")) {
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
                            System.out.println(Ui.ERROR_INVALID_POSTPONETYPE);
                            input = reEnter();
                        }
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (EmptyStackException | IllegalArgumentException e) {
                System.out.println(Ui.ERROR_INVALID_COMMAND + "\n" + Ui.UI_DIVIDER);
                input = reEnter();
            }
        } while (error);

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