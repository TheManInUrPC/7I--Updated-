//+++++++++++++++++++++ PyramidSolitaire ++++++++++++++++++++++++++
import javax.swing.*;
import java.awt.*;

/**
 * PyramidSolitaire -- App class Pyramid Solitaire.
 * 
 * @author rdb
 * Last modified 03/28/14
 */
public class PyramidSolitaire extends JFrame
{
    //---------------------- instance variables ----------------------
    private GUI _appPanel;      // the app's JPanel
    
    //--------------------------- constructor ------------------------
    /**
     * Constructor for playable solitaire game.
     * 
     * @param title String     window title
     */
    public PyramidSolitaire( String title )     
    {
        super( title );
        
        this.setBackground( Color.LIGHT_GRAY );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        _appPanel = new GUI( this );
        this.add( _appPanel );
        
        this.setSize( _appPanel.getWidth(), _appPanel.getHeight() + 100 );
        
        this.setVisible( true );
    }
    
    //--------------------------- main --------------------------------
    /**
     * This main starts the application.
     * 
     * @param args String[]            command line arguments.
     */
    public static void main( String [ ] args ) 
    {
        new PyramidSolitaire( "PyramidSolitaire" );
    }
}
