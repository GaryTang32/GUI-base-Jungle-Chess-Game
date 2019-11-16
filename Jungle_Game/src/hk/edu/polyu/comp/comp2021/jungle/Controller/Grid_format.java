package hk.edu.polyu.comp.comp2021.jungle.Controller;

import hk.edu.polyu.comp.comp2021.jungle.model.*;

/**
 * Class for printing the command line class's block
 */
public class Grid_format {

    /**
     * Public Static Method used to print the chesses and the land
     * @param i Chess's rank
     * @param chess Player's chess
     * @param land  Land's type
     * @return the string being printed in the board
     */
    public static String print_chess(int i , Pieces chess, Land land){
        String ans = "";
        switch (i){
            case 0:
                switch (land.get_land_name()){
                    case ("Wall"): ans = " ##### "; break;
                    case ("Water"): ans = " ~~~~~ "; break;
                    case ("Land"): ans = " _____ "; break;
                    case ("Trap"): ans = " !!!!! "; break;
                    case ("Den"): ans = " OOOOO "; break;

                }

                break;
            case 1:
                if ( land.get_land_name().equals("Wall")) {
                    ans = "#######" ;
                    break;
                }
                if ( land.get_land_name().equals("Water")) {
                    if ( chess.getrank() != 9 ){
                        ans = "~"+chess.getrank()+chess.tostirng(chess.getrank())+"~";
                    }
                    else ans = "~     ~";
                    break;
                }
                if ( land.get_land_name().equals("Land")){
                    if ( chess.getrank() != 9 ){
                        ans = "|"+chess.getrank()+chess.tostirng(chess.getrank())+"|";
                    }
                    else ans = "|     |";
                    break;
                }
                if ( land.get_land_name().equals("Trap")){
                    if ( chess.getrank() != 9 ){
                        ans = "!"+chess.getrank()+chess.tostirng(chess.getrank())+"!";
                    }
                    else ans = "!     !";
                    break;
                }

                if ( chess.getrank() != 9 ){
                    ans = "O"+chess.getrank()+chess.tostirng(chess.getrank())+"O";
                }
                else ans ="O     O";
                break;
            case 2:
                if ( chess.getrank() != 9 ){
                    if ( land.get_land_name().equals("Wall")) ans = "#######";
                    else if ( land.get_land_name().equals("Water")) ans = "~  "+chess.getPlayer().getPlayer_num()+"  ~";
                    else if ( land.get_land_name().equals("Land")) ans = "|  "+chess.getPlayer().getPlayer_num()+"  |";
                    else if ( land.get_land_name().equals("Trap")) ans = "!  "+chess.getPlayer().getPlayer_num()+"  !";
                    else ans = "O  "+chess.getPlayer().getPlayer_num()+"  O";
                }
                else{
                    if ( land.get_land_name().equals("Wall")) ans = "#######";
                    else if ( land.get_land_name().equals("Water")) ans = "~     ~";
                    else if ( land.get_land_name().equals("Land")) ans = "|     |";
                    else if ( land.get_land_name().equals("Trap")) ans = "!     !";
                    else ans = "O     O";
                }
                break;
            case 3:
                if ( land.get_land_name().equals("Wall")) ans = " ##### ";
                else if ( land.get_land_name().equals("Water")) ans = " ~~~~~ ";
                else if ( land.get_land_name().equals("Land")) ans = "|_____|";
                else if ( land.get_land_name().equals("Trap")) ans = " !!!!! ";
                else ans = " OOOOO ";
                break;

        }
        return ans;
    }
}
