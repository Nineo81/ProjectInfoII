package View;

import Model.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResumeActionListener implements ActionListener {

    Window menu;

    public  ResumeActionListener(Window menu){
        this.menu=menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.menu.resumeGame();

    }


}
