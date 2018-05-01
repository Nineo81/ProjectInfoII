package View;

import Model.Activable;
import Model.Directable;
import Model.GameObject;
import Model.Player;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Map extends JPanel {
    private ArrayList<GameObject> objects = null;

    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void paint(Graphics g) {
        for (int i = 0; i < 20; i++) { // Virer la valeur 20 et parametrer ca
            for (int j = 0; j < 20; j++) {
                int x = i;
                int y = j;
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x * 50, y * 50, 48, 48);
                g.setColor(Color.BLACK);
                g.drawRect(x * 50, y * 50, 48, 48);
            }
        }

        for (GameObject object : this.objects) {
            int x = object.getPosX();
            int y = object.getPosY();
            int color = object.getColor();

            if (color == 0) {
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

            g.fillRect(x * 50, y * 50, 48, 48);
            g.setColor(Color.BLACK);
            g.drawRect(x * 50, y * 50, 48, 48);

            // Decouper en fontions
            if(object instanceof Directable) {
                if(object instanceof  Player){
                    int direction = ((Directable) object).getDirection();

                    switch (direction) {
                        case Directable.EAST:
                            try {
                                Image picture = ImageIO.read(new File("images/right_knight.png"));
                                g.drawImage(picture, x*50, y*50,null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                        case Directable.NORTH:
                            try {
                                Image picture = ImageIO.read(new File("images/back_knight.png"));
                                g.drawImage(picture, x*50, y*50,null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                        case Directable.WEST:
                            try {
                                Image picture = ImageIO.read(new File("images/left_knight.png"));
                                g.drawImage(picture, x*50, y*50,null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                        case Directable.SOUTH:
                            try {
                                Image picture = ImageIO.read(new File("images/front_knight.png"));
                                g.drawImage(picture, x*50, y*50,null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            break;
                    }

                    //g.drawImage(picture, x*50, y*50,null);

                }
                else {
                    int direction = ((Directable) object).getDirection();

                    int deltaX = 0;
                    int deltaY = 0;

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

                    int xCenter = x * 50 + 24;
                    int yCenter = y * 50 + 24;
                    g.drawLine(xCenter, yCenter, xCenter + deltaX, yCenter + deltaY);
                }
            }
            if(object instanceof Activable) {
                int life=((Activable) object).getLife();
                int maxLife= ((Activable) object).getMaxLife();
                if(maxLife!=life) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(x * 50 + 1, y * 50 + 2, 46, 2);
                    g.setColor(Color.GREEN);
                    g.fillRect(x * 50 + 1, y * 50 + 2, Math.round(((float) life / (float) maxLife) * 46), 2);
                }
                if (object instanceof Player){
                    int mana=((Player) object).getMana();
                    int maxMana= ((Player) object).getMaxMana();
                    int xp=((Player) object).getXp();
                    int level= ((Player) object).getLevel();
                    int levelXp= ((Player) object).getLevelXp();

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
                    g.drawString("LEVEL : "+String.valueOf(level), 1050, 900);
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(1050, 915, 250, 5);
                    g.setColor(Color.YELLOW);
                    g.fillRect(1050, 915, Math.round(((float) xp / (float) levelXp) * 250), 5);
                }
            }
        }

    }

    public void setObjects(ArrayList<GameObject> objects) {
        this.objects = objects;
    }

    public void redraw() {
        this.repaint();
    }


    protected void paintComponent(Graphics g,Image image, int x, int y) {
        super.paintComponent(g);
        g.drawImage(image, x, y, this); // see javadoc for more info on the parameters
    }

}
