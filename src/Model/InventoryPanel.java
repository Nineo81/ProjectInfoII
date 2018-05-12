package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import Model.Player;

import javax.swing.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class InventoryPanel  extends JPanel implements ActionListener {

    private JButton resumeButton;

    Player player;



    public  InventoryPanel(Window menu) {
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.setLayout(null);

        resumeButton = new JButton("Back");
        resumeButton.setBounds(50, 50, 150, 80);
        resumeButton.addActionListener(new ResumeActionListener(menu));
        this.add(resumeButton);





    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }
}
