package Model;

import java.util.ArrayList;

public class SkillTree implements Modifier {
    private ArrayList<ModifierObserver> observers = new ArrayList<ModifierObserver>();

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
