/**
 * UnoPlayer.java
 * 
 * Created on 01.06.2013
 */

package net.slightlymagic.uno;


import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.Entity;


/**
 * <p>
 * {@code UnoPlayer}
 * </p>
 * 
 * @version V0.0 01.06.2013
 * @author Clemens Koza
 */
public class UnoPlayer implements Entity {
    private static final long serialVersionUID = -8751599353263941983L;
    
    private final UnoZone     hand;
    
    public UnoPlayer(Engine engine) {
        hand = new UnoZone(engine);
    }
    
    public UnoZone getHand() {
        return hand;
    }
}
