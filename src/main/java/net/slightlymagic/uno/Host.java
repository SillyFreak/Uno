/**
 * Host.java
 * 
 * Created on 02.08.2013
 */

package net.slightlymagic.uno;


import static at.pria.koza.harmonic.BranchManager.*;
import net.slightlymagic.uno.action.NewGameAction;

import org.jgroups.JChannel;

import at.pria.koza.harmonic.BranchManager;
import at.pria.koza.harmonic.Engine;
import at.pria.koza.harmonic.jGroups.JGroupsBranchAdapter;


/**
 * <p>
 * {@code Host}
 * </p>
 * 
 * @version V0.0 02.08.2013
 * @author SillyFreak
 */
public class Host {
    private final BranchManager        mgr;
    private final JGroupsBranchAdapter adapter;
    
    public Host(String cluster) throws Exception {
        mgr = new BranchManager();
        
        JChannel ch = new JChannel();
        ch.setDiscardOwnMessages(true);
        adapter = new JGroupsBranchAdapter(ch, mgr);
        ch.setReceiver(adapter);
        ch.connect(cluster);
    }
    
    public void newGame(int numPlayers, long seed) {
        mgr.setBranchTip(BRANCH_DEFAULT, mgr.getEngine().getState(0l));
        mgr.execute(new NewGameAction(mgr.getEngine(), numPlayers, seed));
    }
    
    public void publish(int other, String branch) {
        adapter.sendUpdate(null, other, branch);
    }
    
    public BranchManager getBranchManager() {
        return mgr;
    }
    
    public Engine getEngine() {
        return mgr.getEngine();
    }
    
    public UnoGame getGame() {
        return (UnoGame) mgr.getEngine().getEntity(0);
    }
}
