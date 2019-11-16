package hk.edu.polyu.comp.comp2021.jungle.Master_Class;


import hk.edu.polyu.comp.comp2021.jungle.model.Player;

/**
 * Master class of the class board
 * this class is used to plavce the general information of a board.
 */
public class BOARD_ {

    /**
     *  The board that used in the game.
     */
    protected final Object[][][] board ;
    private final int height;
    private final int width;

    /**
     * The constructor to initialize and instance the board with the given diamention.
     * @param layger    the layer of the board
     * @param height    the vertical diamension of the board
     * @param width     the horizontal diamension of the board
     */
    protected BOARD_(int layger, int height, int width){
        board = new Object[layger][height][width];
        this.height = height;
        this.width = width;
    }


    /**
     * When the player want to load the save first we need to wipe the board and start loading
     * @param p wipe the desired layer of the board
     */
    public void WipeBoard(int p){
        for (int i = 0 ; i < height ; i++ )
            for ( int o = 0 ; o <width ; o++)
                board[p][i][o] = Player.temp.getchess(1);
    }

}
