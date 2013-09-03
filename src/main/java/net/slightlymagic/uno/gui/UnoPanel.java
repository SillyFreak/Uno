/**
 * UnoPanel.java
 * 
 * Created on 03.09.2013
 */

package net.slightlymagic.uno.gui;


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import net.slightlymagic.uno.Host;
import net.slightlymagic.uno.UnoCard;
import net.slightlymagic.uno.UnoZone;


/**
 * <p>
 * {@code UnoPanel}
 * </p>
 * 
 * @version V0.0 03.09.2013
 * @author SillyFreak
 */
public class UnoPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private final Host        host;
    private final CardPainter painter;
    
    public UnoPanel(Host host) {
        this.host = host;
        painter = new DefaultCardPainter();
        setPreferredSize(new Dimension(800, 600));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        paintCard(painter, center(g, getWidth() / 2d - 90, getHeight() / 2d), host.getGame().getTopCard(), false);
        paintCard(painter, center(g, getWidth() / 2d + 90, getHeight() / 2d), host.getGame().getDeck().peek(),
                true);
        
        
        {
            UnoZone hand = host.getGame().getPlayers().get(0).getHand();
            for(int len = hand.size(), i = 0; i < len; i++) {
                paintCard(painter, center(g, getWidth() / 2d - (len / 2) * 180 + i * 180, getHeight() - 150),
                        hand.get(i), false);
            }
        }
        {
            UnoZone hand = host.getGame().getPlayers().get(1).getHand();
            for(int len = hand.size(), i = 0; i < len; i++) {
                paintCard(painter, center(g, getWidth() / 2d - (len / 2) * 180 + i * 180, 150), hand.get(i), true);
            }
        }
    }
    
    private Graphics2D center(Graphics g, double cx, double cy) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(cx, cy);
        return g2d;
    }
    
    private void paintCard(CardPainter p, Graphics2D g2d, UnoCard card, boolean back) {
        p.paintCard(g2d, card, back);
        g2d.dispose();
    }
}
