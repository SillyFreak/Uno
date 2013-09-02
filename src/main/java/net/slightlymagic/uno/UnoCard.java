/**
 * UnoCard.java
 * 
 * Created on 01.06.2013
 */

package net.slightlymagic.uno;


import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.Entity;


/**
 * <p>
 * {@code UnoCard}
 * </p>
 * 
 * @version V0.0 01.06.2013
 * @author Clemens Koza
 */
public class UnoCard implements Entity {
    private static final long serialVersionUID = 4696630557479973898L;
    
    public static final int   NUMBER           = 0, DRAW_TWO = 1, REVERSE = 2, SKIP = 3, WILD = 4, DRAW_FOUR = 5;
    public static final int   BLACK            = 0, BLUE = 1, GREEN = 2, RED = 3, YELLOW = 4;
    
    private final int         kind, color, number;
    
    public static UnoCard number(Engine engine, int color, int number) {
        if(color < 1 || color > 4) throw new IllegalArgumentException();
        if(number < 0 || number > 9) throw new IllegalArgumentException();
        return new UnoCard(engine, NUMBER, color, number);
    }
    
    public static UnoCard drawTwo(Engine engine, int color) {
        if(color < 1 || color > 4) throw new IllegalArgumentException();
        return new UnoCard(engine, DRAW_TWO, color, -1);
    }
    
    public static UnoCard reverse(Engine engine, int color) {
        if(color < 1 || color > 4) throw new IllegalArgumentException();
        return new UnoCard(engine, REVERSE, color, -1);
    }
    
    public static UnoCard skip(Engine engine, int color) {
        if(color < 1 || color > 4) throw new IllegalArgumentException();
        return new UnoCard(engine, SKIP, color, -1);
    }
    
    public static UnoCard wild(Engine engine) {
        return new UnoCard(engine, WILD, 0, -1);
    }
    
    public static UnoCard drawFour(Engine engine) {
        return new UnoCard(engine, DRAW_FOUR, 0, -1);
    }
    
    private UnoCard(Engine engine, int kind, int color, int number) {
        this.kind = kind;
        this.color = color;
        this.number = number;
    }
    
    public int getKind() {
        return kind;
    }
    
    public int getColor() {
        return color;
    }
    
    public int getNumber() {
        return number;
    }
}
