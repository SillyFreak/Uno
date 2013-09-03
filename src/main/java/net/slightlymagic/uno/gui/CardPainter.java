/**
 * CardPainter.java
 * 
 * Created on 03.09.2013
 */

package net.slightlymagic.uno.gui;


import java.awt.Graphics2D;

import net.slightlymagic.uno.UnoCard;


/**
 * <p>
 * {@code CardPainter}
 * </p>
 * 
 * @version V0.0 03.09.2013
 * @author SillyFreak
 */
public interface CardPainter {
    /**
     * <p>
     * Paints a card to the given Graphics object. The card will be centered at the graphics' origin, the size may
     * be implementation dependent. If {@code back} is {@code true}, only the back of the card will be painted. The
     * card must be opaque, and everything not belonging to the card must be transparent.
     * </p>
     */
    public void paintCard(Graphics2D g2d, UnoCard card, boolean back);
}
