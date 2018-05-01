package Model;

import View.Window;

import java.util.ArrayList;
import java.util.Random;

//import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

//import org.omg.CosNaming.IstringHelper;

public class Game implements DeletableObserver, Runnable {
    private ArrayList<GameObject> objects;

    private Window window;
    private Thread sleepThread;
    private int size = 20;
    // private int bombTimer = 3000;
    private int numberOfMobs=6;
    private boolean running=true;
    private int numberOfBreakableBlocks = 40;

    public Game(Window window) {
        this.window = window;
        this.sleepThread = new Thread(this);
        sleepThread.start();
        objects = new ArrayList<GameObject>();
    }

    public void run(){
        try {
            this.sleepThread.sleep(1000);

            // Creating one Player at position (1,1)
            objects.add(new Ninja(10, 10, 10, 100, this));
        /*Mob mob = new Mob(11, 11, 3);
        mob.attachDeletable(this);
        objects.add(mob); */

            Random rand = new Random();
            //mob spawning
            int n=0;
            while (n<numberOfMobs) {
                int x = rand.nextInt(16) + 2;
                int y = rand.nextInt(16) + 2;
                int lifepoints = rand.nextInt(3) + 3;
                boolean occupied = false;


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
                    Mob mob = new Mob(this, x, y, lifepoints);
                    mob.attachDeletable(this);
                    objects.add(mob);
                }
            }
            // Map building
            for (int i = 0; i < size; i++) {
                objects.add(new BlockUnbreakable(i, 0));
                objects.add(new BlockUnbreakable(0, i));
                objects.add(new BlockUnbreakable(i, size - 1));
                objects.add(new BlockUnbreakable(size - 1, i));
            }
            for (int i = 0; i < numberOfBreakableBlocks; i++) {
                int x = rand.nextInt(16) + 2;
                int y = rand.nextInt(16) + 2;
                int lifepoints = rand.nextInt(5) + 1;
                boolean occupied=false;
                for(GameObject object : objects) {
                    if (object.isAtPosition(x, y)) {
                        occupied = true;
                        break;
                    }
                }
                if (!occupied) {
                    BlockBreakable block = new BlockBreakable(x, y, lifepoints);
                    block.attachDeletable(this);
                    objects.add(block);
                }
                else {i--;}
            }

            Thread t1 = new Thread(new MyTimer(this));
            t1.start();

            window.setGameObjects(this.getGameObjects());

            while (true){
                this.sleepThread.sleep(16,666);
                notifyView();
            }

        } catch (Exception e){System.out.println("Something went wrong");}
    }


    public synchronized void movePlayer(int x, int y, int playerNumber) {
        Movable player = ((Movable) objects.get(playerNumber));
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
    }

    public void followPlayer(int playerTarget, int playerObject){  //Methode pour suivre le player
        Movable player1 = ((Movable) objects.get(playerTarget));
        int TargetX = player1.getPosX();
        int TargetY = player1.getPosY();

        Movable player2 = ((Movable) objects.get(playerObject));
        int ObjX = player2.getPosX();
        int ObjY = player2.getPosY();

        int diffX=TargetX-ObjX;
        int diffY=TargetY-ObjY;

        if (Math.abs(diffX)>Math.abs(diffY)){
            if (diffX>0){
                movePlayer(1, 0, playerObject);
            }
            else{
                movePlayer(-1, 0, playerObject);
            }
        }
        else {
            if (diffY>0){
                movePlayer(0, 1, playerObject);
            }
            else{
                movePlayer(0, -1, playerObject);
            }
        }
    }

    public void action(int playerNumber) {
        Powered player = ((Powered) objects.get(playerNumber));
        player.action(objects);
    }

    public void action1(int playerNumber) {
        Powered player = ((Powered) objects.get(playerNumber));
        player.action1(objects);
    }

    public void action2(int playerNumber) {
        Powered player = ((Powered) objects.get(playerNumber));
        player.action2(objects);
    }

    public void add(GameObject object){
        objects.add(object);
    }

    public int getMobCount(){
        return numberOfMobs;
    }

    public void mobDied(){numberOfMobs--;};


    public void notifyView() {
        window.update(running);
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.objects;
    }


    public void pause(){
        running=false;
        window.openEscapeMenu();
    }

    public void resume(){
        window.closeEscapeMenu();
        running=true;
    }

    @Override
    public synchronized void delete(Deletable ps, ArrayList<GameObject> loot) {
        objects.remove(ps);
        if (loot != null) {
            objects.addAll(loot);
        }
    }


    public void playerPos(int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
        System.out.println(player.getPosX() + ":" + player.getPosY());
        
    }

    public boolean running(){return running;}


}