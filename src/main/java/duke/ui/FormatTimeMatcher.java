package duke.ui;

import java.util.regex.Pattern;

public class FormatTimeMatcher implements TimeMatcher{
    private static final Pattern TIME_PATTERN = Pattern.compile(
            "^(((([0-1][0-9])|(2[0-3])):?[0-5][0-9]:?[0-5][0-9]+$))");

    @Override
    public boolean matches(String time) {
        return TIME_PATTERN.matcher(time).matches();
    }
}
