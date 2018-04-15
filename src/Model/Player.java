package Model;

public class Player extends Movable implements Activable {

    int maxLife;
    int life;
    int globalDammageMultiplier=1;
    int baseDammageMultiplier = 1;
    int weaponDammage= 0;
    int baseDammage;

    public Player(int x, int y,int life) {
        super(x, y, life, 2);
        this.maxLife=life;
        this.life = life;
        this.updateDammage();

    }

    public void updateDammage(){
        this.baseDammage=(1+weaponDammage)*globalDammageMultiplier*baseDammageMultiplier;
    }

    public void activate(int dammage){
        if (life <= dammage){
            lose();
        }
        else {
            life-=dammage;
            //this.color = lifepoints + 2; // chiant
        }
    }

    private void lose(){
        super.color=4;
        System.out.println("You diededed");
    }

    public int getLife(){
        return life;
    }

    public int getMaxLife(){
        return maxLife;
    }

}