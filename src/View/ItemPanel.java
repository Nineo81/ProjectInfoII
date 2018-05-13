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

public class ItemPanel extends JPanel implements ActionListener, Modifier{


    private JButton imageButton;
    private JButton useButton;
    private JButton deleteButton;
    private JLabel text;
    Player player;
    GameObject item;

    private ArrayList<ModifierObserver> observers = new ArrayList<ModifierObserver>();

    Thread thread;
    Window menu;
    ImageIcon image;


    public ItemPanel(Window menu, GameObject item){
        //this.player=player;
        this.menu=menu;
        this.item=item;
        this.setFocusable(true);
        this.requestFocusInWindow();
        //RemoteUpdate control = new RemoteUpdate();
        //Command UpdateLifeCommand = new UpdateLifeCommand(player);
        this.setLayout(null);

        if (item instanceof  Bow) {
            image = new ImageIcon("images/bow1.png");
        }
        else {image = new ImageIcon("images/potion.png");}
        imageButton = new JButton(image);
        imageButton.setBounds(50, 50, 200, 200);
        imageButton.addActionListener(new ResumeActionListener(menu));
        this.add(imageButton);


        useButton = new JButton("USE");
        this.useButton.setBounds(300, 50, 150, 50);
        this.useButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (item instanceof Bow){
                player.statsUpdate(((Bow) item).getStat());
                }
                else {
                    player.statsUpdate(((Potion) item).getStat());
                    player.getInventory().getBag().remove(item);
                }
                menu.close();

            }
        });
        this.add(this.useButton);

        deleteButton = new JButton("DELETE");
        deleteButton.setBounds(300, 200, 150, 50);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                player.getInventory().getBag().remove(item);
                closeMenu();
            }
        });
        this.add(deleteButton);

        if (item instanceof Bow){
            text= new JLabel("Dammage : "+String.valueOf(((Bow) item).getStat()[5]));
        }
        else if(item instanceof Potion){
            text= new JLabel("Health: "+String.valueOf((((Potion) item).getStat())[0])+" Mana: "+String.valueOf((((Potion) item).getStat())[2]));
        }



    }

    public void closeMenu(){
        this.menu.close();
    }

    public void delete(){
        player.getInventory().delete(item);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }



    @Override
    public void notifyModifierObserver(int[] stats) {
        for (ModifierObserver o : observers) {
            //
        }
    }

    @Override
    public void attachModifier(ModifierObserver po) {
        observers.add(po);
        this.player=((Player) observers.get(0));

    }

    public void setObserver(Player player){
        this.player=player;
    }
}
