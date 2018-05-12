package Model;


public interface MovingObserver {
    GameObject detect(int x, int y);
    int getPlayerX();
    int getPlayerY();
    void notifyView();

    boolean getPauseState();
}
