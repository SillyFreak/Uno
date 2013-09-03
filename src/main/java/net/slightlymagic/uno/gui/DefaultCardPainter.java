/**
 * DefaultCardPainter.java
 * 
 * Created on 03.09.2013
 */

package net.slightlymagic.uno.gui;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;

import net.slightlymagic.uno.UnoCard;


/**
 * <p>
 * {@code DefaultCardPainter}
 * </p>
 * 
 * @version V0.0 03.09.2013
 * @author SillyFreak
 */
public class DefaultCardPainter implements CardPainter {
    private static final Shape card, internal;
    
    static {
        card = new RoundRectangle2D.Double(-80, -120, 160, 240, 20, 20);
        
        {
            Path2D p = new Path2D.Double();
            p.moveTo(-60, -70);
            p.lineTo(-60, 10);
            p.curveTo(-60, 40, -30, 70, 0, 70);
            p.lineTo(60, 70);
            p.lineTo(60, -10);
            p.curveTo(60, -40, 30, -70, 0, -70);
            p.closePath();
            internal = p;
        }
    }
    
    @Override
    public void paintCard(Graphics2D g2d, UnoCard c, boolean back) {
        g2d.setColor(Color.GRAY);
        g2d.fill(card);
        
        g2d.setStroke(new BasicStroke(5f));
        g2d.setColor(Color.BLACK);
        g2d.draw(card);
        
        g2d.setColor(Color.WHITE);
        g2d.fill(internal);
    }
}
