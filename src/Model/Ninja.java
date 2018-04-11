package Model;

import java.util.ArrayList;

public class Ninja extends Player {

    int life = 1;
    int direction = EAST;

    public Ninja(int x, int y,int life) {
        super(x, y,life);
    }


    @Override
    public void delete(Deletable d, ArrayList<GameObject> loot) {

    }
}
