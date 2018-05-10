package Model;

public interface Moving {
    void attachMoving(MovingObserver mo);

    void refresh();

    GameObject askMovingObserver(int x, int y);

    int askPX();
    int askPY();
}
