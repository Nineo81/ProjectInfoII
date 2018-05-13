package View;

import Model.GameObject;

import javax.swing.*;


public class ItemButton extends JButton {
    GameObject item;
    public ItemButton(ImageIcon icon){
        super(icon);
    }


    public void setItem(GameObject item){
        this.item=item;
    }

    public GameObject getItem(){
        return this.item;
    }
}
