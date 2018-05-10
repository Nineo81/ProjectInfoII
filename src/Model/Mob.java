package Model;

import java.util.ArrayList;
import java.util.Observer;

public class Mob extends Movable implements Deletable, Activable, Moving, Runnable{

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
            o.delete(this, null);
        }
    }

    @Override
    public void run(){
        try{thread.sleep(1000);} catch (Exception e){}
        while (true) {
            int x; //x if diffX>diffY
            int y; //x if diffx<diffY
            px=askPX();
            py=askPY();
            int diffX=px-this.posX;
            int diffY=py-this.posY;
            System.out.print(px);
            System.out.print(" ; ");
            System.out.println(py);
            GameObject obstacle;

            if (diffX>0){ x=1; }
            else{ x=-1; }

            if (diffY>0){ y=1; }
            else{ y=-1; }

            if (Math.abs(diffX)>Math.abs(diffY)){
                obstacle= (observers2.get(0)).detect(this.posX+x,this.posY);
                if (obstacle==null){
                        this.posX+=x;
                }
                else if(obstacle instanceof Player){
                    ((Player) obstacle).activate(this.dammage);
                }
                else{
                    obstacle= (observers2.get(0)).detect(this.posX,this.posY+y);
                    if (obstacle==null){
                        this.posY+=y;
                    }
                }

            }
            else{
                obstacle= (observers2.get(0)).detect(this.posX,this.posY+y);
                if (obstacle==null){
                    this.posY+=y;
                }
                else if(obstacle instanceof Player){
                    ((Player) obstacle).activate(this.dammage);
                }
                else{
                    obstacle= (observers2.get(0)).detect(this.posX+x,this.posY);
                    if (obstacle==null){
                        this.posX+=x;
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

}
