package domain.exceptions;

public enum DukeExceptionCode {
    Validation("E100"),
    NotFound("E101"),
    Existed("E102"),
    Argument("E103"),
    Other("E000");
    public final String code;

    private DukeExceptionCode(String code) {
        this.code = code;
    }
}
