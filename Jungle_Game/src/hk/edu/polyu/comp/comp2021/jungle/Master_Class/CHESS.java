package hk.edu.polyu.comp.comp2021.jungle.Master_Class;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

/**
 * Public master class CHESS extends from Object
 * the class is used to place genral information of the chess of the game.
 */
public class CHESS  extends Object {

    private final Player player ;

    /**
     * Public Constructor to initialize the CHESS object
     * @param A The player who owns the chess.
     */
    protected CHESS(Player A){
        this.player = A;
    }

    /** Public Method returns a Player object
     *  @return  Unique Player who owns the chess
     */
    public Player getPlayer(){
        return this.player;
    }

    /**
     * Public Method returns the number of  a Player
     * @return Player's number in the game
     */
    public int getPlayer_num(){
        return getPlayer().getPlayer_num();
    }





}

