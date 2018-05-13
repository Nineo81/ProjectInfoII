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

public class SkillTreePanel  extends JPanel implements ActionListener {

    private JButton resumeButton;
    private JButton firstAttack;
    private JButton secondAttack;
    private JButton thirdAttack;
    private JButton life;
    private JButton attack;
    private JButton lifeRegen;
    private JButton manaRegen;
    private JButton mana;

    Player player;

    int remainigLevels=3;



    public  SkillTreePanel(Window menu){
        this.setFocusable(true);
        this.requestFocusInWindow();
        int maxLife=3;
        int Life=5;

        this.setLayout(null);

        resumeButton = new JButton("Back");
        resumeButton.setBounds(50, 50,  150, 80);
        resumeButton.addActionListener(new ResumeActionListener(menu));
        this.add(resumeButton);

        JTextArea lifeText = new JTextArea();
        lifeText.setText("MAX HEALTH : " + String.valueOf(Life));
        lifeText.setBounds(650,70,120,40);
        this.add(lifeText);

        life = new JButton( "HEALTH");
        life.setBounds(300, 50, 300, 80);
        life.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (remainigLevels>0){
                    //player.addmaxlife
                    //player.useLevel
                    int n=Life;
                    int Life=n-1;
                    remainigLevels--;
                    lifeText.setText("MAX HEALTH : " + String.valueOf((Life)));
                }
            }
        });
        this.add(life);

        mana = new JButton( "MANA");
        mana.setBounds(300, 150, 300, 80);
        mana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (remainigLevels>0){
                    //player.addmaxmana
                    //player.useLevel
                    remainigLevels--;
                }
            }
        });
        this.add(mana);

        JLabel manaText = new JLabel();
        manaText.setText("MAX MANA : " + String.valueOf(3));
        manaText.setBounds(650,170,100,40);
        this.add(manaText);








    }







    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }
}
