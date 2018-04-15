package Model;

import java.util.ArrayList;

public class Mob extends Movable implements Deletable, Activable {

    private int maxLife;
    private int life ;
    Game game;
    private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();

    public Mob(Game game,int x, int y, int life){
        super(x, y, life, 4);
        this.maxLife=life;
        this.life = life;
        this.game=game;
    }

    public void activate(int dammage){
        if (life <= dammage){
            crush();
        }
        else {
            life-=dammage;
            //this.color = life + 2; // chiant
        }
    }

    public void crush(){
        notifyDeletableObserver();
        game.mobDied();
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


}
