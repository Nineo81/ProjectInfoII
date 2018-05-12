package Model;

import View.Resumer;
import View.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.function.*;
import java.util.Random;
import java.io.FileReader;

//import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

//import org.omg.CosNaming.IstringHelper;

public class Game implements DeletableObserver, LevelableObserver, MovingObserver, ResumerObserver {
    private Vector<GameObject> objects = new Vector<GameObject>();
    SamplePredicate<GameObject> filter = new SamplePredicate<>();

    private Window window;
    private Thread sleepThread;
    private int size = 20;
    // private int bombTimer = 3000;
    private int numberOfMobs=6;
    private boolean running=true;
    boolean pauseState = false;

    Window escapeMenu;

    public Game(Window window) {
        this.window = window;
        Ninja player = new Ninja(0, 0, 10, 100, this);
        objects.add(player);
        filter.varc1 = player;
        nextLevel();
        //Thread t1 = new Thread(new MyTimer(this));
        //t1.start();
    }


    public synchronized void movePlayer(int x, int y, int playerNumber) {
        MovingObject player = ((MovingObject) objects.get(playerNumber));
        int xPos= player.getPosX();
        int yPos=player.getPosY();
        int nextX = xPos + x;
        int nextY = yPos + y;

        boolean obstacle = true;
        while (obstacle) {
            obstacle=false;
            for (GameObject object : objects) {
                if (object.isAtPosition(nextX, nextY)) {
                    obstacle = object.isObstacle();
                }
                if (obstacle) {
                    if ((player instanceof  Mob) && (object instanceof Activable) ){
                        ((Activable) object).activate(1);
                    }
                    break;
                }
            }
            player.rotate(x, y);
            if (!obstacle) {
                player.move(x, y);
            }
            else{
                if (x>0){ x--; }
                if (x<0){ x++; }
                if (y>0){ y--; }
                if (y<0){ y++; }
                if((x == 0) && (y == 0)){ return; }
                nextX=xPos+x;
                nextY=yPos+y;
            }
        }
        notifyView();
    }


    public void action(int playerNumber) {
        Powered player = ((Powered) objects.get(playerNumber));
        player.action(objects);
        notifyView();
    }

    public void action1(int playerNumber) {
        Powered player = ((Powered) objects.get(playerNumber));
        player.action1(objects);
        notifyView();
    }

    public void action2(int playerNumber) {
        Powered player = ((Powered) objects.get(playerNumber));
        player.action2(objects);
    }

    public void action3(int playerNumber) {
        Powered player = ((Powered) objects.get(playerNumber));
        player.action3(objects);
        notifyView();
    }

    public void add(GameObject object){
        objects.add(object);
        if (object instanceof Deletable){
            ((Deletable) object).attachDeletable(this);
        }
        if (object instanceof Moving){
            ((Moving) object).attachMoving(this);
        }
    }

    public void notifyView() {
        window.update();
    }

    public Vector<GameObject> getGameObjects() {
        return this.objects;
    }

    @Override
    public synchronized void delete(Deletable ps, GameObject loot) {
        objects.remove(ps);
        if(loot!=null){
            Random rand = new Random();
            if(rand.nextInt(2)==1){
                objects.add(loot);
            }
        }
        notifyView();
    }

    public void nextLevel(){
        objects.removeIf(filter);

        window.setGameObjects(this.getGameObjects());
        //New Map building
        Level level = new Level();
        mapReader(level.getLevelMap());

        Random rand = new Random();

        int px=getPlayerX(); int py=getPlayerY(); boolean occupied=true;
        while (occupied){
            occupied=false;
            for (GameObject object : objects) {
                if (object.isAtPosition(px, py)) {
                    occupied=true;
                    break;
                }
            }
            if (occupied){
                px=rand.nextInt(10);
                py=rand.nextInt(10);
            }
        }

        ((Player)objects.get(0)).move(px,py);

        //mob spawning
        int n=0;
        while (n<numberOfMobs) {
            int x = rand.nextInt(16) + 2;
            int y = rand.nextInt(16) + 2;
            int lifepoints = rand.nextInt(3) + 3;
            occupied = false;


            for (GameObject object : objects) {
                if (object.isAtPosition(x, y)) {
                    occupied = true;
                    break;
                }
                if ((Math.abs(10 - x) + Math.abs(10 - y)) < 6) {
                    occupied = true;
                    break;
                }
            }
            if (!occupied) {
                n++;
                Mob mob = new Mob( x, y, lifepoints);
                mob.attachDeletable(this); mob.attachMoving(this);
                objects.add(mob);
            }
        }

        Stair stair = new Stair(10,10);
        stair.attachLevelable(this);
        objects.add(stair);
    }

    public void mapReader (int[][] room) {
        int x=0;
        int y=0;
        for (int[] i:room){
            for (int j:i){
                switch (j){
                    case 1:
                        objects.add(new BlockUnbreakable(x, y));
                        break;
                    case 2:
                        BlockBreakable block = new BlockBreakable(x, y, 3);
                        block.attachDeletable(this);
                        objects.add(block);
                }
                x++;
            }
            x=0;
            y++;
        }
        window.setGameObjects(this.getGameObjects());
        window.drawWall();
        notifyView();
    }

    public GameObject detect(int x, int y){
        for (GameObject object : objects) {
            if (object.isAtPosition(x, y)) {
                if (object.isObstacle()) {
                    return object;
                }
            }
        }
        return null;
    }

    public void pauseGame (){
        this.pauseState = !this.pauseState;
        if (pauseState){
            escapeMenu = new Window(2);
            this.escapeMenu.attachResumer(this);
        }

    }

    public boolean getPauseState(){
        return pauseState;
    }

    public void playerPos(int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
    }

    public int getPlayerX(){
        int posX = ((Player) objects.get(0)).getPosX();
        return posX;
    }
    public int getPlayerY(){
        int posY = ((Player) objects.get(0)).getPosY();
        return posY;
    }

    public void resume(){
        pauseGame();
    }


}