package Model;

public interface Modifier {
    void attachModifier(ModifierObserver po);

    void notifyModifierObserver(int[] stats);
}
