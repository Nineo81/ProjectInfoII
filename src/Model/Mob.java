package Model;

import java.util.ArrayList;

public class Mob extends Movable implements Deletable, Activable {

    private int lifepoints = 0;
    private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();

    public Mob(int x, int y, int lifes){
        super(x, y,0, lifes, 4);
        this.lifepoints = lifes;
    }

    public void activate(){
        if (lifepoints == 1){
            crush();
        }
        else {
            lifepoints--;
            this.color = lifepoints + 2; // pour éviter de retourner au gris
        }
    }

    public void crush(){
        notifyDeletableObserver();
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
