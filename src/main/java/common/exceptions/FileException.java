package common.exceptions;

import static common.constants.ErrorMessage.INVALID_FILE_ERROR_MSG;

public class FileException extends DukeException {

    public FileException() {
        super(INVALID_FILE_ERROR_MSG);
    }
}
