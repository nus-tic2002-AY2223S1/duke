package domain;

import domain.task.Task;

import java.util.List;

import static utils.CommonStrings.FIVE_SPACE;
import static utils.CommonStrings.UNDERSCORES;

public class Mouth {
    public static void speak(String sentence) {
        System.out.println(UNDERSCORES);
        sentence = sentence.replace("\n", "\n     ");
        System.out.println(FIVE_SPACE + sentence);
        System.out.println(UNDERSCORES);
    }

    public static void speak(List<Task> tasks) {
        System.out.println(UNDERSCORES);
        if (tasks.isEmpty())
            System.out.println("Task List is Empty");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println(FIVE_SPACE + (i+1) + ": " + t);
        }
        System.out.println(UNDERSCORES);
    }
}
