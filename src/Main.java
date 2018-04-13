import Controller.Keyboard;
import Model.Game;
import View.Window;
import Model.MyTimer;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();

        Game game = new Game(window);
        Keyboard keyboard = new Keyboard(game);
        window.setKeyListener(keyboard);
    }
}
