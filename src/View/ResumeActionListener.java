package View;

import Model.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResumeActionListener implements ActionListener {

    private ArrayList<Game> gameList = null;
    private Game game;

    public  ResumeActionListener(){
        System.out.println("I exist");
        //for(Game game : this.gameList) this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //game.resume();
    }
}