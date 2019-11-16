package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.Master_Class.PLAYER_;


/** Public Player class extends from PLAYER_
 *  Player class store the information of the players
 *  and provide function related to the player and player's chess
 */
public class Player extends PLAYER_ {

    private final Pieces[] chess;

    /** Public Constructor to initilalize the player
     * @param name  Player's name
     * @param num   Player's unique number
     */
    public Player(String name, int num){
        super(name,num);
        this.chess = new Pieces[8];
        for (int i = 0 ; i < 8 ; i++){
            chess[i] = new Pieces(i+1, this);
        }
    }

    /** Public Final Static Field of a temp Player
     * this temp player have only one chess which is the null chess.
     * so the board will not have empty slot and always have chess in it
     */
    public final static Player temp = new Player(9);


    /** Public Constructor to assign the chess to the temp player
     *  this constuctor only create a player with only one chess
     * @param i Player's unique number
     */
    public Player(int i ){
        super(null, 0);
        this.chess = new Pieces[1];
        chess[0] = new Pieces(i , this);

    }

    /** Public Method used to get the chess of a player
     * @param index Chess's rank
     * @return  Player's chess according to the rank
     */
    public Pieces getchess(int index){
        return this.chess[index-1];
    }


    /** Public Method used to kill the chess
     * @param index Chess's index that being killed
     */
    public void  killed(int index ){
        set_killed(index);
    }

    /** Private Method used to set the chess killed by saving the index of the chesslist with the empty chess
     * @param index Chess's index
     */
    private void set_killed(int index){
        chess[index-1] = Player.temp.getchess(1);
    }

    /** Public Method used to check if the chess of a player is dead if it is dead then will equals to the null chess
     * @param rank Chess's rank
     * @return  True if the chess is dead, otherwise False
     */
    public boolean is_chess_dead(int rank){
        return chess[rank-1] == Player.temp.getchess(1);
    }

    /** Public Method used to check if the chess of a player is empty
     * @return True if the chess of a player is empty, otherwise False
     */
    public boolean check_is_empty(){
        for (int i = 0 ; i < 8 ; i++){
           if  (chess[i] != Player.temp.getchess(1) ) return false;
        }
        return true;
    }
}
