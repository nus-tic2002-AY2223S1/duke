package service;

import entity.Form;
import util.StringUtil;

public class Parser {

    private Parser() {}

    public static Form parseForm(String input) {
        input = StringUtil.trim(input);
        String[] args = input.split(" ");
        if (args.length > 2) {
            // exception here
        }

        // TODO
        extracted(args);

        Form form = new Form(input);
        form.setCommand(input);
        return form;
    }

    private static void extracted(String[] args) {
        String command = args[0];
    }


    private class MarkAction
}
