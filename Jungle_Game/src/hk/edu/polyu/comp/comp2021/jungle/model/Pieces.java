package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.Master_Class.CHESS;
import java.util.HashMap;
import java.util.Map;

/** Public Pieces class extends from CHESS
 *  Pieces class used to save the rank of the chesses
 *  And provide some function to get and set and Pieces related fucntion calculation
 */
public class Pieces extends CHESS{

    private final Piece chess ;
    private boolean on_trap ;

    private enum Piece  {
        Rat(1), Cat(2), Dog(3), Wolf(4), Leopard(5),Tiger(6),Lion(7), Elephant(8), NULL(9) ;

        Piece(int rank) {
            setrank(rank);
        }

        private static Piece getpiece(int rank) {
            if (PieceMap == null) {
                PieceMap = new HashMap();
                for (Piece p : Piece.values()) {
                    PieceMap.put(p.getrank(), p);
                }
            }
            return PieceMap.get(rank);
        }

        public int getrank(){
            return this.rank;
        }

        private void setrank(int rank){
            this.rank = rank;
        }

        private int rank;
        private static Map<Integer, Piece> PieceMap;
    }

    /** Public Constructor to initialize the piece
     * @param rank    The rank of the piece
     * @param player  Which player the piece belongs to
     */
    public Pieces(int rank, Player player){
        super(player);
        this.chess = Piece.getpiece(rank);
        this.on_trap = false;
    }

    /**
     *
     */
    public void going_into_the_trap(){
        this.on_trap = !this.on_trap;
    }

    /** Public Method used to set the flag of the chess if the chess is going into the trap
     * and can be eaten by all the oppoent chesses.
     */
    public void going_out_of_trap(){
        this.on_trap = !this.on_trap;
    }


    /** Public Method used to get the rank of a chess
     * @return  The rank of a chess
     */
    public int getrank(){
            return this.chess.getrank();

    }

    /** Public Method used to set the chess was killed by another player
     *  and remove the chess from baord
     */
    public void chess_killed(){
        getPlayer().killed(this.getrank());
    }

    /** Public Method used to return the name of the player of the chess
     * @return  Player's names
     */
    public String getPlayer_name(){
        return getPlayer().getPlayer_name();
    }

    /** Public Method used to check if the player has that piece
     * @param A The type of piece
     * @return  True if the player has, otherwise False
     */
    public boolean compare_Pieces_player(Pieces A ){
        return this.getPlayer_name().equals(A.getPlayer_name());
    }

    /** Public Method used to check if the piece can kill another piece
     * @param B The pieces that going to comapre
     * @return  True if the piece can kill another piece, otherwise False
     */
    public boolean decide_fighting( Pieces B ){
        if (B.get_ontrap()) return true;
        if ( getrank() >= B.getrank() && getrank() - B.getrank() != 7 ) return true;
        else return getrank() - B.getrank() == -7;

    }

    /**
     * Fuction used to get the ontrap flag of the chess.
     * @return true if the chess is currently in the trap, otherwise false
     */
    public boolean get_ontrap(){
        return this.on_trap;
    }

    /** Public Method used to covert the chess and the rank to a String for printing the board in command line
     * @param rank The integer rank of the piece
     * @return The String of the pieces with name and rank
     */
    public String tostirng(int rank){
        Piece temp = Piece.getpiece(rank);
        String temp1= temp+"";
        if ( temp1.length() < 4 ) {
            temp1 = " "+temp1.substring(0,3);
        }
        else temp1 = temp1.substring(0,4);
        return temp1;
    }


}
