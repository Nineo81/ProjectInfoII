package Model;

public class Player extends Movable {

    int lifes = 0;

    public Player(int x, int y,int lifes) {
        super(x, y,0, lifes, 2);
        this.lifes = lifes;
    }
}