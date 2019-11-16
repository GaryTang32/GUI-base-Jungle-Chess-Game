package hk.edu.polyu.comp.comp2021.jungle.Controller;

import hk.edu.polyu.comp.comp2021.jungle.model.JungleGame;
import hk.edu.polyu.comp.comp2021.jungle.model.Land;
import hk.edu.polyu.comp.comp2021.jungle.model.Pieces;
import hk.edu.polyu.comp.comp2021.jungle.model.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This calss is used to do the loading  of the Jungle game in graphic mode
 * Because the saving is the same as the commandline mode so they share the same function
 */
class Jungle_graphic_save_load {

    /**
     *  Function used to load the saved Jungle game from the file of the GUI mode.
     * @param file_path The file path that used to load game
     * @param game The JungleGame object of the entire game
     * @param cons The JungleConsole object
     * @return The player who is taking turn when saving
     * @throws IOException If the path is incorrect or doesnt existed
     */
    public Player open_file_gui(String file_path, JungleGame game, JungleConsole cons) throws IOException {
        FileReader file;
        String nameA , nameB , line;
        String[] temp;
        Player temp_player;
        Pieces temp_chess;
        int chess_index ;
        final int player_offset = 8 ;
        if (file_path.equals("")){
            file = new FileReader("save.txt");
        }
        else file = new FileReader(file_path);
        BufferedReader read = new BufferedReader(file);
        nameA = read.readLine();
        nameB = read.readLine();
        game.set_newplayer(nameA, 1);
        game.set_newplayer(nameB, 2);
        game.getBoard() .WipeBoard();
        line = read.readLine();
        while ( line.split(" ").length != 1){
            chess_index = 0 ;
            temp = line.split(" ");
            if ( temp[0].equals(game.getplayerA().getPlayer_name()) ) {
                chess_index += player_offset;
                temp_player = game.getplayerA();
            }
            else {
                temp_player = game.getplayerB();
            }
            temp_chess = temp_player.getchess(Integer.parseInt(temp[1]));
            chess_index += temp_chess.getrank();
            if ( Integer.parseInt(temp[2]) == 0 && Integer.parseInt(temp[3]) == 0 ) {
                temp_chess.chess_killed();
                cons.getGraph().delete(chess_index);
            }
            else {
                cons.getgame().getBoard().set_chess_on_bloard(Integer.parseInt(temp[2]),Integer.parseInt(temp[3]),temp_chess);
                if (cons.getgame().getBoard().getland_onBoard(Integer.parseInt(temp[2]),Integer.parseInt(temp[3])).getland() == Land.getLandType("Trap")  )
                    temp_chess.going_into_the_trap();
                cons.getGraph().move(chess_index,Integer.parseInt(temp[3])-1,Integer.parseInt(temp[2])-1);
            }
            line = read.readLine();
        }
        if ( line.equals(nameA) ) return game.getplayerA();
        else  return game.getplayerB();
    }





}
