package Model;

public class Potion extends Loot {
    private int[] stat = {0,0,0,0,0,0};

    public Potion(int x,int y,int color,int type,int value){
        super(x,y,color);
        this.weaponDamage = 3;
        this.lifeBoost =0;
        this.manaBoost =0;
        this.stat[type]=value;
    }

    public int[] getStat(){return this.stat;}

    @Override
    public boolean isRanged() {
        return true;
    }

    @Override
    public boolean isObstacle(){return false;}
}
