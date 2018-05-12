package View;

import Model.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Map extends JPanel {
    private Vector<GameObject> objects = null;
    private int spacing = 30;
    private Dimension size = new Dimension(1000,800);
    private ArrayList<int[]> wallMatrix = new ArrayList<int[]>();

    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void paint(Graphics g) {
        this.spacing=(this.size.height-30)/20;

        int n=0;

        for (int i = 0; i < 20; i++) { // base grid

            for (int j = 0; j < 20; j++) {
                int x = i;
                int y = j;
                try {
                    Image picture = ImageIO.read(new File("images/Ground.png"));
                    Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                    g.drawImage(scaled, x * spacing, y * spacing, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                        try {
                            Image picture = ImageIO.read(new File("images/Pillar.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[1, 0, 0, 0]":
                        try {
                            Image picture = ImageIO.read(new File("images/NarrowCornerLeft.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[1, 1, 0, 0]":
                        try {
                            Image picture = ImageIO.read(new File("images/NarrowHorizontal.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[1, 1, 1, 0]":
                        try {
                            Image picture = ImageIO.read(new File("images/WallDown.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[0, 1, 0, 0]":
                        try {
                            Image picture = ImageIO.read(new File("images/NarrowCornerRight.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[0, 1, 1, 0]":
                        try {
                            Image picture = ImageIO.read(new File("images/CornerUpRight.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[0, 1, 1, 1]":
                        try {
                            Image picture = ImageIO.read(new File("images/WallRight.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[0, 1, 0, 1]":
                        try {
                            Image picture = ImageIO.read(new File("images/CornerDownRight.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[0, 0, 1, 0]":
                        try {
                            Image picture = ImageIO.read(new File("images/NarrowCornerDown.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[0, 0, 1, 1]":
                        try {
                            Image picture = ImageIO.read(new File("images/NarrowVertical.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[0, 0, 0, 1]":
                        try {
                            Image picture = ImageIO.read(new File("images/NarrowCornerUp.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[1, 0, 1, 0]":
                        try {
                            Image picture = ImageIO.read(new File("images/CornerUpLeft.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[1, 0, 1, 1]":
                        try {
                            Image picture = ImageIO.read(new File("images/WallLeft.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[1, 1, 0, 1]":
                        try {
                            Image picture = ImageIO.read(new File("images/WallUp.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "[1, 0, 0, 1]":
                        try {
                            Image picture = ImageIO.read(new File("images/CornerDownLeft.png"));
                            Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                            g.drawImage(scaled, x * spacing, y * spacing, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
                n++;
            }

            if (object instanceof BlockUnbreakable){
                try {
                    Image picture = ImageIO.read(new File("images/Void.png"));
                    Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                    g.drawImage(scaled, x * spacing, y * spacing, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (object instanceof Stair){
                try {
                    Image picture = ImageIO.read(new File("images/stone_stairs_down.png"));
                    Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                    g.drawImage(scaled, x * spacing, y * spacing, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (object instanceof Bow){
                try {
                    Image picture = ImageIO.read(new File("images/bow1.png"));
                    Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                    g.drawImage(scaled, x * spacing, y * spacing, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                if (object instanceof Player) {
                    //int direction = ((Directable) object).getDirection();

                    switch (direction) {
                        case Directable.EAST:
                            try {
                                Image picture = ImageIO.read(new File("images/right_knight.png"));
                                Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                                g.drawImage(scaled, x * spacing, y * spacing, null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                        case Directable.NORTH:
                            try {
                                Image picture = ImageIO.read(new File("images/back_knight.png"));
                                Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                                g.drawImage(scaled, x * spacing, y * spacing, null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                        case Directable.WEST:
                            try {
                                Image picture = ImageIO.read(new File("images/left_knight.png"));
                                Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                                g.drawImage(scaled, x * spacing, y * spacing, null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                        case Directable.SOUTH:
                            try {
                                Image picture = ImageIO.read(new File("images/front_knight.png"));
                                Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                                g.drawImage(scaled, x * spacing, y * spacing, null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                    }

                    //g.drawImage(picture, x*spacing, y*spacing,null);

                }

                if(object instanceof Mob){
                    try {
                        Image picture = ImageIO.read(new File("images/Orc.png"));
                        Image scaled = picture.getScaledInstance(spacing, spacing, Image.SCALE_DEFAULT);
                        g.drawImage(scaled, x * spacing, y * spacing, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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

                        g.setFont(new Font("TimesRoman", Font.BOLD, 25));
                        g.setColor(Color.BLACK);
                        g.drawString("LIFE", 1050, 85);
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(1050, 100, 250, 50);
                        g.setColor(Color.GREEN);
                        g.fillRect(1050, 100, Math.round(((float) life / (float) maxLife) * 250), 50);

                        g.setColor(Color.BLACK);
                        g.drawString("MANA", 1050, 235);
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(1050, 250, 250, 50);
                        g.setColor(Color.BLUE);
                        g.fillRect(1050, 250, Math.round(((float) mana / (float) maxMana) * 250), 50);

                        g.setColor(Color.BLACK);
                        g.drawString("LEVEL : " + String.valueOf(level), 1050, 900);
                        g.setColor(Color.DARK_GRAY);
                        g.fillRect(1050, 915, 250, 5);
                        g.setColor(Color.YELLOW);
                        g.fillRect(1050, 915, Math.round(((float) xp / (float) levelXp) * 250), 5);
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
}
