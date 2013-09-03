/**
 * NewGameAction.java
 * 
 * Created on 28.07.2013
 */

package net.slightlymagic.uno.action;


import net.slightlymagic.uno.UnoGame;
import net.slightlymagic.uno.proto.Uno.NewGameActionP;
import at.pria.koza.harmonic.Action;
import at.pria.koza.harmonic.Engine;
import at.pria.koza.polybuf.PolybufConfig;
import at.pria.koza.polybuf.PolybufException;
import at.pria.koza.polybuf.PolybufIO;
import at.pria.koza.polybuf.PolybufInput;
import at.pria.koza.polybuf.PolybufOutput;
import at.pria.koza.polybuf.PolybufSerializable;
import at.pria.koza.polybuf.proto.Polybuf.Obj;

import com.google.protobuf.GeneratedMessage.GeneratedExtension;


/**
 * <p>
 * {@code NewGameAction}
 * </p>
 * 
 * @version V0.0 28.07.2013
 * @author SillyFreak
 */
public class NewGameAction extends Action implements PolybufSerializable {
    public static final int                                     FIELD     = NewGameActionP.NEW_GAME_ACTION_FIELD_NUMBER;
    public static final GeneratedExtension<Obj, NewGameActionP> EXTENSION = NewGameActionP.newGameAction;
    
    public static PolybufIO<NewGameAction> getIO(Engine engine) {
        return new IO(engine);
    }
    
    public static void configure(PolybufConfig config, Engine engine) {
        config.add(getIO(engine));
    }
    
    private final int  numPlayers;
    private final long seed;
    private UnoGame    game;
    
    public NewGameAction(Engine engine, int numPlayers, long seed) {
        super(engine);
        this.numPlayers = numPlayers;
        this.seed = seed;
    }
    
    @Override
    protected void apply0() {
        game = new UnoGame(getEngine());
        game.startGame(seed, numPlayers);
    }
    
    public UnoGame getGame() {
        return game;
    }
    
    @Override
    public int getTypeId() {
        return FIELD;
    }
    
    private static class IO implements PolybufIO<NewGameAction> {
        private final Engine engine;
        
        public IO(Engine engine) {
            this.engine = engine;
        }
        
        @Override
        public int getType() {
            return FIELD;
        }
        
        @Override
        public GeneratedExtension<Obj, ?> getExtension() {
            return EXTENSION;
        }
        
        @Override
        public void serialize(PolybufOutput out, NewGameAction object, Obj.Builder obj) throws PolybufException {
            NewGameActionP.Builder b = NewGameActionP.newBuilder();
            b.setNumPlayers(object.numPlayers);
            b.setSeed(object.seed);
            
            obj.setExtension(EXTENSION, b.build());
        }
        
        @Override
        public NewGameAction initialize(PolybufInput in, Obj obj) throws PolybufException {
            NewGameActionP p = obj.getExtension(EXTENSION);
            int numPlayers = p.getNumPlayers();
            long seed = p.getSeed();
            
            return new NewGameAction(engine, numPlayers, seed);
        }
        
        @Override
        public void deserialize(PolybufInput in, Obj obj, NewGameAction object) throws PolybufException {}
    }
}
