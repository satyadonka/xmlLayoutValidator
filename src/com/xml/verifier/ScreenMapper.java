/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml.verifier;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author devadas
 */
public class ScreenMapper extends Container {

    HashMap gadgetMap;
    public void displayScreen(Object obj)
    {
        System.out.println("setting mapper obj");
        this.gadgetMap = (HashMap)obj;
//        repaint();
//        repaint(.getBounds());
    }
    
    @Override
    public void paint(Graphics g) {
        System.out.println("Inside mapper paint...");
        super.paint(g);
        Iterator iterator = gadgetMap.values().iterator();
        while (iterator.hasNext())
        {
            GadgetDisplayElement gadgetElement = (GadgetDisplayElement) iterator.next();
            System.out.println("DRAWING : " + gadgetElement);
            g.setColor(GadgetConfig.getGadgetColor(gadgetElement.getGadgetType()));
            g.drawRect(gadgetElement.getBounds().x, gadgetElement.getBounds().y,
                    gadgetElement.getBounds().width, gadgetElement.getBounds().height);
            if (null != gadgetElement.getBackgroundImagePath())
            {
                Image image = loadImage(gadgetElement.getBackgroundImagePath());
                if (null != image)
                {
                    g.drawImage(image, gadgetElement.getBounds().x, gadgetElement.getBounds().y,
                    gadgetElement.getBounds().width, gadgetElement.getBounds().height, this);
                }
            }
            g.drawString(gadgetElement.getGadgetName(), gadgetElement.getBounds().x + 20, gadgetElement.getBounds().y + 20);
        }
    }

    private static Image loadImage(String fileName)
    {
        fileName = "D:/dev/dstvo/Project_Olive_Tree/Resources/Images/Graphics/Menu/" + fileName;
        Image image = Toolkit.getDefaultToolkit().createImage(fileName);
        MediaTracker tracker = new MediaTracker(new Container());
        tracker.addImage(image, 0);
        try
        {
            tracker.waitForAll();
        } catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
        return image;
    }
}
