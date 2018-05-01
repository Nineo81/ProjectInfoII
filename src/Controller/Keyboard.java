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

        switch (key) {
        case KeyEvent.VK_A:
             if (game.running()){game.action1(player1);}
             break;
        case KeyEvent.VK_Z:
            if (game.running()){game.action2(player1);}
            break;
        case KeyEvent.VK_ESCAPE:
            if (game.running()){game.pause();}
            else {game.resume();}
            break;
        case KeyEvent.VK_RIGHT:
            if (game.running()){game.movePlayer(1, 0, player1);}
            break;
        case KeyEvent.VK_LEFT:
            if (game.running()){game.movePlayer(-1, 0, player1);}
            break;
        case KeyEvent.VK_DOWN:
            if (game.running()){game.movePlayer(0, 1, player1);}
            break;
        case KeyEvent.VK_UP:
            if (game.running()){game.movePlayer(0, -1, player1);}
             break;
        case KeyEvent.VK_SPACE:
            if (game.running()){game.action(player1);}
            break;
        case KeyEvent.VK_P:
            if (game.running()){game.playerPos(player1);}
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
