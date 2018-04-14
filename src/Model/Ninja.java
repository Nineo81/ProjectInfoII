package Model;

import java.util.ArrayList;

public class Ninja extends Player implements DeletableObserver,Powered{

    int life = 20;
    int direction = EAST;



    public Ninja(int x, int y,int life) {
        super(x, y,life);
    }







    public void action(ArrayList<GameObject> objects, Game game){
        Activable aimedObject = null;
        for(GameObject object : objects){
            if(object.isAtPosition(this.getFrontX(),this.getFrontY())){
                if(object instanceof Activable){
                    aimedObject = (Activable) object;
                }
            }
        }
        if(aimedObject != null){
            aimedObject.activate( baseDammage);
            game.notifyView();
        }
    }

    public void action2(ArrayList<GameObject> objects, Game game){
        int frontX=this.getFrontX();
        int frontY=this.getFrontY();
        Activable aimedObject = null;
        for (GameObject object : objects) {
            if (object.isAtPosition(frontX, frontY)) {
                if (object instanceof Activable) {
                    aimedObject = (Activable) object;
                    aimedObject.activate(2*baseDammage);
                    game.notifyView();
                }
                return;

            }
        }
        game.add(new Projectile(frontX,frontY,this.getDirection(),objects,game,2*baseDammage));
        return;
    }

    @Override
    public void delete(Deletable d, ArrayList<GameObject> loot) {

    }
}
