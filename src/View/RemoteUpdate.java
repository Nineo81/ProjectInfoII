package View;

public class RemoteUpdate {
    private Command command;
    public void setCommand(Command command){
        this.command=command;
    }
    public void pressButton(){
        command.execute();
    }
}
