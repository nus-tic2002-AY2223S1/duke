package service;

import entity.Form;

public class Parser {

    private Parser() {}

    public static Form parseForm(String input) {
        System.out.println("parsing command ...");
        Form form = new Form(input);
        form.setCommand(input);
        return form;
    }
}
