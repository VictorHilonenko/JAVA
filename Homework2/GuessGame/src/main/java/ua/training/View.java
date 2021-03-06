package ua.training;

public class View {
    public static final String GREETING = "Hello! I conceived a number, can you guess it?";
    public static final String RANGE = "The number is in the range from %d to %d.";
    public static final String PROMPT = "You may enter your guess or press Enter w/o any input to stop the game:";
    public static final String GRATER = "The puzzled number is grater, keep on trying...";
    public static final String LESS = "The puzzled number is less, keep on trying...";
    public static final String INCORRECT_MUNBER = "Incorrect number, try again...";
    public static final String OUT_OF_BOUNDS = "Your number is out of bounds, try again...";
    public static final String CONGRATULATIONS = "You win! That's what I conceived";
    public static final String GOOD_BYE = "Goodbye!";
    public static final String USER_ENTERED = "You entered \"%s\"";
    public static final String I_RREPLIED = "I replied";
    public static final String LOG_LIST = "Your actions were:";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String makeFormattedString(String template, String[] params) {
        return String.format(template, params);
    }

    public String makeFormattedString(String template, Integer[] params) {
        return String.format(template, params);
    }

    public void printFormattedMessage(String template, Integer[] params) {
        System.out.println(makeFormattedString(template, params));
    }

}
