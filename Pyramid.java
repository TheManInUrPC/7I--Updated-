//+++++++++++++++++++++++++ Pyramid.java ++++++++++++++++++++++++++++
import javax.swing.JPanel;
import java.awt.*;
import java.util.*;

/**
 * Pyramid - Represent a directed acyclic graph that defines a 
 *              pyramid organization suitable for the PyramidSolitaire
 *              game. The goal of this class is to focus it on the 
 *              data structure and expect all real game and graphics
 *              functionality to be done elsewhere. The nodes of the
 *              Pyramid, however, do have size and location information
 *              and the Pyramid has a top card location.
 * 
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * This is a very skeleton implementation. You do NOT need to use any
 * or all of the features implied by this skeleton. These are just
 * suggestions that might be useful. 
 * 
 * AND more methods are certainly needed.
 * 
 * The only requirements are:
 *    1. You should use the PyramidNode class to represent each node in
 *       the "tree".
 *    2. Each PyramidNode object needs to have references to its left
 *       and right children nodes (if it has them).
 *    3. Your test for whether you can  move a card should be based on
 *       the information in Pyramid and/or PyramidNode.
 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * @author rdb
 * March 2014
 * 
 * @param <T>    Pyramid nodes have a data field of type T
 */
public class Pyramid<T extends Geometry> 
{
    //-------------------- instance variables --------------------
    private int  _xCenter;    // x is CENTER of top card of pyramid
    private int  _yCenter;    // y center of top card
    private PyramidNode<T> node;
    private ArrayList<PyramidNode<T>> row;
    private ArrayList<ArrayList<PyramidNode<T>>> pepepyramid;
    private int rows;
    private Point p1;
    private PyramidNode<T> root;
    
    
    //--------------------- constructor --------------------------
    /**
     * Constructor.
     * 
     * @param nRows int        number rows in the pyramid
     * @param x int            x location of center of the pyramid
     * @param y int            y location of center of top card
     * @param nW int           width of nodes of pyramid
     * @param nH int           height of nodes of pyramid
     */
    public Pyramid( int nRows, int x, int y, int nW , int nH )
    {
        _xCenter = x;
        _yCenter = y;
        
        rows = nRows;
        
        p1.x = x;
        p1.y = y;
        
        for( int i = 0; i < rows; i++ )
        {
            for( int r = 0; r < i; r++ )
            {
                node = new PyramidNode<T>( ( x % 2 ), ( y + 10 ), nW, nH );
                
                
                row.add( node );
            }
            
            pepepyramid.add( row );
        }
    }
    
    //----------------- clear( ) -------------------
    /**
     * Empty all nodes of content.
     */
    public void clear()
    {
        for( int i = 0; i < pepepyramid.size(); i++ )
        {
            pepepyramid.remove( i );
        }
        for( int x = 0; x < row.size(); x++ )
        {
            row.remove( x );
        }
        
    }
    
    //------------------- getNode( int, int  ) -----------------------
    /**
     * Get the PyramidNode at level l, position p. 
     * 
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * This is a particularly questionable method. It's not clear that
     * this specific level of information is really necessary. I used
     * it in my first solution, but as I transitioned that solution into
     * starter code + solution, I realized that it should be pretty 
     * straightforward to come up with a solution that doesn't need it.
     * 
     * Feel free to use it or not.
     * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     * 
     * @param l int     level of pyramid
     * @param p int     position in row ( 0 .. l-1 )
     * @throws IndexOutOfBoundsException
     * @return PyramidNode<T>
     */
    public PyramidNode<T> getNode( int l, int p )  // get l,n node
    { 
        return pepepyramid.get( l ).get( p );
    }
    //------------------- getRoot() -----------------------
    /**
     * Get the root PyramidNode. 
     * 
     * @return PyramidNode
     */
    public PyramidNode<T> getRoot()  // get root node
    { 
        return pepepyramid.get( 0 ).get( 0 );
    }
    //-------------------------- isEmpty() ----------------------------
    /**
     * One way of making this test is to assume that the entire pyramid
     *    is "empty" if the top element does not contain valid data.
     * 
     * @return boolean    true if no valid data is in the pyramid.
     */
    public boolean isEmpty()
    {
        if( getRoot() == null )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * 
     * Add card to pyramid.
     * 
     * 
     * 
     */
    public void add()
    {
        for( int i = 0; i < rows; i++ )
        {
            for( int x = 0; x < pepepyramid.get( i ).size(); x++ )
            {
                
                PyramidNode<T> node2 = pepepyramid.get( i ).get( x );
                
                PyramidNode<T> child1 = null; //pepepyramid.get( i + 1 ).get( x );
                
                PyramidNode<T> child2 = null; //pepepyramid.get( i + 1 ).get( x + 1 );
                    
                //try
                //{
                    child1 = pepepyramid.get( i + 1 ).get( x );
                    
                    child2 = pepepyramid.get( i + 1 ).get( x + 1 );
                //}
                //catch( IndexOutOfBoundsException e )
                //{
                    child1 = null;
                    
                    child2 = null;
                //}
                
                
                //Set one to left, and the other to right.
                
                node2.makeLeft( child1 );
                
                node2.makeRight( child2 );
            }
            
        }
    }
    /**
     * Delete card method.
     * 
     * Removes the card from the pyramid.
     * 
     */
    public void delete( Card n )
    {
        
        for( int i = 0; i < rows; i++ )
        {
            for( int x = 0; x < pepepyramid.get( i ).size(); x++ )
            {
                
                Card pyramidC = (Card)pepepyramid.get( i ).get( x ).getData();
                
                if( pyramidC.getSuit() == n.getSuit() )
                {
                    if( pyramidC.getRank() == n.getRank() )
                    {
                        pepepyramid.get( i ).remove( x );
                        
                        x =  ( pepepyramid.get( i ).size() - 1 );
                        
                        i = ( rows - 1 );
                        
                        add();
                    }
                }
            }
        }
        
        
        
    }
    //--------------------------- main --------------------------------
    /**
     * This main is a convenience call to the application.
     * 
     * @param args String[]            command line arguments.
     */
    public static void main( String[] args )
    {
        // Invoke main class's main
        PyramidSolitaire.main( args );
    }
}
