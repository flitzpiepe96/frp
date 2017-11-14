package de.tinf15b4.frp.internal.gui;

import javax.swing.*;

import de.tinf15b4.frp.api.Document;
import de.tinf15b4.frp.api.KeyEventType;
import de.tinf15b4.frp.api.MouseEvent;
import de.tinf15b4.frp.api.Paradigm;
import de.tinf15b4.frp.internal.MouseEventSimple;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

public class ParadigmView extends JPanel implements Paradigm.DocumentListener {

    private Document doc;
    private Paradigm paradigm;

    public ParadigmView(Document initDoc, Paradigm.Factory factory) {
        this.doc = initDoc;
        this.paradigm = factory.create(initDoc, this);
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(new MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent ev) {
                paradigm.mouseEvent(new MouseEventSimple(MouseEvent.Type.DOWN,
                        new Point(ev.getX(), ev.getY())));
            }
            public void mouseReleased(java.awt.event.MouseEvent ev) {
                paradigm.mouseEvent(new MouseEventSimple(MouseEvent.Type.UP,
                        new Point(ev.getX(), ev.getY())));
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent ev) {
                paradigm.mouseEvent(new MouseEventSimple(MouseEvent.Type.MOVE,
                        new Point(ev.getX(), ev.getY())));
            }
            public void mouseMoved(java.awt.event.MouseEvent ev) {
                paradigm.mouseEvent(new MouseEventSimple(MouseEvent.Type.MOVE,
                        new Point(ev.getX(), ev.getY())));
            }
        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 300);
    }

    public void documentUpdated(Document doc) {
        this.doc = doc;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doc.draw(g);
    }

    public void removeNotify() {
        paradigm.dispose();
        super.removeNotify();
    }
}

