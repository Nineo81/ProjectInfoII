package Model;

import java.util.ArrayList;

public class BlockBreakable extends Block implements Deletable, Activable {

    private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
    private int maxLife;
    private int life ;
    public BlockBreakable(int X, int Y, int lifepoints) {
        super(X, Y, 1);
        this.life = lifepoints;
        this.maxLife=lifepoints;
    }
    
    public int activate(int dammage){
        if (life <= dammage){
            crush();
        }
        else {
            life-=dammage;
            //this.color = life + 2; // chiant ici aussi (moins mais quand meme)
        }
        return 0;
    }

    public int getLife(){
        return life;
    }

    public int getMaxLife(){
        return maxLife;
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

    @Override
    public boolean isObstacle() {
        return true;
    }

}
