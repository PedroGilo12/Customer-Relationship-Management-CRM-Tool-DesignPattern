package application;

public interface ApplicationState {

    public String getName();

    public void entry(Object data);
    public void exit(Object data);

    public void option0(Object data);
    public void option1(Object data);
    public void option2(Object data);
    public void option3(Object data);

    public String defaultTextInput(Object data);
    public String passwordTextInput(Object data);
}