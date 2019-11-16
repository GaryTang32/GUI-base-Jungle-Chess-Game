package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.Master_Class.BOARD_;
import hk.edu.polyu.comp.comp2021.jungle.View.Printing;

/**
 * PublcicBoard class used to the generate the board of the Jungel Game
 *  Board class also holds the location of the chess of the players
 *  and provide some basic function about the board and calculating validation about the move
 *  This class is the one of the important class in the game.
 *  Most logical decision is done in here including the moving and checking
 */
public class Board extends BOARD_ {
    private static final int HEIGHT = 11;
    private static final int WIDTH = 9;
    private static final int AnimalsPlane = 1;
    private static final int LandPlane = 0;
    private static final int layer = 2;

    /**
     * Public Contructor to initilialize the board of the Jungle Game and place the pieces on the starting position
     * @param A player A for the game
     * @param B player B for the game
     */
    public Board(Player A , Player B ){
        super(layer,HEIGHT,WIDTH);
        for (int i = 0 ; i < HEIGHT ; i++ ){
            for ( int o = 0 ; o < WIDTH ; o++){
                if( i == 0 || i == 10 || o == 0 || o == 8) board[LandPlane][i][o] = new Land("Wall");
                else if ( (i == 4 || i == 5 || i == 6) && (o == 2 || o == 3 || o == 5 || o == 6 ) ) board[LandPlane][i][o] = new Land("Water");
                else if ( ( i == 1 || i == 9 ) && ( o == 3 || o == 5) ) board[0][i][o] = new Land("Trap");
                else if ( ( i == 2 || i == 8 ) && o == 4 ) board[LandPlane][i][o] = new Land("Trap");
                else if ( ( i == 1 || i == 9 ) && o == 4 ) board[LandPlane][i][o] = new Land("Den");
                else board[0][i][o] = new Land("Land");
            }
        }

        for (int i = 0 ; i < HEIGHT ; i++ ){
            for ( int o = 0 ; o < WIDTH ; o++){
                board[AnimalsPlane][i][o] = Player.temp.getchess(1);
            }
        }

        board[AnimalsPlane][1][1] = A.getchess(7);
        board[AnimalsPlane][1][7] = A.getchess(6);
        board[AnimalsPlane][2][2] = A.getchess(3);
        board[AnimalsPlane][2][6] = A.getchess(2);
        board[AnimalsPlane][3][1] = A.getchess(1);
        board[AnimalsPlane][3][3] = A.getchess(5);
        board[AnimalsPlane][3][5] = A.getchess(4);
        board[AnimalsPlane][3][7] = A.getchess(8);

        board[AnimalsPlane][7][1] = B.getchess(8);
        board[AnimalsPlane][7][3] = B.getchess(4);
        board[AnimalsPlane][7][5] = B.getchess(5);
        board[AnimalsPlane][7][7] = B.getchess(1);
        board[AnimalsPlane][8][2] = B.getchess(2);
        board[AnimalsPlane][8][6] = B.getchess(3);
        board[AnimalsPlane][9][1] = B.getchess(6);
        board[AnimalsPlane][9][7] = B.getchess(7);

    }

    /**
     * Function used to wipe the board when loading save.
     */
    public void WipeBoard(){
        super.WipeBoard(1);
    }

    /**
     * function used to set up the Pieces in the board during the loading after the wipe
     * @param Y The Y position of the chess in the board
     * @param X The X position of the chess in the board
     * @param chess Chess that being set in the board
     */
    public void set_chess_on_bloard(int Y, int X, Pieces chess ){
        board[1][Y][X] = chess;
    }

    private Object getboard(int i, int o, int p ){
        return board[i][o][p];
    }

    private void change_chess(int A_X, int A_Y, int B_X, int B_Y ){
        board[AnimalsPlane][B_Y][B_X] = board[AnimalsPlane][A_Y][A_X];
        board[AnimalsPlane][A_Y][A_X] = Player.temp.getchess(1);
    }

    private void FIGHT(Pieces A ){
        A.chess_killed();
    }

    /**
     * Function used to actually move the chess in the board
     * @param A_X The from X location in the board
     * @param A_Y The form Y location in the board
     * @param B_X The to X location in the board
     * @param B_Y The to Y position in the board
     */
    public void move (int A_X, int A_Y, int B_X, int B_Y){
        Land A = getland_onBoard(A_Y,A_X);
        Land B = getland_onBoard(B_Y,B_X);
        Pieces D = getchess_onBoard(B_Y,B_X);
        int movement = A.move_possible(B);
        switch (movement){
            case 1 : case 2 :
                if ( D == Player.temp.getchess(1)){
                    change_chess(A_X,A_Y,B_X,B_Y);
                }
                else {
                    FIGHT(D);
                    change_chess(A_X,A_Y,B_X,B_Y);
                }
                break;
            case 3 : case 4 : case 5 : case 6:
                change_chess(A_X,A_Y,B_X,B_Y);
                break;
        }

    }

    /**
     * Function used to get the chess on the board  with given coordinate
     * @param i The X position of the board
     * @param o The Y position of the board
     * @return THe chess in the X,Y position on the board
     */

    public Pieces getchess_onBoard(int i, int o ){
        return (Pieces)getboard(1,i,o);
    }

    /**
     * Function used to get the land type on the board with given coordinate
     * @param i The X position of the board
     * @param o The Y position of the board
     * @return The land in the board with the given orrodinate
     */
    public Land getland_onBoard(int i, int o ){
        return (Land)getboard(0,i,o);
    }

    /**
     * Fucntion to check when moving if the starting position have no chess is invalid
     * @param A_X The X coordiante of the board
     * @param A_Y The Y coordinate of the board
     * @return Boolean value to indicate is the board is empty.
     */
    public boolean check_from_isempty(int A_X , int A_Y ){
        return getchess_onBoard(A_Y,A_X) == Player.temp.getchess(1);
    }

    /**
     * Fucntion to check when moving if the ending position have no chess
     * @param B_X   x-coordinate of the board
     * @param B_Y   y-coordinate of the board
     * @return Boolean value to indicate is the board location is empty.
     */
    public boolean check_to_isempty(int B_X, int B_Y){
        return getchess_onBoard(B_Y,B_X) == Player.temp.getchess(1);
    }

    /**
     * Public Method used to check if the player can move the chess to the specific location inputed by the player
     * @param A_X x-coordinate of the chess of from location
     * @param A_Y y-coordinate of the chess of from location
     * @param B_X x-coordinate of the chess of to location
     * @param B_Y y-coordinate of the chess of to location
     * @return True if the chess can move to the location, otherwise False
     */
    public boolean check_move_possible(int A_X , int A_Y, int B_X, int B_Y ){
        if ( check_jump_river(A_X , A_Y, B_X, B_Y) ){
            return true;
        }
        else if ( ( A_X - B_X == 1 || A_X - B_X == -1 ) && A_Y == B_Y ) {
            return true;
        }
        else return (A_Y - B_Y == 1 || A_Y - B_Y == -1) && A_X == B_X;
    }

    /** Public Method used to check if the rank of a chess is larger or equal to the rank of  another chess
     * @param A_X x-coordinate of the chess of from location
     * @param A_Y y-coordinate of the chess of from location
     * @param B_X x-coordinate of the chess of to location
     * @param B_Y y-coordinate of the chess of to location
     * @param turn_player  Unique Player who are currently on turn
     * @return  booelan expression about checking ranking
     *          True if the rank of a chess is larger or equal to the rank of another chess and have
     *          the same player , otherwise False
     *          if the destination chess is on the trap is always true
     *          if the from destinatino chess is on the trap is always false
     */
    public boolean check_Pieces_rank(int A_X , int A_Y, int B_X, int B_Y, Player turn_player){
        Pieces A = getchess_onBoard(A_Y,A_X), B;
        if ( getchess_onBoard(B_Y,B_X) == Player.temp.getchess(1) )
            if ( A.getPlayer_name().equals(turn_player.getPlayer_name()))return true;
            else {
                Printing.error(3);
                return false;
            }
        B = getchess_onBoard(B_Y,B_X);
        if ( !A.getPlayer_name().equals(turn_player.getPlayer_name()))return false;
        if (A.compare_Pieces_player(B)) {
            Printing.error(2);
            return false;
        }
        else if ( A.get_ontrap()) return false;
        else if ( B.get_ontrap()) return true;
        else return A.decide_fighting(B);
    }

    /** Public Method used to check if the chess can move to the land inputed by the player
     * @param A_X   x-coordinate of the chess of form location
     * @param A_Y   y-coordinate of the chess of from location
     * @param B_X   x-coordinate of the chess of to location
     * @param B_Y   y-coordinate of the chess of to location
     * @return  True if the chess can move to the land , otherwise False
     *          All cases are devided into different cases and each case have different things to check
     *          and have different special condition such as only Rat can move from land to water
     */
    public boolean check_land_possible (int A_X , int A_Y, int B_X, int B_Y){
        Land A = getland_onBoard(A_Y,A_X);
        Land B = getland_onBoard(B_Y,B_X);
        Pieces temp = getchess_onBoard(A_Y,A_X);
        switch (A.move_possible(B)){
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                temp.going_into_the_trap();
                return true;
            case 3:
                return (temp.getPlayer_num() == 1 && B_Y == 9) || temp.getPlayer_num() == 2 && B_Y == 1;
            case 4:
                return temp.getrank() == 1 && getchess_onBoard(B_Y, B_X).getrank() != temp.getrank();
            case 5:
                temp.going_out_of_trap();
                return true;
            case 6 :
                if ( getchess_onBoard(B_Y,B_X) != Player.temp.getchess(1)  ) return false;
                else return true ;
            default:
                return false;
        }
    }

    /** Public Method used to check if the chess can jump over the river inputed by the player
     * @param A_X   x-coordinate of the chess of form location
     * @param A_Y   y-coordinate of the chess of from location
     * @param B_X   x-coordinate of the chess of to location
     * @param B_Y   y-coordinate of the chess of to location
     * @return  True if the chess can jump over the river, otherwise False
     *          Test the rank of the pieces to know is such jumping is possible or not
     *          then check the middle location one by one between the from and to
     *          if that is not am water, return false.
     *          if there is pieces in the middle return false.
     *          if passed all return true.
     */
    private boolean check_jump_river(int A_X , int A_Y, int B_X, int B_Y){
        Pieces temp = getchess_onBoard(A_Y,A_X);
        Land temp1 ;
        Pieces temp2;
        int rank = temp.getrank();
        if ( rank != 6 && rank != 7 ) return false;
        else if ( A_X == B_X ){
            if ( A_Y - B_Y == -4 ){
                for ( int i = 1 ; i < 4 ; i++ ){
                    temp1 = getland_onBoard(A_Y+i,A_X);
                    temp2 = getchess_onBoard(A_Y+i,A_X);
                    if ( !temp1.get_land_name().equals("Water")) return false;
                    else if (temp2 != Player.temp.getchess(1) ) return false;
                }
                return true;
            }
            else if ( A_Y - B_Y == 4 ){
                for ( int i = 1 ; i < 4 ; i++ ){
                    temp1 = getland_onBoard(A_Y-i,A_X);
                    temp2 = getchess_onBoard(A_Y-i,A_X);
                    if ( !temp1.get_land_name().equals("Water")) return false;
                    else if (temp2 != Player.temp.getchess(1) ) return false;
                }
                return true;
            }
            else return false;
        }
        else if ( A_Y == B_Y ){
            if ( A_X - B_X == 3 ){
                for ( int i = 1 ; i < 3 ; i++ ){
                    temp1 = getland_onBoard(A_Y,A_X-i);
                    temp2 = getchess_onBoard(A_Y,A_X-i);
                    if ( !temp1.get_land_name().equals("Water")) return false;
                    else if (temp2 != Player.temp.getchess(1) ) return false;
                }
                return true;
            }
            else if ( A_X - B_X == -3 ){
                for ( int i = 1 ; i < 3 ; i++ ){
                    temp1 = getland_onBoard(A_Y,A_X+i);
                    temp2 = getchess_onBoard(A_Y,A_X+i);
                    if ( !temp1.get_land_name().equals("Water")) return false;
                    else if (temp2 != Player.temp.getchess(1) ) return false;

                }
                return true;
            }
            else return false;
        }
        return false;
    }


}
