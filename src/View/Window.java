package View;

import Model.*;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.Vector;
import java.awt.Dimension;

import javax.swing.*;


public class Window implements SizeableObserver, Resumer {
    private Dimension size = new Dimension(1000, 800);
    private Map map = new Map();
    private Inventory inventory;

    private EscapeMenu escapeMenu = new EscapeMenu(this);
    JFrame window = new JFrame("Game");
    private ResizeListener sizing = new ResizeListener();

    private Vector<ResumerObserver> observers = new Vector<ResumerObserver>();

    public Window(int type) { //type 1= game - type 2 = menu
        if (type==1){
        JFrame window = new JFrame("Game");
        this.window=window;
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(200, 0, 1400, 1020);
        window.addComponentListener(sizing);
        sizing.attachSizeable(this);
        window.getContentPane().setBackground(Color.gray);
        window.getContentPane().add(this.map);
        window.setVisible(true);
        }
        else if (type==2) {
            JFrame pause = new JFrame("Pause");
            pause.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            pause.setBounds(200, 0, 1400, 1020);
            pause.addComponentListener(sizing);
            sizing.attachSizeable(this);
            pause.getContentPane().setBackground(Color.gray);
            pause.getContentPane().add(this.escapeMenu);
            pause.setVisible(true);
            this.window=pause;
        }

    }

    public void setGameObjects(Vector<GameObject> objects) {
        this.map.setObjects(objects);
        this.inventory=((Player)objects.get(0)).getInventory();
        this.map.redraw();
    }

    public void update() {
        this.map.redraw();
    }

    public void setKeyListener(KeyListener keyboard) {
        this.map.addKeyListener(keyboard);
    }



    public void newSize(Dimension s) {
        this.map.setSize(s);
        update();
    }

    public void close(){
        window.setVisible(false);
        window.dispose();
    }

    public void drawWall(){this.map.wallConstructor();}



    @Override
    public void resumeGame(){
        close();
        (observers.get(0)).resume();
    }

    @Override
    public void attachResumer(ResumerObserver ro){observers.add(ro);}

}
