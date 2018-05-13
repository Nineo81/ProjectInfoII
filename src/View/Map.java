package View;

import Model.*;

import java.awt.*;
import java.util.Observer;

import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Map extends JPanel {
    private Vector<GameObject> objects = null;
    private int spacing = 30;
    private Dimension size = new Dimension(1000,800);
    private ArrayList<int[]> wallMatrix = new ArrayList<int[]>();

    private Image ground,left_knight,right_knight,back_knight,front_knight,bow,stairs,empty,WallUp,
            WallDown,WallLeft,WallRight,orc,CornerDownLeft,CornerDownRight,CornerUpLeft,CornerUpRight,
            NarrowCornerDown,NarrowCornerLeft,NarrowCornerRight,NarrowCornerUp,NarrowHorizontal,
            NarrowVertical,Pillar,ArrowUp,ArrowDown,ArrowRight,ArrowLeft,Potion;


    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow();

    }

    public void paint(Graphics g) {
        int n=0;

        for (int i = 0; i < 20; i++) { // base grid

            for (int j = 0; j < 20; j++) {
                int x = i;
                int y = j;
                g.drawImage(ground, x * spacing, y * spacing, null);
            }
        }

        for (GameObject object : this.objects) {


            int x = object.getPosX();
            int y = object.getPosY();
            int color = object.getColor();

            /*if (color == 0) {
                g.setColor(Color.DARK_GRAY);
            } else if (color == 1) {
                g.setColor(Color.GRAY);
            } else if (color == 2) {
                g.setColor(Color.LIGHT_GRAY);
            } else if (color == 3) {
                g.setColor(Color.GREEN);
            } else if (color == 4) {
                g.setColor(Color.RED);
            } else if (color == 5) {
                g.setColor(Color.ORANGE);
            }

            g.fillRect(x * spacing, y * spacing, spacing - 2, spacing - 2);
            g.setColor(Color.BLACK);
            g.drawRect(x * spacing, y * spacing, spacing - 2, spacing - 2);*/

            if (object instanceof BlockBreakable){
                int[] wallType = wallMatrix.get(n);

                switch(Arrays.toString(wallType)){
                    case "[0, 0, 0, 0]":
                        g.drawImage(Pillar, x * spacing, y * spacing, null);
                        break;
                    case "[1, 0, 0, 0]":
                        g.drawImage(NarrowCornerLeft, x * spacing, y * spacing, null);
                        break;
                    case "[1, 1, 0, 0]":
                        g.drawImage(NarrowHorizontal, x * spacing, y * spacing, null);
                        break;
                    case "[1, 1, 1, 0]":
                        g.drawImage(WallDown, x * spacing, y * spacing, null);
                        break;
                    case "[0, 1, 0, 0]":
                        g.drawImage(NarrowCornerRight, x * spacing, y * spacing, null);
                        break;
                    case "[0, 1, 1, 0]":
                        g.drawImage(CornerUpRight, x * spacing, y * spacing, null);
                        break;
                    case "[0, 1, 1, 1]":
                        g.drawImage(WallRight, x * spacing, y * spacing, null);
                        break;
                    case "[0, 1, 0, 1]":
                        g.drawImage(CornerDownRight, x * spacing, y * spacing, null);
                        break;
                    case "[0, 0, 1, 0]":
                        g.drawImage(NarrowCornerDown, x * spacing, y * spacing, null);
                        break;
                    case "[0, 0, 1, 1]":
                        g.drawImage(NarrowVertical, x * spacing, y * spacing, null);
                        break;
                    case "[0, 0, 0, 1]":
                        g.drawImage(NarrowCornerUp, x * spacing, y * spacing, null);
                        break;
                    case "[1, 0, 1, 0]":
                        g.drawImage(CornerUpLeft, x * spacing, y * spacing, null);
                        break;
                    case "[1, 0, 1, 1]":
                        g.drawImage(WallLeft, x * spacing, y * spacing, null);
                        break;
                    case "[1, 1, 0, 1]":
                        g.drawImage(WallUp, x * spacing, y * spacing, null);
                        break;
                    case "[1, 0, 0, 1]":
                        g.drawImage(CornerDownLeft, x * spacing, y * spacing, null);
                }
                n++;
            }

            if (object instanceof BlockUnbreakable){
                g.drawImage(empty, x * spacing, y * spacing, null);
            }

            if (object instanceof Stair){
                g.drawImage(stairs, x * spacing, y * spacing, null);
            }

            if (object instanceof Bow){
                g.drawImage(bow, x * spacing, y * spacing, null);
            }

            if (object instanceof Potion){
                g.drawImage(Potion, x * spacing, y * spacing, null);
            }

            // Decouper en fontions
            if (object instanceof Directable) {
                int direction = ((Directable) object).getDirection();

                int deltaX = 0;
                int deltaY = 0;

                switch (direction) {
                    case Directable.EAST:
                        deltaX = +(spacing - 2) / 2;
                        break;
                    case Directable.NORTH:
                        deltaY = -(spacing - 2) / 2;
                        break;
                    case Directable.WEST:
                        deltaX = -(spacing - 2) / 2;
                        break;
                    case Directable.SOUTH:
                        deltaY = (spacing - 2) / 2;
                        break;
                }
                if (object instanceof Projectile){
                    switch(direction){
                        case Directable.EAST:
                            g.drawImage(ArrowRight, x * spacing, y * spacing, null);

                            break;
                        case Directable.NORTH:
                            g.drawImage(ArrowUp, x * spacing, y * spacing, null);

                            break;
                        case Directable.WEST:

                            g.drawImage(ArrowLeft, x * spacing, y * spacing, null);

                            break;
                        case Directable.SOUTH:
                            g.drawImage(ArrowDown, x * spacing, y * spacing, null);

                            break;
                    }
                }
                if (object instanceof Player) {
                    //int direction = ((Directable) object).getDirection();

                    switch (direction) {
                        case Directable.EAST:
                                g.drawImage(right_knight, x * spacing, y * spacing, null);

                            break;
                        case Directable.NORTH:
                                g.drawImage(back_knight, x * spacing, y * spacing, null);

                            break;
                        case Directable.WEST:

                                g.drawImage(left_knight, x * spacing, y * spacing, null);

                            break;
                        case Directable.SOUTH:
                                g.drawImage(front_knight, x * spacing, y * spacing, null);

                            break;
                    }

                    //g.drawImage(picture, x*spacing, y*spacing,null);

                }

                if(object instanceof Mob){
                    g.drawImage(orc, x * spacing, y * spacing, null);
                }

                else {
                    //int direction = ((Directable) object).getDirection();

                    //int deltaX = 0;
                    //int deltaY = 0;

                    switch (direction) {
                        case Directable.EAST:
                            deltaX = +24;
                            break;
                        case Directable.NORTH:
                            deltaY = -24;
                            break;
                        case Directable.WEST:
                            deltaX = -24;
                            break;
                        case Directable.SOUTH:
                            deltaY = 24;
                            break;
                    }

                    int xCenter = x * spacing + (spacing - 2) / 2;
                    int yCenter = y * spacing + (spacing - 2) / 2;
                    g.drawLine(xCenter, yCenter, xCenter + deltaX, yCenter + deltaY);
                }
                if (object instanceof Activable) {
                    int life = ((Activable) object).getLife();
                    int maxLife = ((Activable) object).getMaxLife();
                    if (maxLife != life) {
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(x * spacing + 1, y * spacing + 2, (spacing-4), 2);
                        g.setColor(Color.GREEN);
                        g.fillRect(x * spacing + 1, y * spacing + 2, Math.round(((float) life / (float) maxLife) * (spacing-4)), 2);
                    }
                    if (object instanceof Player) {
                        int mana = ((Player) object).getMana();
                        int maxMana = ((Player) object).getMaxMana();
                        int xp = ((Player) object).getXp();
                        int level = ((Player) object).getLevel();
                        int levelXp = ((Player) object).getLevelXp();

                        g.setFont(new Font("TimesRoman", Font.BOLD, spacing));
                        g.setColor(Color.BLACK);
                        g.drawString("LIFE", 22*spacing, spacing);
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(22*spacing, 2*spacing, 8*spacing, spacing);
                        g.setColor(Color.GREEN);
                        g.fillRect(22*spacing, 2*spacing, Math.round(((float) life / (float) maxLife) * 8*spacing), spacing);

                        g.setColor(Color.BLACK);
                        g.drawString("MANA", 22*spacing, 4*spacing);
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(22*spacing, 5*spacing, 8*spacing, spacing);
                        g.setColor(Color.BLUE);
                        g.fillRect(22*spacing, 5*spacing, Math.round(((float) mana / (float) maxMana) * 8*spacing), spacing);

                        g.setColor(Color.BLACK);
                        g.drawString("LEVEL : " + String.valueOf(level), 22*spacing, 7*spacing);
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(22*spacing, 8*spacing, 8*spacing, spacing/4);
                        g.setColor(Color.YELLOW);
                        g.fillRect(22*spacing, 8*spacing, Math.round(((float) xp / (float) levelXp) * 8*spacing), spacing/4);

                    }
                }
            }
        }
    }

    public void setObjects(Vector<GameObject> objects) {
        this.objects = objects;
    }

    public void redraw() {
        this.repaint();
    }

    public void levelLayout(int width, int heigth) {

        ArrayList<Integer> pointSet = null;
        do {

        } while (pointSet.size() < 4);
    }


    protected void paintComponent(Graphics g,Image image, int x, int y) {
        super.paintComponent(g);
        g.drawImage(image, x, y, this); // see javadoc for more info on the parameters
    }

    public void setSize(Dimension size){
        this.size=size;
        this.spacing=(this.size.height-30)/20;
        loadImages();
    }

    public void wallConstructor(){
        this.wallMatrix.clear();

        for(GameObject wall:this.objects){
            if(wall instanceof BlockBreakable){
                int x = wall.getPosX();
                int y = wall.getPosY();
                int[][] neighbor = {{1,0},{-1,0},{0,1},{0,-1}};
                int[] wallType = {0,0,0,0};
                int j=0;
                for (int[] i:neighbor){
                    for(GameObject adjacent:this.objects){
                        if(adjacent.isAtPosition(x+i[0], y+i[1])){
                            if(adjacent instanceof BlockBreakable){
                                wallType[j]=1;
                            }
                        }
                    }
                    j++;
                }
                this.wallMatrix.add(wallType);
            }
        }

    }

    private void loadImages(){
        try{this.left_knight = (ImageIO.read(new File("images/left_knight.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}

        try{this.back_knight = (ImageIO.read(new File("images/back_knight.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.right_knight = (ImageIO.read(new File("images/right_knight.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.front_knight = (ImageIO.read(new File("images/front_knight.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.bow = (ImageIO.read(new File("images/bow1.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.ground = (ImageIO.read(new File("images/Ground.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.orc = (ImageIO.read(new File("images/Orc.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.stairs = (ImageIO.read(new File("images/stone_stairs_down.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.empty = (ImageIO.read(new File("images/void.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.WallDown = (ImageIO.read(new File("images/WallDown.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.WallUp = (ImageIO.read(new File("images/WallUp.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){System.out.print("bite");}
        try{this.WallLeft = (ImageIO.read(new File("images/WallLeft.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.WallRight = (ImageIO.read(new File("images/WallRight.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}

        try{this.CornerDownLeft = (ImageIO.read(new File("images/CornerDownLeft.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.CornerDownRight = (ImageIO.read(new File("images/CornerDownRight.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.CornerUpLeft = (ImageIO.read(new File("images/CornerUpLeft.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.CornerUpRight = (ImageIO.read(new File("images/CornerUpRight.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.NarrowCornerDown = (ImageIO.read(new File("images/NarrowCornerDown.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.NarrowCornerLeft = (ImageIO.read(new File("images/NarrowCornerLeft.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.NarrowCornerRight = (ImageIO.read(new File("images/NarrowCornerRight.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.NarrowCornerUp = (ImageIO.read(new File("images/NarrowCornerUp.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.NarrowHorizontal = (ImageIO.read(new File("images/NarrowHorizontal.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){System.out.print("penis");}
        try{this.NarrowVertical = (ImageIO.read(new File("images/NarrowVertical.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.Pillar = (ImageIO.read(new File("images/Pillar.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}

        try{this.ArrowUp = (ImageIO.read(new File("images/ArrowUp.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.ArrowRight = (ImageIO.read(new File("images/ArrowRight.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.ArrowLeft = (ImageIO.read(new File("images/ArrowLeft.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.ArrowDown = (ImageIO.read(new File("images/ArrowDown.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}
        try{this.Potion = (ImageIO.read(new File("images/brilliant_blue.png"))).getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);}catch (IOException e){}

    }
}
