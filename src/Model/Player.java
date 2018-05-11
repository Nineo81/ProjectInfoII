package Model;

public class Player extends MovingObject implements Activable, Runnable {

    private int maxLife;
    private int life;
    protected int mana;
    protected int maxMana;
    private int globalDammageMultiplier=1;
    private int baseDammageMultiplier = 1;
    private int weaponDammage= 0;
    int baseDammage;
    private int xp=0;
    private int level=1;
    private int levelXp=100;
    private Thread thread;
    Game game;

    public Player(int x, int y,int life, int mana, Game game) {
        super(x, y, life, 2);
        this.maxLife=life;
        this.life = life;
        this.maxMana=mana;
        this.mana=mana;
        this.updateDammage();
        this.game=game;
        this.thread = new Thread(this);
        thread.start();
    }

    public void updateDammage(){
        this.baseDammage=(1+weaponDammage)*globalDammageMultiplier*baseDammageMultiplier;
    }

    public int activate(int dammage){
        if (life <= dammage){
            life=0;
            lose();
        }
        else {
            life-=dammage;
            //this.color = lifepoints + 2; // chiant
        }
        return 0;
    }

    private void lose(){
        super.color=4;
        System.out.println("You diededed");
    }


    public void xp(int xp){
        this.xp+=xp;
        if (this.xp>=levelXp){
            this.xp-=levelXp;
            level++;
            levelXp=levelXp*4;
        }
    }


    public void run(){
        try{
            while (true) {
                if (mana < maxMana) {
                    mana += 1;
                }
                this.thread.sleep(150);
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

    public int getXp(){
        return xp;
    }

    public int getLevel(){ return level; }

    public int getLevelXp(){ return levelXp; }

}