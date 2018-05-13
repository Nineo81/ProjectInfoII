package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import Model.*;

import javax.swing.*;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class InventoryPanel  extends JPanel implements ActionListener, Modifier, Runnable {

    private JButton resumeButton;
    private JButton testButton;

    Player player;
    private ArrayList<ModifierObserver> observers = new ArrayList<ModifierObserver>();
    Inventory inventory;
    Thread thread;
    Window menu;
    ImageIcon bow;


    public  InventoryPanel(Window menu) {
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.menu=menu;

        this.setLayout(null);
        thread = new Thread(this);
        thread.start();

        resumeButton = new JButton("Back");
        resumeButton.setBounds(50, 50, 150, 80);
        resumeButton.addActionListener(new ResumeActionListener(menu));
        this.add(resumeButton);

        /*
        testButton = new JButton("Test");
        testButton.setBounds(500, 420,  400, 80);
        testButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                menu.setInventory();
            }
        });

        this.add(testButton);
        */




    }


    public void run(){
        try{
            this.thread.sleep(50);
            menu.setInventory();
        } catch (Exception e){}

        int n =0;
        int x=300;
        int y=100;

        bow = new ImageIcon("images/bow1.png");

        while (n<inventory.getBag().size()){
            JButton itemButton = new JButton(this.bow);
            itemButton.setBounds(x, y,  100, 100);
            itemButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    //
                }
            });

            this.add(itemButton);

            System.out.println("fuck");

            x+=150;
            if (x>1000){
                x=300;
                y+=150;
            }


            n++;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }

    @Override
    public void attachModifier(ModifierObserver po) {
        observers.add(po);
        this.player=((Player) observers.get(0));
        this.inventory=this.player.getInventory();

    }

    @Override
    public void notifyModifierObserver(int[] stats) {
        for (ModifierObserver o:observers){
            //
        }
    }
}
