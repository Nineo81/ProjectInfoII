package Model;

import java.util.ArrayList;

public class Ninja extends Player implements DeletableObserver,Powered{

    int direction = EAST;



    public Ninja(int x, int y,int life, int mana) {
        super(x, y,life, mana);
    }



    public void action(ArrayList<GameObject> objects, Game game){
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
            aimedObject.activate( baseDammage);
            game.notifyView();
        }
    }

    public void action1(ArrayList<GameObject> objects, Game game){
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

    public void action2(ArrayList<GameObject> objects, Game game){
        if (mana>=25) {
            int frontX=this.getFrontX();
            int frontY=this.getFrontY();
            Activable aimedObject = null;
            for (GameObject object : objects) {
                if (object.isAtPosition(frontX, frontY)) {
                    if (object instanceof Activable) {
                        aimedObject = (Activable) object;
                        aimedObject.activate(2 * baseDammage);
                        mana -= 25;
                        game.notifyView();
                    }
                    return;

                }
            }
            mana-=25;
            game.add(new Projectile(frontX, frontY, this.getDirection(), objects, game, 2 * baseDammage));
        }
    }

    @Override
    public void delete(Deletable d, ArrayList<GameObject> loot) {

    }
}
