package application;

public class Main {
      public static void main(String[] args) {
        UserAction userAction = new UserAction(); 
        userAction.setState(new UnregisterState(userAction));
      }
}