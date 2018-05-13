package Model;

public class Bow extends Loot{

    private int[] stat = {0,0,0,0,0,4};

    public Bow(int x,int y,int color){
        super(x,y,color);
        this.weaponDamage = 3;
        this.lifeBoost =0;
        this.manaBoost =0;
    }

    public int[] getStat(){return this.stat;}

    @Override
    public boolean isRanged() {
        return true;
    }

    @Override
    public boolean isObstacle(){return false;}
}
