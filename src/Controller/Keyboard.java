package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Game;

public class Keyboard implements KeyListener {
    private Game game;

    private static final int player1 = 0;

    public Keyboard(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();

        switch(key){
            case KeyEvent.VK_ESCAPE:
                game.pauseGame();
                //faire une classe menu pause
        }
        if(game.getPauseState()==false){
        switch (key) {
        case KeyEvent.VK_C:
             game.action2(player1);
             break;
        case KeyEvent.VK_RIGHT:
            game.movePlayer(1, 0, player1);
            break;
        case KeyEvent.VK_LEFT:
            game.movePlayer(-1, 0, player1);
            break;
        case KeyEvent.VK_DOWN:
            game.movePlayer(0, 1, player1);
            break;
        case KeyEvent.VK_UP:
            game.movePlayer(0, -1, player1);
             break;
        case KeyEvent.VK_SPACE:
             game.action(player1);
             break;
        case KeyEvent.VK_P:
             game.playerPos(player1);
             break;
        case KeyEvent.VK_E:
             game.action3(player1);
        }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
