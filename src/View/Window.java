package View;

import Model.GameObject;
import Model.SizeableObserver;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window implements SizeableObserver {
    private Dimension size = new Dimension(1000,800);
    private Map map = new Map(this.size);
    private ResizeListener sizing = new ResizeListener();

    public Window() {
        JFrame window = new JFrame("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 1000, 1020);
        window.addComponentListener(sizing);
        sizing.attachSizeable(this);
        window.getContentPane().setBackground(Color.gray);
        window.getContentPane().add(this.map);
        window.setVisible(true);
        System.out.println(size);
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

    public void newSize(Dimension s){
        this.size=s;
        update();
        System.out.println(this.size);
    }
}
