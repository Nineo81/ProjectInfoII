package Model;

import java.util.ArrayList;

public class Ninja extends Player implements DeletableObserver,Powered{

    int direction = EAST;
    Game game;



    public Ninja(int x, int y,int life, int mana, Game game) {
        super(x, y,life, mana, game);
        this.game=game;

    }



    public void action(ArrayList<GameObject> objects){
        Activable aimedObject = null;
        for(GameObject object : objects){
            if(object.isAtPosition(this.getFrontX(),this.getFrontY())){
                if(object instanceof Activable){
                    aimedObject = (Activable) object;
                    if (mana<=maxMana-10){
                        mana+=10;
                    }
                }
            }
        }
        if(aimedObject != null){
            xp(aimedObject.activate( baseDammage));
        }
    }

    public void action1(ArrayList<GameObject> objects){
        if (mana>=10) {
            int x = 0;
            int y = 0;
            direction = this.getDirection();
            if (direction % 2 == 0) {
                x += 3 - (3 * direction);
            } else {
                y += 3 * direction - 6;
            }
            mana -= 10;
            game.movePlayer(x, y, 0);
        }
    }

    public void action2(ArrayList<GameObject> objects){
        if (mana>=25) {
            int frontX=this.getFrontX();
            int frontY=this.getFrontY();
            Activable aimedObject = null;
            for (GameObject object : objects) {
                if (object.isAtPosition(frontX, frontY)) {
                    if (object instanceof Activable) {
                        aimedObject = (Activable) object;
                        xp(aimedObject.activate(2 * baseDammage));
                        mana -= 25;
                    }
                    return;

                }
            }
            mana-=25;
            game.add(new Projectile(frontX, frontY, this.getDirection(), 2 * baseDammage,this));
        }
    }


    @Override
    public void delete(Deletable d, ArrayList<GameObject> loot) {

    }
}
