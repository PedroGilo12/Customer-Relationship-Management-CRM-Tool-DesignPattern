package userInterface;

public class DetailedDesktop {

    protected static String RESET = "\u001B[0m";
    protected static String BLACK = "\u001B[30m";
    protected static String RED = "\u001B[31m";
    protected static String GREEN = "\u001B[32m";
    protected static String YELLOW = "\u001B[33m";
    protected static String BLUE = "\u001B[34m";
    protected static String PURPLE = "\u001B[35m";
    protected static String CYAN = "\u001B[36m";
    protected static String WHITE = "\u001B[37m";

    protected static void makeFrame(String title,String Color,String character, int length, int width) {
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

    protected static void makeTitle(String title, String Color) {
        makeFrame(title, Color, "#", 3, 30);
    }

    protected static void printInFrame(String text, String Color) {
        System.out.println(Color + text + RESET);
    }

    protected static void printColoredLine(String character, int length, String color) {
        for (int i = 0; i < length; i++) {
            System.out.print(color + character + RESET);
        }
        System.out.println();
    }

    protected static void printStringWithBorders(String text, String textColor, String character, int length, String color) {
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
