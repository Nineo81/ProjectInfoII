package Model;

public interface Levelable {
    void attachLevelable(LevelableObserver o);

    void notifyLevelableObserver();
}
