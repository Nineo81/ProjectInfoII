package Model;

public interface Sizeable {
    void attachSizeable(SizeableObserver o);

    void notifySizeableObserver();
}
