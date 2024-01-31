package userInterface;

import java.util.Scanner;

import adapter.UserInteraction;
import application.UserAction;
import utilities.Common;

public class DetailedDesktopInterface extends DetailedDesktop implements UserInteraction
{
    
  UserAction userAction;

  public DetailedDesktopInterface(UserAction userAction) {
    this.userAction = userAction;
  }

  @Override
  public void displayPage(Common.userActionState page) {
    clearTerminal();
    printColoredLine("#", 75, GREEN);
    switch (page) {
    case UNREGISTER:
      printStringWithBorders("Welcome to ClientConnect System\n",WHITE, "#", 75,GREEN);
      printStringWithBorders("Already have an account?",WHITE, "#", 75,GREEN);
      break;

    case LOGIN:
      printStringWithBorders("Login\n", "#",WHITE, 75,GREEN);
      break;

    case SIGNUP:
      printStringWithBorders("Sign Up", "#",WHITE, 75,GREEN);
      break;

    case HOME:
      printStringWithBorders("Home", "#",WHITE, 75,GREEN);
      break;

    case SALE_MANAGER:
      printStringWithBorders("Welcome to Sale Manager",WHITE, "#", 75,GREEN);
      break;

    case CUSTOMER_MANAGER:
      printStringWithBorders("Welcome to Customer Manager",WHITE, "#", 75,GREEN);
      break;

    case ACTIVITY_TRACKING:
      printStringWithBorders("Welcome to Activity Tracking",WHITE, "#", 75,GREEN);
      break;

    case CAMPAIGN_DASHBOARD:
      printStringWithBorders("Welcome to Campaign Dashboard",WHITE, "#", 75,GREEN);
      break;

    default:

      break;
    }

    printColoredLine("#", 75, GREEN);
  }

  @Override
  public void updatePage(String info) {
    System.out.println(info);
  }

  @Override
  public void updateMenu(Common.Menu menu) {
    printStringWithBorders(" ", WHITE,"#", 75, CYAN);

    if (!menu.option0.equals("")) {
      String text = "[0] " + menu.option0 + "\n";
      printStringWithBorders(text,WHITE, "#", 75, CYAN);
    }

    if (!menu.option1.equals("")) {
        String text = "[1] " + menu.option1 + "\n";
        printStringWithBorders(text,WHITE, "#", 75, CYAN);
    }

    if (!menu.option2.equals("")) {
        String text = "[2] " + menu.option2 + "\n";
        printStringWithBorders(text,WHITE, "#", 75, CYAN);
    }

    if (!menu.option3.equals("")) {
        String text = "[3] " + menu.option3 + "\n";
        printStringWithBorders(text,WHITE, "#", 75, GREEN);
    }

    printStringWithBorders(" ",WHITE, "#", 75, CYAN);
    printColoredLine("#", 75, GREEN);
  }

  @Override
  public void executeUserAction() {

    Scanner scanner = new Scanner(System.in);
    String option;

    option = scanner.nextLine();

    switch (option) {
    case "0":
      this.userAction.action0();
      break;

    case "1":
      this.userAction.action1();
      break;

    case "2":
      this.userAction.action2();
      break;

    case "3":
      this.userAction.action3();
      break;

    default:
      printInFrame("Invalid option", RED);
      executeUserAction();
      break;
    }

    scanner.close();
  }

  @Override
  public String getUserResponse() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine().toLowerCase();
  }

  private static void clearTerminal() {
    String os = System.getProperty("os.name").toLowerCase();

    try {
      if (os.contains("win")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        System.out.print("\033[H\033[2J");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
