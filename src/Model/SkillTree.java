package Model;

import java.util.ArrayList;

public class SkillTree implements Modifier {
    private ArrayList<ModifierObserver> observers = new ArrayList<ModifierObserver>();

    public void upLife(){
        notifyModifierObserver(new int[]{0,1,0,0,0,0});
    }

    public void upMana(){
        notifyModifierObserver(new int[]{0,0,0,1,0,0});
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