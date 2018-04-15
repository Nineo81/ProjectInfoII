package Model;

import java.util.ArrayList;

public class Projectile extends Movable implements Deletable,  Directable, Runnable {


    private int direction;
    private Thread thread;
    private ArrayList<GameObject> objects;
    private Activable aimedObject = null;
    private Game game;
    private int dammage;
    private int projectileNumber;

    public Projectile(int x, int y, int direction, ArrayList<GameObject> objects,Game game, int dammage) {
        super(x, y, 1, 5);
        this.direction=direction;
        this.objects = objects;
        this.game=game;
        this.dammage=dammage;
        this.thread = new Thread(this);
        thread.start();
        game.notifyView();


    }


    @Override
    public void run() {
        try{thread.sleep(200);} catch (Exception e){};
        while (true) {
            for (GameObject object : objects) {
                if (object.isAtPosition(this.getFrontX(), this.getFrontY())) {
                    if (object instanceof Activable) {
                        aimedObject = (Activable) object;
                    } else {
                        this.sleep(200);
                        this.crush();
                        return;
                    }
                }
                if (object==this){
                    projectileNumber=objects.indexOf(object);
                }
            }
            if (aimedObject != null) {
                aimedObject.activate(dammage);
                game.notifyView();
                this.sleep(200);
                this.crush();
                game.notifyView();
                return;
            } else {
                this.move();
                game.notifyView();
                this.sleep(200);
            }
        }
    }

    private void move() {
        int x=0;
        int y=0;
        direction=this.getDirection();
        if (direction % 2 == 0) x += 1 - direction;
        else y += direction - 2;
        game.movePlayer(x,y,projectileNumber);
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