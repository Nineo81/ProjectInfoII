package Model;

import View.Window;

import java.util.ArrayList;
import java.util.Random;

//import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

//import org.omg.CosNaming.IstringHelper;

public class Game implements DeletableObserver {
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();

    private Window window;
    private int size = 20;
    // private int bombTimer = 3000;
    private int numberOfBreakableBlocks = 40;

    public Game(Window window) {
        this.window = window;

        // Creating one Player at position (1,1)
        objects.add(new Ninja(10, 10, 3));
        Mob mob = new Mob(11, 11, 3);
        mob.attachDeletable(this);
        objects.add(mob);


        // Map building
        for (int i = 0; i < size; i++) {
            objects.add(new BlockUnbreakable(i, 0));
            objects.add(new BlockUnbreakable(0, i));
            objects.add(new BlockUnbreakable(i, size - 1));
            objects.add(new BlockUnbreakable(size - 1, i));
        }
        Random rand = new Random();
        for (int i = 0; i < numberOfBreakableBlocks; i++) {
            int x = rand.nextInt(16) + 2;
            int y = rand.nextInt(16) + 2;
            int lifepoints = rand.nextInt(5) + 1;
            BlockBreakable block = new BlockBreakable(x, y, lifepoints);
            block.attachDeletable(this);
            objects.add(block); //Boris pue vraiment la merde
        }

        window.setGameObjects(this.getGameObjects());
        notifyView();
    }


    public void movePlayer(int x, int y, int playerNumber) {
        Movable player = ((Movable) objects.get(playerNumber));
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


    public void playerPos(int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
        System.out.println(player.getPosX() + ":" + player.getPosY());
        
    }

}