package Model;

public class Mob extends GameObject implements Directable,Runnable {

    int direction = EAST;

    public Mob(int x, int y){
        super(x, y, 2);
    }

    public void move(int X, int Y) {
        this.posX = this.posX + X;
        this.posY = this.posY + Y;
    }

    @Override
    public void run(){
        try{
            while(true){
                Thread.sleep(100); //se met en attente
            }
        }catch(Exception e){};
    }

    @Override
    public boolean isObstacle() {
        return true;
    }

    @Override
    public int getDirection() {
        return direction;
    }
}
