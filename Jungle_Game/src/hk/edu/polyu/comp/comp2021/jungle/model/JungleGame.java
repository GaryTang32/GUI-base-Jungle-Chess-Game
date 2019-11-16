package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.View.Printing;

import java.io.*;

/** Public JungleGame class used to play the Jungle Game
 * The game provide normal function in the game suchs as saving, loading, moving and check the input
 */
public class JungleGame {

    private Board ChessBoard ;
    private Player A;
    private Player B;


    /**
     * Starting the jungle gmae with the gui based setting
     * and the default setting
     * @param name the first player's name
     * @param name1 the second player's name
     */
    public JungleGame(String name, String name1 ){
        A = new Player(name,1);
        B = new Player(name1,2);
        ChessBoard = new Board(A,B);
    }


    /**
     * temp start to let the input possible before user input any name
     */
    public JungleGame(){

    }


    /**
     * temp start to let the input possible before user input any name
     * This is also for the user loading the gmae from the start
     * @param i Temp parameter to separate this with the other constuctor
     */
    public JungleGame(String i ){
        A = new Player("A",1);
        B = new Player("B",2);
        ChessBoard = new Board(A,B);
    }


    /**
     * Function to get the first player in the jungle game
     * @return the first player in the game
     */
    public Player getplayerA(){
        return this.A;
    }

    /**
     * Function used to get the second player of the jungle game
     * @return The second player in the gmae
     */
    public Player getplayerB(){
        return this.B;
    }

    /** Public Method used to return the chessboard of the Jungle Game
     * @return The board of the Jungle Game
     */
    public Board getBoard(){
        return this.ChessBoard;
    }

    /**
     * function used to reset the player name during the loading save
     * @param name The player's name
     * @param num The player's number
     */
    public void set_newplayer(String name, int num ){
        if ( num == 1 ){
            this.A = new Player(name,num);
        }
        else this.B = new Player(name,num);
    }

    /** Publice Method used to check if the input x and y coordinate are valid
     * @param X x-coordinate of the board
     * @param Y y-coordinate of the board
     * @return True if the x and y coordinate are inside the board, otherwise False
     */
    public boolean check_input_valid( int X , int  Y ){
        if (X < 1 || X > 7 ) return false;
        else return Y >= 1 && Y <= 9;
    }

    /** Public Method used to check if the chesses on the board are valid or not
     * @param A The player who made the move
     * @return True if valid, otherwise False
     */
    public boolean check_board(Player A){
        if ( getplayerA() == A ){
            if (getplayerA().check_is_empty())  return true;
        }
        else {
            if (getplayerB().check_is_empty()) return true;
        }
        return ChessBoard.getchess_onBoard(1, 4) != Player.temp.getchess(1) || ChessBoard.getchess_onBoard(9, 4) != Player.temp.getchess(1);
    }

    /** Public Method used to check if the chess can move from its position to the destination inputed by the player
     * @param A Starting position of the chess
     * @param B Destination of the chess
     * @param turn_player   Unique Player who are taking turn
     * @return True if the chess successfully moved to the destination, otherwise False
     *         first will fetch the input string and then check the moving is it possible
     *         if such move is possible then will move the chess.
     *         other wise will print the error message and return false.
     */
    public boolean move_chess(String A, String B, Player turn_player){
        final int CHARACTERTOINT = 64;
        final int INTCHARTOINT = 48;
        if ( A.length() != 2 || B.length() != 2 ){
            Printing.error(6);
            return false;
        }
        int A_X = (int)Character.toUpperCase(A.charAt(0)) - CHARACTERTOINT;
        int A_Y = (int)(A.charAt(1)) - INTCHARTOINT;
        int B_X = (int)Character.toUpperCase(B.charAt(0)) - CHARACTERTOINT;
        int B_Y = (int)(B.charAt(1)) - INTCHARTOINT;
        A_Y = 10 - A_Y;
        B_Y = 10 - B_Y;
        if ( !check_input_valid(A_X,A_Y)){
            Printing.error(6);
            return false;
        }
        if ( !check_input_valid(B_X,B_Y)){
            Printing.error(6);
            return false;
        }
        if ( ChessBoard.check_from_isempty(A_X,A_Y)){
            Printing.error(7);
            return false;
        }
        else if (ChessBoard.check_move_possible(A_X,A_Y,B_X,B_Y) ) {
            if (ChessBoard.check_to_isempty(B_X, B_Y)) {
                if (ChessBoard.check_Pieces_rank(A_X,A_Y,B_X,B_Y,turn_player)){
                    if (ChessBoard.check_land_possible(A_X,A_Y,B_X,B_Y)){
                        ChessBoard.move(A_X,A_Y,B_X,B_Y);
                        return true;
                    }
                    else return false;
                }
                else return false;

            }
            else if (ChessBoard.check_Pieces_rank(A_X,A_Y,B_X,B_Y,turn_player)){
                if (ChessBoard.check_land_possible(A_X,A_Y,B_X,B_Y)){
                    ChessBoard.move(A_X,A_Y,B_X,B_Y);
                    return true;
                }
                else return false;
            }
            else {

                return false;
            }
        }
        else {
            Printing.error(8);
            return false;
        }

    }

    /** Public Method used to open a saved file and continue the saved Jungle Game
     *  and this is for the command line mode
     * @param file_path The location of the saved file
     * @param game  The Jungle Game that being overwrite with the save
     * @return The saved Jungle Game's player who is playing at that turn
     * @throws IOException If the file does not exists or the path is incorrect
     */
    public Player open_file(String file_path, JungleGame game) throws IOException {
        FileReader file;
        String nameA , nameB , line;
        String[] temp;
        Player temp_player;
        Pieces temp_chess;
        if (file_path.equals("")){
            file = new FileReader("save.txt");
        }
        else file = new FileReader(file_path);
        BufferedReader read = new BufferedReader(file);
        nameA = read.readLine();
        nameB = read.readLine();
        game.set_newplayer(nameA, 1);
        game.set_newplayer(nameB, 2);
        game.getBoard().WipeBoard();
        line = read.readLine();
        while ( line.split(" ").length != 1){
            temp = line.split(" ");
            if ( temp[0].equals(game.getplayerA().getPlayer_name()) ) temp_player = game.getplayerA();
            else temp_player = game.getplayerB();
            temp_chess = temp_player.getchess(Integer.parseInt(temp[1]));
            if ( Integer.parseInt(temp[2]) == 0 ) temp_chess.chess_killed();
            else game.getBoard().set_chess_on_bloard(Integer.parseInt(temp[2]),Integer.parseInt(temp[3]),temp_chess);
            line = read.readLine();
        }
        if ( line.equals(nameA) ) return game.getplayerA();
        else  return game.getplayerB();
    }

    /** Public Method used to save the current Jungle Game
     * @param file_path The location for saving the game
     * @param A The first Player
     * @param B The second Player
     * @param turn  Which player's turn
     * @throws IOException  If the path is incorrect
     */
    public void saving (String file_path, Player A, Player B, Player turn ) throws IOException {
        File file ;
        file = new File(file_path);
        BufferedWriter save = new BufferedWriter(new FileWriter(file));
        Pieces temp;
        String name ;
        int rank , x , y;
        save.write(A.getPlayer_name()+"\n");
        save.write(B.getPlayer_name()+"\n");
        for (int i = 1 ; i < 10 ; i++ ) {
            for (int o = 1; o < 8; o++) {
                temp = getBoard().getchess_onBoard(i, o);
                if ( temp != Player.temp.getchess(1)) {
                    name = temp.getPlayer_name();
                    rank = temp.getrank();
                    y = i;
                    x = o;
                    save.write(name + " "+ rank +" "+ y +" "+x +"\n");
                }
            }
        }
        for (int o = 1 ; o < 9 ; o++) {
            if( A.is_chess_dead(o)) save.write(A.getPlayer_name() + " "+ o +" 0 0 \n");
            if( B.is_chess_dead(o)) save.write(B.getPlayer_name() + " "+ o +" 0 0 \n");
        }
        save.write(turn.getPlayer_name());
        save.close();
    }



}
