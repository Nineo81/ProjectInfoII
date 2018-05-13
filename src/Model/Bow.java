package Model;

import java.util.Random;

public class Bow extends Loot{

    Random random= new Random();

    private int[] stat = {0,0,0,0,0,1+random.nextInt(5)};

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
