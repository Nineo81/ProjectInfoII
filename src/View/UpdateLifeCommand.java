package View;

import Model.Player;

public class UpdateLifeCommand implements Command {
    Player player;
    public UpdateLifeCommand(Player player){
        this.player=player;
    }
    public void execute(){
        player.upLife();
    }
}
