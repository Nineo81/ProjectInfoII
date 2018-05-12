package Model;

public abstract class GameObject {
    protected int posX;
    protected int posY;
    protected int previousPosX;
    protected int previousPosY;
    protected int color;

    public GameObject(int X, int Y, int color) {
        this.posX = X;
        this.posY = Y;
        this.color = color;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getPreviousPosX(){return this.previousPosX;}

    public int getPreviousPosY(){return this.previousPosY;}

    public int getColor() {
        return this.color;
    }

    public boolean isAtPosition(int x, int y) {
        return this.posX == x && this.posY == y;
    }

    public abstract boolean isObstacle();
}
