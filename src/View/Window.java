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
    int type;
    private Inventory inventory;


    private EscapeMenu escapeMenu = new EscapeMenu(this);
    private SkillTreePanel skillTreePanel = new SkillTreePanel(this);
    private View.InventoryPanel inventoryPanel = new View.InventoryPanel(this);
    JFrame window = new JFrame("Game");
    private ResizeListener sizing = new ResizeListener();

    private Vector<ResumerObserver> observers = new Vector<ResumerObserver>();

    public Window(int type) { //type 1= game - type 2 = menu
        this.type=type;
        if (type==1) {
            JFrame window = new JFrame("Game");
            this.window = window;
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
        else if(type==3) {
            JFrame skillTree = new JFrame("Skill Tree");
            skillTree.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            skillTree.setBounds(200, 0, 1400, 1020);
            skillTree.addComponentListener(sizing);
            sizing.attachSizeable(this);
            skillTree.getContentPane().setBackground(Color.gray);
            skillTree.getContentPane().add(this.skillTreePanel);
            skillTree.setVisible(true);
            this.window = skillTree;
        }
        else if(type==4) {
            JFrame inventoryPanel = new JFrame("Inventory");
            inventoryPanel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            inventoryPanel.setBounds(200, 0, 1400, 1020);
            inventoryPanel.addComponentListener(sizing);
            sizing.attachSizeable(this);
            inventoryPanel.getContentPane().setBackground(Color.gray);
            inventoryPanel.getContentPane().add(this.inventoryPanel);
            inventoryPanel.setVisible(true);
            this.window = inventoryPanel;
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
        if (type==1) {
            this.map.setSize(s);
            update();
        }
        else if (type==2){
            //dicks
        }
        else if (type==3){
            //more dicks
        }


    }

    public void close(){
        window.setVisible(false);
        window.dispose();
    }

    public void drawWall(){this.map.wallConstructor();}



    @Override
    public void resumeGame(){
        close();
        if (observers.size()>0) {
            (observers.get(0)).resume();
        }
    }

    @Override
    public void attachResumer(ResumerObserver ro){observers.add(ro);}

}
