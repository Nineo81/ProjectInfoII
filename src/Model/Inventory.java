package Model;

import java.util.ArrayList;
import java.util.Vector;

public class Inventory implements Modifier{
    private Vector<GameObject> bag = new Vector<>();
    private ArrayList<ModifierObserver> observers = new ArrayList<ModifierObserver>();

    public void addItem(GameObject object){
        bag.add(object);
        System.out.print(object.getClass().getName());
        System.out.println(" grabbed!");
    }

    public Vector<GameObject> getBag(){
        return bag;
    }

    public void delete(GameObject object){
        bag.remove(object);
    }

    @Override
    public void attachModifier(ModifierObserver po) {
        observers.add(po);
    }

    @Override
    public void notifyModifierObserver(int[] stats) {
        for (ModifierObserver o:observers){
            o.statsUpdate(stats);
        }
    }
}
