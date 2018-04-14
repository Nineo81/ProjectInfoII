package Model;

public class Player extends Movable {

    int lifes = 0;
    int globalDammageMultiplier=1;
    int baseDammageMultiplier = 1;
    int weaponDammage= 0;
    int baseDammage;

    public Player(int x, int y,int lifes) {
        super(x, y,0, lifes, 2);
        this.lifes = lifes;
        this.updateDammage();

    }

    public void updateDammage(){
        this.baseDammage=(1+weaponDammage)*globalDammageMultiplier*baseDammageMultiplier;
    }

}