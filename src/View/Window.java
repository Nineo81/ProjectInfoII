package View;

import Model.GameObject;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Window {
    private Map map = new Map();
    private EscapeMenu escapeMenu= new EscapeMenu();
    JFrame window = new JFrame("Game");

    public Window() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(200, 0, 1400, 1020);
        window.getContentPane().setBackground(Color.gray);
        window.getContentPane().add(this.map);
        window.setVisible(true);
    }

    public void setGameObjects(ArrayList<GameObject> objects) {
        this.map.setObjects(objects);
        this.map.redraw();
    }

    public void update(boolean running) {
        if (running) {
            this.map.redraw();
        }
        else {this.escapeMenu.redraw();}
    }

    public void setKeyListener(KeyListener keyboard) {
        this.map.addKeyListener(keyboard);
    }

    public void openEscapeMenu(){
        window.getContentPane().add(this.escapeMenu);
    }

    public void closeEscapeMenu(){
        window.getContentPane().remove(this.escapeMenu);
    }
}
