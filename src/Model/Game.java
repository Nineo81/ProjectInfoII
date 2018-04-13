package Model;

import View.Window;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.io.FileReader;

//import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

//import org.omg.CosNaming.IstringHelper;

public class Game implements DeletableObserver {
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();

    private Window window;
    private int size = 20;
    // private int bombTimer = 3000;
    private int numberOfMobs=6;
    private int numberOfBreakableBlocks = 40;
    boolean pauseState = false;

    public Game(Window window) {
        this.window = window;

        // Creating one Player at position (1,1)
        objects.add(new Ninja(10, 10, 3));
        /*Mob mob = new Mob(11, 11, 3);
        mob.attachDeletable(this);
        objects.add(mob); */

        Random rand = new Random();
        //mob spawning
        int n=0;
        while (n<numberOfMobs){
            int x = rand.nextInt(16) + 2;
            int y = rand.nextInt(16) + 2;
            int lifepoints = rand.nextInt(3) + 3;
            boolean occupied=false;
            for(GameObject object : objects){
                if(object.isAtPosition(x,y)){
                    occupied=true;
                    break;
                }
                if((Math.abs(10-x)+Math.abs(10-y))<4){
                    occupied=true;
                    break;
                }
            }
            if (!occupied){
                n++;
                Mob mob = new Mob(this,x, y, lifepoints);
                mob.attachDeletable(this);
                objects.add(mob);
            }
        }

        //New Map building
        mapReader(mapCreator());


        /*Map building
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
        notifyView();*/
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

    public int getMobCount(){
        return numberOfMobs;
    }

    public void mobDied(){numberOfMobs--;};


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

    public int[][] matrixGenerator(int num,int dimension){
        int[][] matrix = new int[dimension][dimension];
        for (int i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                matrix[i][j]=num;
            }
        }
        return matrix;
    }

    public int[][] mapCreator(){
        Random rand = new Random();
        int dimension =20,
                maxTunnels = 70,
                maxLength = 10,
                currentRow = rand.nextInt(dimension),
                currentColumn = rand.nextInt(dimension),
                randomLength,
                tunnelLength;
        int[][] map = matrixGenerator(1,dimension),
                directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[] lastDirection = new int[2],
                randomDirection;

        // Start of algorithm
        while(maxTunnels != 0) {

            //Check if the new direction does go back on track and if so, choose a new one
            do {
                randomDirection = directions[rand.nextInt(4)];
            } while (randomDirection[0] == -lastDirection[0] && randomDirection[1] == -lastDirection[1]);

            randomLength = rand.nextInt(maxLength);
            tunnelLength =0;

            //Constructing tunnel
            while(tunnelLength<randomLength){

                //break the loop if it is going out of the map
                if (((currentRow == 1) && (randomDirection[0] == -1)) ||
                        ((currentColumn == 1) && (randomDirection[1] == -1)) ||
                        ((currentRow == dimension - 2) && (randomDirection[0] == 1)) ||
                        ((currentColumn == dimension - 2) && (randomDirection[1] == 1))) {
                    break;
                }
                else{
                    map[currentRow][currentColumn] = 0;
                    currentRow += randomDirection[0];
                    currentColumn += randomDirection[1];
                    tunnelLength++;
                }
            }

            //Prevent a tunnel without length
            if(tunnelLength != 0){
                lastDirection=randomDirection;
                maxTunnels--;
            }
        }

        return map;
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