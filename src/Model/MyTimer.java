package Model;

import java.util.Random;

public class MyTimer implements Runnable {

    private Game game;
    private Random rand = new Random();


    private static final int player2 = 1;

    public MyTimer(Game game){
        this.game = game;
    }

    @Override
    public void run(){
        try{
            while(true){
                int pos = rand.nextInt(4);
                switch(pos) {
                    case 0:
                        game.movePlayer(1, 0, player2);
                        break;
                    case 1:
                        game.movePlayer(-1, 0, player2);
                        break;
                    case 2:
                        game.movePlayer(0, 1, player2);
                        break;
                    case 3:
                        game.movePlayer(0, -1, player2);
                }
                Thread.sleep(1000); //se met en attente
            }
        }catch(Exception e){};
    }
}
