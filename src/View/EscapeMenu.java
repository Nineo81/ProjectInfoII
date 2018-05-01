package View;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EscapeMenu  extends JPanel {

    private JPanel panel;
    private JButton resumeButton;
    private JButton inventoryButton;
    private JButton skillTreeButton;
    private JButton quitButton;

    public  EscapeMenu(){
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.setLayout(null);

        resumeButton = new JButton("Resume");
        resumeButton.setBounds(500, 320,  400, 80);
        resumeButton.addActionListener(new ResumeActionListener());

        inventoryButton = new JButton("Inventory");
        inventoryButton.setBounds(500, 420,  400, 80);

        skillTreeButton = new JButton("Skill Tree");
        skillTreeButton.setBounds(500, 520,  400, 80);

        quitButton = new JButton("Quit to main menu");
        quitButton.setBounds(500, 620,  400, 80);

        this.add(resumeButton);
        this.add(inventoryButton);
        this.add(skillTreeButton);
        this.add(quitButton);




    }


    public void redraw() {
        this.repaint();
    }

}
