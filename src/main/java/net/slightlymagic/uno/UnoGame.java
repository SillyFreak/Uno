/**
 * UnoGame.java
 * 
 * Created on 01.06.2013
 */

package net.slightlymagic.uno;


import static java.util.Collections.*;
import static net.slightlymagic.uno.UnoCard.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.Entity;


/**
 * <p>
 * {@code UnoGame}
 * </p>
 * 
 * @version V0.0 01.06.2013
 * @author Clemens Koza
 */
public class UnoGame implements Entity {
    private static final long     serialVersionUID       = 8942624581281838040L;
    
    private final Random          random;
    private final List<UnoPlayer> players, view;
    private final UnoZone         deck, stack;
    
    private int                   currentPlayer;
    private int                   currentPlayerIncrement = 1;
    
    public UnoGame(Engine engine) {
        random = new Random(); //TODO modifications
        players = new ArrayList<>(); //TODO modifications
        view = unmodifiableList(players);
        deck = new UnoZone(engine);
        stack = new UnoZone(engine);
    }
    
    public List<UnoPlayer> getPlayers() {
        return view;
    }
    
    public UnoZone getStack() {
        return stack;
    }
    
    public UnoPlayer getCurrentPlayer() {
        return players.get(currentPlayer);
    }
    
    public UnoCard getTopCard() {
        return stack.peek();
    }
    
    public void startGame(long seed, int numPlayers) {
        if(numPlayers < 2) throw new IllegalArgumentException();
        
        //TODO actions
        
        //add players to the game
        for(int i = 0; i < numPlayers; i++) {
            UnoPlayer p = new UnoPlayer(getEngine());
            players.add(p);
        }
        
        //add cards to the deck
        for(int i = 0; i < 4; i++) {
            //wild cards
            deck.add(wild(getEngine()));
            //draw four cards
            deck.add(drawFour(getEngine()));
        }
        for(int color = UnoCard.BLUE; color <= UnoCard.YELLOW; color++) {
            //numbered cards per color
            deck.add(number(getEngine(), color, 0));
            for(int i = 1; i <= 9; i++) {
                deck.add(number(getEngine(), color, i));
                deck.add(number(getEngine(), color, i));
            }
            for(int i = 0; i < 2; i++) {
                deck.add(drawTwo(getEngine(), color));
                deck.add(reverse(getEngine(), color));
                deck.add(skip(getEngine(), color));
            }
        }
        
        //randomness
        random.setSeed(seed);
        //TODO modifications
        currentPlayer = random.nextInt(numPlayers);
        deck.shuffle(random);
        
        //move cards
        for(int i = 0; i < 7; i++)
            for(UnoPlayer p:players)
                p.getHand().add(deck.pop());
        stack.push(deck.pop());
    }
    
    public void drawCard(UnoPlayer player) {
        //TODO actions
        player.getHand().add(deck.pop());
    }
    
    public void playCard(UnoPlayer player, UnoCard card, int wishColor) {
        if(players.get(currentPlayer) != player) throw new IllegalArgumentException();
        if(card != null && !player.getHand().contains(card)) throw new IllegalArgumentException();
        UnoCard top = stack.peek();
        if(card != null && !(card.getColor() == BLACK || card.getColor() == top.getColor())
                && !(card.getKind() == top.getKind() && card.getNumber() == top.getNumber())) throw new IllegalArgumentException();
        if(wishColor != -1 && card.getColor() != BLACK) throw new IllegalArgumentException();
        
        //TODO actions
        player.getHand().remove(card);
        stack.push(card);
        
        int numPlayers = players.size();
        int nextPlayer = currentPlayer + currentPlayerIncrement;
        if(nextPlayer >= numPlayers) nextPlayer -= numPlayers;
        else if(nextPlayer < 0) nextPlayer += numPlayers;
        UnoPlayer next = players.get(nextPlayer);
        
        switch(card.getKind()) {
            case DRAW_TWO:
                for(int i = 0; i < 2; i++)
                    next.getHand().add(deck.pop());
            break;
            case REVERSE:
                if(numPlayers == 2) incrementCurrentPlayer();
                else reverseTurnOrder();
            break;
            case SKIP:
                incrementCurrentPlayer();
            break;
            case WILD:
            //TODO wish color
            break;
            case DRAW_FOUR:
                for(int i = 0; i < 4; i++)
                    next.getHand().add(deck.pop());
            //TODO wish color
            break;
            case NUMBER:
            //do nothing
            break;
            default:
                throw new AssertionError();
        }
    }
    
    public void incrementCurrentPlayer() {
        //TODO modifications
        currentPlayer += currentPlayerIncrement;
        
        int numPlayers = players.size();
        if(currentPlayer >= numPlayers) currentPlayer -= numPlayers;
        else if(currentPlayer < 0) currentPlayer += numPlayers;
    }
    
    public void reverseTurnOrder() {
        //TODO modifications
        currentPlayerIncrement = -currentPlayerIncrement;
    }
}
