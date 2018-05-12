package Model;

import java.util.ArrayList;
import java.util.Vector;

public class Inventory implements Modifier{
    private Vector<GameObject> bag = new Vector<>();
    private ArrayList<ModifierObserver> observers = new ArrayList<ModifierObserver>();

    public void addItem(GameObject object){
        bag.add(object);
        System.out.println(object.getClass().getName());
    }

    @Override
    public void attachModifier(ModifierObserver po) {
        observers.add(po);
    }

    @Override
    public void notifyModifierObserver() {

    }
}
