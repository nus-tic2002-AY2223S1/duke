package exercise;

import java.util.Arrays;

public class Week1 {

    public static double[] getMultipleGradeCaps(String[] grades) {
        double[] caps = new double[grades.length];
        for (int i = 0; i < grades.length; i++) {
            caps[i] = getGradeCap(grades[i]);
        }
        return caps;
    }

    public static double getGradeCap(String grade) {
        switch (grade) {
            case "A+":
            case "A":
                return 5.0;
            case "A-":
                return 4.5;
            case "B+":
                return 4.0;
            case "B":
                return 3.5;
            case "B-":
                return 3;
            case "C":
                return 2.5;
            default:
                return 0;
        }
    }

    public static void grader_helper(String[] args) {
        String grade = args[0];
        double cutoff = 0;
        switch (grade) {
            case "A+":
                cutoff = 5.0;
                break;
            case "A":
                cutoff = 5.0;
                break;
            case "A-":
                cutoff = 4.5;
                break;
            case "B+":
                cutoff = 4.0;
                break;
            case "B":
                cutoff = 3.5;
                break;
            case "B-":
                cutoff = 3;
                break;
            case "C":
                cutoff = 2.5;
                break;
            default:
                cutoff = 0;

        }
        System.out.println("CAP for grade " + grade + " is " + cutoff);
    }

    public static void main(String[] args) {
        grader_helper(new String[] {"A+"});
    }
}
