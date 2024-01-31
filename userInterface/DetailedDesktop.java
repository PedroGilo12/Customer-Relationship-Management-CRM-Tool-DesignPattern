package userInterface;

public class DetailedDesktop {

    public static String RESET = "\u001B[0m";
    public static String BLACK = "\u001B[30m";
    public static String RED = "\u001B[31m";
    public static String GREEN = "\u001B[32m";
    public static String YELLOW = "\u001B[33m";
    public static String BLUE = "\u001B[34m";
    public static String PURPLE = "\u001B[35m";
    public static String CYAN = "\u001B[36m";
    public static String WHITE = "\u001B[37m";

    public static void makeFrame(String title,String Color,String character, int length, int width) {
        if (length <= 0 || width <= 0) {
            return;
        }

        System.out.println(Color + title + RESET);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == length - 1 || j == 0 || j == width - 1) {
                    System.out.print( Color + character + " " + RESET);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void makeTitle(String title, String Color) {
        makeFrame(title, Color, "#", 3, 30);
    }

    public static void printInFrame(String text, String Color) {
        System.out.println(Color + text + RESET);
    }

    public static void printColoredLine(String character, int length, String color) {
        for (int i = 0; i < length; i++) {
            System.out.print(color + character + RESET);
        }
        System.out.println();
    }

    public static void printStringWithBorders(String text, String textColor, String character, int length, String color) {
        System.out.print(color + character + " ");
        int len = text.length();
        String new_text;
        if(text.contains("\n")) {
            new_text = text.substring(0, len - 1);
            System.out.print(textColor + new_text + RESET);
            for (int i = 0; i < length - 2 - len; i++) {
                System.out.print(" ");
            }
            System.out.println(color + character + RESET);
        } else {
            new_text = text;
            System.out.print(textColor + new_text + RESET);
            for (int i = 0; i < length - 3 - len; i++) {
                System.out.print(" ");
            }
            System.out.println(color + character + RESET);
        }
        
    }

}
