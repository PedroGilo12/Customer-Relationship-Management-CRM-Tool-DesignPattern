package userInterface;

import adapter.*;
import application.*;
import java.util.Scanner;
import java.util.Stack;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import utilities.*;

public class GuiInterface implements UserInteraction {
    UserAction userAction;
    private Panel mainPanel;
    private ActionListBox actionListBox;
    private Panel leftPanel;
    private Panel rightPanel;
    private MultiWindowTextGUI gui;
    private BasicWindow window;
    private Screen screen;
    private Label textLabel;
    private TextBox inputTextBox;

    private Stack<String> menuStack = new Stack<>();

    private Common.userActionState currentPage;
    
    public GuiInterface(UserAction userAction) {
        this.userAction = userAction;

        this.textLabel = new Label("");

        try {
            Terminal terminal = new DefaultTerminalFactory()
            .setInitialTerminalSize(new TerminalSize(100, 50))
            .createTerminal();

            this.screen = new TerminalScreen(terminal);
            this.screen.startScreen();

            this.gui = new MultiWindowTextGUI(this.screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("GuiInterface created");
    }

  public void makeWindow(String title) {
    
        this.window = new BasicWindow();

        this.mainPanel = new Panel().setPreferredSize(new TerminalSize(92, 42));
        this.mainPanel.setLayoutManager(new LinearLayout(Direction.HORIZONTAL));

        this.leftPanel = new Panel().setPreferredSize(new TerminalSize(30, 42));
        this.mainPanel.addComponent(this.leftPanel.withBorder(Borders.singleLine("Left Panel")));

        this.rightPanel = new Panel().setPreferredSize(new TerminalSize(62, 42));
        this.mainPanel.addComponent(rightPanel.withBorder(Borders.singleLine("Right Panel")));

        this.actionListBox = new ActionListBox(new TerminalSize(30, 42));
        this.inputTextBox = new TextBox().setPreferredSize(new TerminalSize(62, 1));

        this.window.setComponent(this.mainPanel.withBorder(Borders.singleLine(title)));
        
        this.gui = new MultiWindowTextGUI(this.screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
        
        this.rightPanel.addComponent(this.textLabel);
        this.leftPanel.addComponent(this.actionListBox);
        this.gui.addWindow(this.window);
    
  }
    
  @Override
  public void displayPage(Common.userActionState page) {
    if(this.currentPage != page) {
        clearTerminal(); 
        this.currentPage = page;   
    }
    switch (page) {
    case UNREGISTER:
      this.makeWindow("Welcome to ClientConnect System\n");
      break;

    case LOGIN:
      this.makeWindow("Login");
      break;

    case SIGNUP:
      this.makeWindow("Sign Up");
      break;

    case HOME:
      this.makeWindow("Home");
      break;

    case SALE_MANAGER:
      this.makeWindow("Welcome to Sale Manager");
      break;

    case CUSTOMER_MANAGER:
      this.makeWindow("Welcome to Customer Manager");
      break;

    case ACTIVITY_TRACKING:
      this.makeWindow("Welcome to Activity Tracking");
      break;

    case CAMPAIGN_DASHBOARD:
      this.makeWindow("Welcome to Campaign Dashboard");
      break;

    default:

      break;
    }
  }

  @Override
  public void updatePage(String info) {
    this.displayPage(this.currentPage);
    String text = this.textLabel.getText();
    this.textLabel.setText(text + "\n" + info);
    try {
      this.gui.updateScreen();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateMenu(Common.Menu menu) {

    actionListBox.clearItems();

    if (!menu.option0.equals("")) {
        this.actionListBox.addItem("[0] " + menu.option0 + "", () -> {
          this.menuStack.push("0");
          this.gui.removeWindow(this.window);
        });
    }

    if (!menu.option1.equals("")) {
      this.actionListBox.addItem("[1] " + menu.option1 + "", () -> {
        this.menuStack.push("1");
        this.gui.removeWindow(this.window);
      });
    }

    if (!menu.option2.equals("")) {
      this.actionListBox.addItem("[2] " + menu.option2 + "", () -> {
          this.menuStack.push("2");
          this.gui.removeWindow(this.window);
      });
    }

    if (!menu.option3.equals("")) {
      this.actionListBox.addItem("[3] " + menu.option3 + "", () -> {
          this.menuStack.push("3");
          this.gui.removeWindow(this.window);
      });
    }
    try {
      this.gui.updateScreen();
      this.gui.addWindowAndWait(this.window);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void executeUserAction() {
    String option = this.menuStack.pop();

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
      System.out.println("Invalid option");
      executeUserAction();
      break;
    }
  }

  private Stack<String> entryStack = new Stack<>();

  @Override
  public String getUserResponse() {
    this.displayPage(currentPage);
    
    this.inputTextBox.setText("");
    this.rightPanel.addComponent(this.inputTextBox);
    Button button = new Button("Enter", () -> {
        this.entryStack.push(this.inputTextBox.getText());
        this.gui.removeWindow(this.window);
    });

    this.rightPanel.addComponent(button);

    try {
      this.gui.updateScreen();
      this.gui.addWindowAndWait(window);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return this.entryStack.pop().toLowerCase();
  }


  private void clearTerminal() {
    this.textLabel.setText("");
  }
}
