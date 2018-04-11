package Model;

import java.util.ArrayList;

public class Projectile extends GameObject implements Deletable,  Directable, Runnable {


    private int direction;
    private Thread thread;
    private ArrayList<GameObject> objects;
    private Activable aimedObject = null;
    private Game game;

    public Projectile(int x, int y, int direction, ArrayList<GameObject> objects,Game game) {
        super(x, y, 4);
        this.direction=direction;
        this.objects = objects;
        this.game=game;
        this.thread = new Thread(this);
        thread.start();
        game.notifyView();


    }


    @Override
    public void run() {
        while (true) {
            for (GameObject object : objects) {
                if (object.isAtPosition(this.getFrontX(), this.getFrontY())) {
                    if (object instanceof Activable) {
                        aimedObject = (Activable) object;
                        System.out.println("i have something");
                    } else {
                        this.sleep(200);
                        this.crush();
                        System.out.println("i hit a wall");
                        return;
                    }
                }
            }
            if (aimedObject != null) {
                System.out.println("i'm kicking it");
                aimedObject.activate();
                game.notifyView();
                this.sleep(200);
                this.crush();
                game.notifyView();
                return;
            } else {
                this.sleep(300);
                System.out.println("i'll move now...");
                this.move();
                game.notifyView();
            }
        }
    }

    private void move() {
        this.posX = getFrontX();
        this.posY = getFrontY();
    }

    private void crush(){
        notifyDeletableObserver();
    }


    private void sleep (int sleepTime){
        try {
            thread.sleep(sleepTime);
            //return;
        } catch (Exception e){System.out.println("something went wrong!");}

    }

    // //////////////////////////////////////////////////////////////////////////////////////


    @Override
    public boolean isObstacle() {
        return false;
    }

    @Override
    public int getDirection() {
        return direction;
    }

    public int getFrontX() {
        int delta = 0;
        if (direction % 2 == 0){
            delta += 1 - direction;
        }
        return this.posX + delta;
    }

    public int getFrontY() {
        int delta = 0;
        if (direction % 2 != 0){
            delta += direction - 2;
        }
        return this.posY + delta;
    }



    @Override
    public void attachDeletable(DeletableObserver po) {

    }

    @Override
    public void notifyDeletableObserver() {
        game.delete(this, null);
    }

}