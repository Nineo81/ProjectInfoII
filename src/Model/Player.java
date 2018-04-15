package Model;

public class Player extends Movable implements Activable, Runnable {

    int maxLife;
    int life;
    int mana;
    int maxMana;
    int globalDammageMultiplier=1;
    int baseDammageMultiplier = 1;
    int weaponDammage= 0;
    int baseDammage;
    private Thread thread;

    public Player(int x, int y,int life, int mana) {
        super(x, y, life, 2);
        this.maxLife=life;
        this.life = life;
        this.maxMana=mana;
        this.mana=mana;
        this.updateDammage();
        this.thread = new Thread(this);
        thread.start();
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

    public void run(){
        try{
            while (true) {
                if (mana < maxMana) {
                    mana += 1;
                }
                this.thread.sleep(100);
            }
        } catch (Exception e){System.out.println("Something went wrong");}
    }

    public int getLife(){
        return life;
    }

    public int getMaxLife(){
        return maxLife;
    }

    public int getMana(){
        return mana;
    }

    public int getMaxMana(){
        return maxMana;
    }

}