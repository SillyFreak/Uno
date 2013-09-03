/**
 * Uno.java
 * 
 * Created on 14.05.2013
 */

package net.slightlymagic.uno;


import static at.pria.koza.harmonic.BranchManager.*;
import net.slightlymagic.uno.action.NewGameAction;
import at.pria.koza.harmonic.BranchManager;
import at.pria.koza.harmonic.Engine;
import at.pria.koza.polybuf.PolybufConfig;


/**
 * <p>
 * The class Uno.
 * </p>
 * 
 * @version V0.0 14.05.2013
 * @author SillyFreak
 */
public class Uno {
    public static void main(String[] args) throws Exception {
        Host host1 = config("uno");
        Host host2 = config("uno");
        
        Thread.sleep(100);
        
        host1.newGame(2, 0);
        host1.publish(0, BRANCH_DEFAULT);
        
        Thread.sleep(100);
        
        System.out.println(host1.getGame().getPlayers().get(0).getHand().getCards());
        System.out.println(host2.getGame().getPlayers().get(0).getHand().getCards());
        
//        {
//            JFrame jf = new JFrame(format("%08X", host.getEngine().getId()));
//            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            
//            jf.add(new TicTacToePanel(host));
//            
//            jf.pack();
//            jf.setVisible(true);
//        }
//        {
//            JFrame jf = new JFrame(format("%08X", host.getEngine().getId()));
//            jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//            
//            HarmonicViewer viewer = new HarmonicViewer();
//            viewer.listenTo(host.getBranchManager());
//            jf.add(viewer);
//            
//            jf.pack();
//            jf.setVisible(true);
//        }
    }
    
    private static Host config(String cluster) throws Exception {
        final Host host = new Host(cluster);
        
        BranchManager mgr = host.getBranchManager();
        Engine engine = mgr.getEngine();
        PolybufConfig config = engine.getConfig();
        
        mgr.configure(config);
        NewGameAction.configure(config, engine);
        
        return host;
    }
}
