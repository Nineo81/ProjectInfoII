package View;

import Model.Directable;
import Model.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observer;

import javax.swing.JPanel;

public class Map extends JPanel {
    private ArrayList<GameObject> objects = null;
    private int spacing = 30;

    public Map(Dimension size) {
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.spacing = size.height / 20;
    }

    public void paint(Graphics g) {
        for (int i = 0; i < 20; i++) { // base grid
            for (int j = 0; j < 20; j++) {
                int x = i;
                int y = j;
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x * spacing, y * spacing, spacing - 2, spacing - 2);
                g.setColor(Color.BLACK);
                g.drawRect(x * spacing, y * spacing, spacing - 2, spacing - 2);
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
                g.setColor(Color.GREEN);
            } else if (color == 3) {
                g.setColor(Color.GREEN);
            } else if (color == 4) {
                g.setColor(Color.RED);
            } else if (color == 5) {
                g.setColor(Color.ORANGE);
            }

            g.fillRect(x * spacing, y * spacing, spacing - 2, spacing - 2);
            g.setColor(Color.BLACK);
            g.drawRect(x * spacing, y * spacing, spacing - 2, spacing - 2);

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

                int xCenter = x * spacing + (spacing - 2) / 2;
                int yCenter = y * spacing + (spacing - 2) / 2;
                g.drawLine(xCenter, yCenter, xCenter + deltaX, yCenter + deltaY);
            }
        }
    }

    public void setObjects(ArrayList<GameObject> objects) {
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
}
