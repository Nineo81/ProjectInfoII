package Model;

import java.util.ArrayList;
import java.util.Observer;

public class Mob extends MovingObject implements Deletable, Activable, Moving, Runnable{

    private int maxLife;
    private int life ;
    private Thread thread;
    private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
    private ArrayList<MovingObserver> observers2 = new ArrayList<MovingObserver>();
    int dammage;
    int px;
    int py;

    public Mob(int x, int y, int life){
        super(x, y, life, 4);
        this.maxLife=life;
        this.life = life;
        this.dammage=1;
        this.thread = new Thread(this);
        thread.start();
    }

    public int activate(int dammage){
        int xp=0;
        if (life <= dammage){
            xp=10;
            crush();
        }
        else {
            life-=dammage;
        }
        return xp;
    }

    public void crush(){
        notifyDeletableObserver();
    }

    public int getLife(){
        return life;
    }

    public int getMaxLife(){
        return maxLife;
    }



    // //////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void attachDeletable(DeletableObserver po) {
        observers.add(po);
    }

    @Override
    public void notifyDeletableObserver() {
        int i = 0;
        for (DeletableObserver o : observers) {
            i++;
            Bow bow = new Bow(this.posX,this.posY,1);
            bow.attachDeletable(o);
            o.delete(this, bow);
        }
    }

    @Override
    public void run(){
        try{thread.sleep(1000);} catch (Exception e){}
        while (true) {
            if (!askPauseState()) {
                int x; //x if diffX>diffY
                int y; //x if diffx<diffY
                px = askPX();
                py = askPY();
                this.previousPosX = this.posX;
                this.previousPosY = this.posY;
                int diffX = px - this.posX;
                int diffY = py - this.posY;
                GameObject obstacle;

                if (diffX > 0) {
                    x = 1;
                } else {
                    x = -1;
                }

                if (diffY > 0) {
                    y = 1;
                } else {
                    y = -1;
                }

                if (Math.abs(diffX) > Math.abs(diffY)) {
                    obstacle = (observers2.get(0)).detect(this.posX + x, this.posY);
                    if (obstacle == null) {
                        this.posX += x;
                        y = 0;
                    } else if (obstacle instanceof Player) {
                        ((Player) obstacle).activate(this.dammage);
                        y = 0;
                    } else {
                        obstacle = (observers2.get(0)).detect(this.posX, this.posY + y);
                        if (obstacle == null) {
                            this.posY += y;
                            x = 0;
                        }
                    }

                } else {
                    obstacle = (observers2.get(0)).detect(this.posX, this.posY + y);
                    if (obstacle == null) {
                        this.posY += y;
                        x = 0;
                    } else if (obstacle instanceof Player) {
                        ((Player) obstacle).activate(this.dammage);
                        x = 0;
                    } else {
                        obstacle = (observers2.get(0)).detect(this.posX + x, this.posY);
                        if (obstacle == null) {
                            this.posX += x;
                            y = 0;
                        }
                    }

                }

                if (x == 0) {
                    if (y == 1) {
                        direction = SOUTH;
                    } else {
                        direction = NORTH;
                    }
                } else {
                    if (x == 1) {
                        direction = EAST;
                    } else {
                        direction = WEST;
                    }
                }
            }
            refresh();
            try{thread.sleep(1000);} catch (Exception e){}
        }

    }

    @Override
    public void refresh() {
        (observers2.get(0)).notifyView();
    }

    @Override
    public void attachMoving(MovingObserver mo){observers2.add(mo);}

    @Override
    public GameObject askMovingObserver(int x,int y){
        return (observers2.get(0)).detect(x,y);
    }

    @Override
    public int askPX(){
        return (observers2.get(0)).getPlayerX();
    }
    @Override
    public int askPY(){
        return (observers2.get(0)).getPlayerY();
    }

    @Override
    public boolean askPauseState(){
        return (observers2.get(0)).getPauseState();
    }

}