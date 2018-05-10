package View;

import Model.GameObject;
import Model.SizeableObserver;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Window implements SizeableObserver {
    private Dimension size = new Dimension(1000, 800);
    private Map map = new Map(this.size);

    private EscapeMenu escapeMenu = new EscapeMenu();
    JFrame window = new JFrame("Game");
    private ResizeListener sizing = new ResizeListener();

    public Window() {
        JFrame window = new JFrame("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(200, 0, 1400, 1020);
        window.addComponentListener(sizing);
        sizing.attachSizeable(this);
        window.getContentPane().setBackground(Color.gray);
        window.getContentPane().add(this.map);
        window.getContentPane().add(this.escapeMenu);
        this.escapeMenu.setOpaque(false);
        this.escapeMenu.setVisible(true);
        window.setVisible(true);
        this.map.setVisible(true);
        window.getContentPane().add(this.escapeMenu);
    }

    public void setGameObjects(ArrayList<GameObject> objects) {
        this.map.setObjects(objects);
        this.map.redraw();
    }

    public void update() {
        this.map.redraw();
    }

    public void setKeyListener(KeyListener keyboard) {
        this.map.addKeyListener(keyboard);
    }

    public void openEscapeMenu() {
        this.escapeMenu.setVisible(true);
    }

    public void closeEscapeMenu() {
        this.escapeMenu.setVisible(false);
    }

    public void newSize(Dimension s) {
        this.size = s;
        update();
        System.out.println(this.size);
    }

    public void pause(boolean pauseState){
        if (pauseState){
            openEscapeMenu();
            System.out.println("opening menu");
        }
        else{
            closeEscapeMenu();
        }
    }
}
