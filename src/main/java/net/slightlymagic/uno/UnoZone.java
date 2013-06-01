/**
 * UnoZone.java
 * 
 * Created on 01.06.2013
 */

package net.slightlymagic.uno;


import static java.util.Collections.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import at.pria.koza.harmonic.AEntity;
import at.pria.koza.harmonic.Engine;


/**
 * <p>
 * {@code UnoZone}
 * </p>
 * 
 * @version V0.0 01.06.2013
 * @author Clemens Koza
 */
public class UnoZone extends AEntity implements Iterable<UnoCard> {
    private static final long serialVersionUID = -8183632314265874214L;
    
    private final List<UnoCard> cards, view;
    
    public UnoZone(Engine engine) {
        super(engine);
        cards = new ArrayList<>(); //TODO modifications
        view = unmodifiableList(cards);
    }
    
    public List<UnoCard> getCards() {
        return view;
    }
    
    public void shuffle(Random random) {
        Collections.shuffle(cards, random);
    }
    
    public int size() {
        return cards.size();
    }
    
    @Override
    public Iterator<UnoCard> iterator() {
        return view.iterator();
    }
    
    //list interface
    public void add(UnoCard card) {
        cards.add(card);
    }
    
    public boolean contains(UnoCard card) {
        return cards.contains(card);
    }
    
    public boolean remove(UnoCard card) {
        return cards.remove(card);
    }
    
    public UnoCard remove(int index) {
        return cards.remove(index);
    }
    
    public UnoCard get(int index) {
        return cards.get(index);
    }
    
    //stack interface
    public void push(UnoCard card) {
        add(card);
    }
    
    public UnoCard pop() {
        return remove(size() - 1);
    }
    
    public UnoCard peek() {
        return get(size() - 1);
    }
}
