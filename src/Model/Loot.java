package Model;

import java.util.ArrayList;

public abstract class Loot extends GameObject implements Deletable{

    protected int weaponDamage;
    protected int lifeBoost;
    protected int manaBoost;
    private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();

    public Loot(int x,int y,int color){
        super(x,y,color);
    }

    public abstract boolean isRanged();

    public void crush(){notifyDeletableObserver();}

    public void pickUp(){
        crush();
    }

    @Override
    public boolean isObstacle(){return true;}

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