package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import Model.Modifier;
import Model.ModifierObserver;
import Model.Player;
import Model.SkillTree;


import javax.swing.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SkillTreePanel extends JPanel implements ActionListener, Modifier, Runnable {


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

    private ArrayList<ModifierObserver> observers = new ArrayList<ModifierObserver>();

    Thread thread;
    Window menu;
    SkillTree skillTree;

    int Life;
    int MaxLife;
    int Mana;
    int MaxMana;
    int Level;
    int usableLevel;

    public SkillTreePanel(Window menu){
        //this.player=player;
        this.menu=menu;
        this.setFocusable(true);
        this.requestFocusInWindow();
        int maxLife=3;
        //RemoteUpdate control = new RemoteUpdate();
        //Command UpdateLifeCommand = new UpdateLifeCommand(player);
        thread = new Thread(this);
        thread.start();




        this.setLayout(null);




    }

    public void run() {
        try {
            this.thread.sleep(50);
            menu.setSkillTree();
        } catch (Exception e) {
        }

        MaxLife=player.getMaxLife();
        MaxMana=player.getMaxMana();

        resumeButton = new JButton("Back");
        resumeButton.setBounds(50, 50,  150, 80);
        resumeButton.addActionListener(new ResumeActionListener(menu));
        this.add(resumeButton);

        JTextArea lifeText = new JTextArea();
        lifeText.setText("MAX HEALTH : " + String.valueOf(player.getMaxLife()));

        lifeText.setBounds(650,70,120,40);
        this.add(lifeText);

        life = new JButton( "HEALTH");
        life.setBounds(300, 50, 300, 80);
        life.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usableLevel>0) {
                    player.getSkillTree().upLife();
                    usableLevel--;
                    player.useLevel();
                    lifeText.setText("MAX HEALTH : " + String.valueOf(MaxLife+20));
                }

            }
        });
        this.add(life);

        JLabel manaText = new JLabel();
        manaText.setText("MAX MANA : " + String.valueOf(MaxMana));
        manaText.setBounds(650,170,100,40);
        this.add(manaText);

        mana = new JButton( "MANA");
        mana.setBounds(300, 150, 300, 80);
        mana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setSkillTree();
                if (usableLevel>0) {
                    player.getSkillTree().upMana();
                    usableLevel--;
                    player.useLevel();
                    manaText.setText("MAX MANA : " + String.valueOf(MaxMana+30));
                }

            }
        });

        this.add(mana);


    }


        @Override
    public void actionPerformed(ActionEvent e) {
        //
    }

    @Override
    public void attachModifier(ModifierObserver po) {
        observers.add(po);
        this.player=((Player) observers.get(0));
        this.skillTree=player.getSkillTree();
        usableLevel=player.getUsableLevel();


    }

    @Override
    public void notifyModifierObserver(int[] stats) {
        for (ModifierObserver o : observers) {
            //
        }
    }
}
