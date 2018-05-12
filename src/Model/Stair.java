package Model;

import java.util.ArrayList;

public class Stair extends Block implements Levelable{
    private ArrayList<LevelableObserver> observers = new ArrayList<LevelableObserver>();

    public Stair(int X, int Y) {
        super(X, Y, 3);
    }

    public int takeIt(){
        notifyLevelableObserver();
        return 0;
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
