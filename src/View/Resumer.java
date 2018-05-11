package View;

import Model.ResumerObserver;

public interface Resumer {
    void attachResumer(ResumerObserver ro);
    void resumeGame();
    void close();
}
