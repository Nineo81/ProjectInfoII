package Model;

import java.util.ArrayList;

public class Projectile extends Movable implements Deletable,  Directable, Runnable, Moving {


    private int direction;
    private Thread thread;
    private int dammage;
    private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
    private ArrayList<MovingObserver> observers2 = new ArrayList<MovingObserver>();
    Powered launcher;

    public Projectile(int x, int y, int direction, int dammage, Powered launcher) {
        super(x, y, 1, 5);
        this.direction=direction;
        this.dammage=dammage;
        this.launcher=launcher;
        this.thread = new Thread(this);
        thread.start();
    }


    @Override
    public synchronized void run() {
        try{thread.sleep(200);} catch (Exception e){}
        while (true) {
            if (!askPauseState()) {
                int frontX = this.getFrontX();
                int frontY = this.getFrontY();
                GameObject target = askMovingObserver(frontX, frontY);
                if (target instanceof Activable) {
                    if (launcher instanceof Player) {
                        int xp = ((Activable) target).activate(dammage);
                        ((Player) launcher).xp(xp);
                    } else {
                        ((Activable) target).activate(dammage);
                    }

                }
                if (target == null) {
                    this.move();
                    refresh();
                    this.sleep(200);
                } else {
                    this.sleep(200);
                    this.crush();
                    refresh();
                    return;
                }
            }
        }
    }

    private void move() {
        int x=0;
        int y=0;
        direction=this.getDirection();
        if (direction % 2 == 0) x += 1 - direction;
        else y += direction - 2;
        posX +=x;
        posY+=y;
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
        return (this.posX + delta);
    }

    public int getFrontY() {
        int delta = 0;
        if (direction % 2 != 0){
            delta += direction - 2;
        }
        return (this.posY + delta);
    }



    @Override
    public void attachDeletable(DeletableObserver po) {
        observers.add(po);
    }

    @Override
    public void notifyDeletableObserver() {
        int i = 0;
        for (DeletableObserver o : observers) {
            i++;
            o.delete(this, null);
        }
    }

    @Override
    public GameObject askMovingObserver(int x,int y){
        return (observers2.get(0)).detect(x,y);
    }

    @Override
    public void refresh() {
        (observers2.get(0)).notifyView();
    }

    @Override
    public void attachMoving(MovingObserver mo){observers2.add(mo);}

    @Override
    public int askPX(){return (observers2.get(0)).getPlayerX();}
    @Override
    public int askPY(){return (observers2.get(0)).getPlayerY();}

    @Override
    public boolean askPauseState(){
        return (observers2.get(0)).getPauseState();
    }

}