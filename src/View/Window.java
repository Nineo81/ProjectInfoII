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

    private EscapeMenu escapeMenu = new EscapeMenu(this);
    private SkillTreePanel skillTreePanel = new SkillTreePanel(this);
    private View.InventoryPanel inventory = new View.InventoryPanel(this);
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
            JFrame inventoryFrame = new JFrame("Inventory");
            inventoryFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            inventoryFrame.setBounds(200, 0, 1400, 1020);
            inventoryFrame.addComponentListener(sizing);
            sizing.attachSizeable(this);
            inventoryFrame.getContentPane().setBackground(Color.gray);
            inventoryFrame.getContentPane().add(this.inventory);
            inventoryFrame.setVisible(true);
            this.window = inventoryFrame;
        }
    }

    public void setGameObjects(Vector<GameObject> objects) {
        this.map.setObjects(objects);
        this.map.redraw();
   
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


    public void setInventory(){
        this.inventory.attachModifier(((Player)(((Game) observers.get(0)).getGameObjects()).get(0)));
    }




    @Override
    public void resumeGame(){
        close();

        if ((observers.size()>0)&&(this.type==2)) {

            (observers.get(0)).resume();
        }
    }

    @Override
    public void attachResumer(ResumerObserver ro){observers.add(ro);}


    public ResumerObserver getObserver(){return observers.get(0);}


}
