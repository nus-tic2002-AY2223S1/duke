package domain;

import domain.Task;

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
        tasks.forEach(t -> System.out.println(FIVE_SPACE + t));
        System.out.println(UNDERSCORES);
    }
}
