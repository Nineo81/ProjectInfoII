package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import Model.Player;
import Model.SkillTree;


import javax.swing.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SkillTreePanel extends JPanel implements ActionListener {


    private JButton resumeButton;
    private JButton firstAttack;
    private JButton secondAttack;
    private JButton thirdAttack;
    private JButton life;
    private JButton attack;
    private JButton lifeRegen;
    private JButton manaRegen;
    private JButton mana;
    private Player player;


    public SkillTreePanel(Window menu,Player player){
        this.player=player;
        this.setFocusable(true);
        this.requestFocusInWindow();
        int maxLife=3;
        RemoteUpdate control = new RemoteUpdate();
        Command UpdateLifeCommand = new UpdateLifeCommand(player);


        this.setLayout(null);

        resumeButton = new JButton("Back");
        resumeButton.setBounds(50, 50,  150, 80);
        resumeButton.addActionListener(new ResumeActionListener(menu));
        this.add(resumeButton);

        JTextArea lifeText = new JTextArea();
        lifeText.setText("MAX HEALTH : " + String.valueOf(5));

        lifeText.setBounds(650,70,120,40);
        this.add(lifeText);

        life = new JButton( "HEALTH");
        life.setBounds(300, 50, 300, 80);
        life.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.setCommand(UpdateLifeCommand);
                control.pressButton();

            }
        });
        this.add(life);

        mana = new JButton( "MANA");
        mana.setBounds(300, 150, 300, 80);
        mana.addActionListener(e -> player.upLife());

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
