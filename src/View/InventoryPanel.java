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

public class InventoryPanel  extends JPanel implements ActionListener, Modifier {

    private JButton resumeButton;
    private JButton testButton;

    Player player;
    private ArrayList<ModifierObserver> observers = new ArrayList<ModifierObserver>();
    Inventory inventory;


    public  InventoryPanel(Window menu) {
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.setLayout(null);

        resumeButton = new JButton("Back");
        resumeButton.setBounds(50, 50, 150, 80);
        resumeButton.addActionListener(new ResumeActionListener(menu));
        this.add(resumeButton);

        testButton = new JButton("Test");
        testButton.setBounds(500, 420,  400, 80);
        testButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                menu.setInventory();
            }
        });

        this.add(testButton);





    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }

    @Override
    public void attachModifier(ModifierObserver po) {
        observers.add(po);
        this.inventory=((Player) observers.get(0)).getInventory();

        for (GameObject o:this.inventory.getBag()){
            System.out.print("1 ");
            System.out.println(o.getClass().getName());
        }
    }

    @Override
    public void notifyModifierObserver(int[] stats) {
        for (ModifierObserver o:observers){
            //
        }
    }
}
