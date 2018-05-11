package View;

import Model.SizeableObserver;
import Model.Sizeable;

import java.awt.event.*;
import java.awt.Dimension;
import java.util.ArrayList;

class ResizeListener implements ComponentListener,Sizeable {
    Dimension size;
    private ArrayList<SizeableObserver> observers = new ArrayList<SizeableObserver>();

    public void componentHidden(ComponentEvent e) {}
    public void componentMoved(ComponentEvent e) {}
    public void componentShown(ComponentEvent e) {}

    public void componentResized(ComponentEvent e) {
        this.size = e.getComponent().getBounds().getSize();
        notifySizeableObserver();
    }

    public void attachSizeable(SizeableObserver o){
        observers.add(o);
    }

    public void notifySizeableObserver(){
        for(SizeableObserver o:observers){
            o.newSize(this.size);
        }
    }
}