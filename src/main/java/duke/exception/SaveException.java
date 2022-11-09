package duke.exception;

@SuppressWarnings("serial")
public class SaveException extends Exception {
    public static enum SaveExceptionType {
        NO_SAVE,
        INVALID_SAVE,
        INVALID_PATH
    }

    public SaveException(SaveExceptionType saveType) {
        this(saveType, null);
    }

    public SaveException(SaveExceptionType saveType, Throwable err) {
        super(getDescription(saveType), err);
    }

    public static String getDescription(SaveExceptionType saveType) {
        switch (saveType) {
            case NO_SAVE:
                return "No save file is found at the given path.";
            case INVALID_SAVE:
                return "The save file looks invalid - we cannot recover a list from it.";
            case INVALID_PATH:
                return "Save file at the path specified cannot be created or overwritten.";
            default:
                return "This type of invalid input is not recognized.";
        }
    }
}
