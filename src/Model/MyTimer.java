package Model;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class MyTimer implements Runnable {

    private Random rand = new Random();
    Game game;
    private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();


    private static final int mob1 = 1;

    public MyTimer(Game game){this.game = game;}
/*
    public void moveRandom(int mobNumber){
        int pos = rand.nextInt(4);
        switch(pos) {
            case 0:
                game.movePlayer(1, 0, mobNumber);
                break;
            case 1:
                game.movePlayer(-1, 0, mobNumber);
                break;
            case 2:
                game.movePlayer(0, 1, mobNumber);
                break;
            case 3:
                game.movePlayer(0, -1, mobNumber);
        }
    }

    /public void moveFollow2(int mobNumber){

        ArrayList<Integer> posPlayer = game.playerPos(0);
        ArrayList<Integer> posMob = game.playerPos(mobNumber);

        if (Math.abs(posMob.get(0)-posPlayer.get(0))<Math.abs(posMob.get(1)-posPlayer.get(1))){
            if (posMob.get(1)<posPlayer.get(1)){
                game.movePlayer(0,1,mobNumber);
            }
            else{
                game.movePlayer(0,-1,mobNumber);
            }
        }
        else{
            if (posMob.get(0)<posPlayer.get(0)){
                game.movePlayer(1,0,mobNumber);
            }
            else{
                game.movePlayer(-1,0,mobNumber);
            }
        }

    }*/


    @Override
    public void run(){
        try{
            int i=0;
            while(true){
                int mobCount = 6;
                for (int mobNumber = 0; mobNumber<mobCount; mobNumber++ ) {
                    if (mobNumber==mobCount-1){
                        if (mobCount!=6){
                            break;
                        }
                    }
                    //game.followPlayer(0, mobNumber + 1);
                    //moveRandom(1);
                }
                Thread.sleep(1000); //se met en attente
            }
        }catch(Exception e){};
    }

    /*@Override
    public void attachMoving(MovingObserver mo) {observers.add(mo);}
    */
}
