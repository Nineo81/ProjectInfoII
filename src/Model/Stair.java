package Model;

import java.util.ArrayList;

public class Stair extends Block implements Levelable, Activable{
    private ArrayList<LevelableObserver> observers = new ArrayList<LevelableObserver>();

    public Stair(int X, int Y) {
        super(X, Y, 3);
    }

    public void activate(){
        notifyLevelableObserver();
    }

    @Override
    public boolean isObstacle() {
        return false;
    }

    public void attachLevelable(LevelableObserver o){
        observers.add(o);
    }

    public void notifyLevelableObserver(){
        for(LevelableObserver o:observers){
            o.nextLevel();
        }
    }
}
