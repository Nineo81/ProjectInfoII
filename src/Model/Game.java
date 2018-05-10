package Model;

import View.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileReader;

//import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

//import org.omg.CosNaming.IstringHelper;

public class Game implements DeletableObserver, LevelableObserver {
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();

    private Window window;
    private Thread sleepThread;
    private int size = 20;
    // private int bombTimer = 3000;
    private int numberOfMobs=6;
    private boolean running=true;
    boolean pauseState = false;

    public Game(Window window) {
        this.window = window;
        nextLevel();
        Thread t1 = new Thread(new MyTimer(this));
        t1.start();
    }


    public void movePlayer(int x, int y, int playerNumber) {
        MovingObject player = ((MovingObject) objects.get(playerNumber));
        int nextX = player.getPosX() + x;
        int nextY = player.getPosY() + y;

        boolean obstacle = false;
        for (GameObject object : objects) {
            if (object.isAtPosition(nextX, nextY)) {
                obstacle = object.isObstacle();
            }
            if (obstacle == true) {
                break;
            }
        }
        player.rotate(x, y);
        if (obstacle == false) {
            player.move(x, y);
        }
        notifyView();
    }

    public void followPlayer(int playerTarget, int playerObject){  //Methode pour suivre le player
        MovingObject player1 = ((MovingObject) objects.get(playerTarget));
        int TargetX = player1.getPosX();
        int TargetY = player1.getPosY();

        MovingObject player2 = ((MovingObject) objects.get(playerObject));
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
        Player player = ((Player) objects.get(playerNumber));
        Activable aimedObject = null;
		for(GameObject object : objects){
			if(object.isAtPosition(player.getFrontX(),player.getFrontY())){
			    if(object instanceof Activable){
			        aimedObject = (Activable) object;
			    }
			}
		}
		if(aimedObject != null){
		    aimedObject.activate();
            notifyView();
		}
        
    }

    public void action2(int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
        int frontX=player.getFrontX();
        int frontY=player.getFrontY();
        Activable aimedObject = null;
        for (GameObject object : objects) {
            if (object.isAtPosition(frontX, frontY)) {
                if (object instanceof Activable) {
                    aimedObject = (Activable) object;
                    aimedObject.activate();
                    notifyView();
                }
                return;

            }
        }
        this.objects.add(new Projectile(frontX,frontY,player.getDirection(),objects,this));
        return;
    }

    public void notifyView() {
        window.update();
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.objects;
    }

    @Override
    synchronized public void delete(Deletable ps, ArrayList<GameObject> loot) {
        objects.remove(ps);
        if (loot != null) {
            objects.addAll(loot);
        }
        notifyView();
    }

    public void nextLevel(){
        objects = new ArrayList<GameObject>();

        // Creating one Player at position (1,1)
        objects.add(new Ninja(10, 10, 3));

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
                Mob mob = new Mob( x, y, lifepoints);
                mob.attachDeletable(this);
                objects.add(mob);
            }
        }

        //New Map building
        Level level = new Level();
        mapReader(level.getLevelMap());
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
        notifyView();
    }

    public void pauseGame (){
        this.pauseState = !this.pauseState;
    }

    public boolean getPauseState(){
        return pauseState;
    }

    public void playerPos(int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
        System.out.println(player.getPosX() + ":" + player.getPosY());

    }

}