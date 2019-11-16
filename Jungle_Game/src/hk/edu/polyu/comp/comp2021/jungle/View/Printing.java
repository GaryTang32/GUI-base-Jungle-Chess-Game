package hk.edu.polyu.comp.comp2021.jungle.View;

import hk.edu.polyu.comp.comp2021.jungle.model.*;
import hk.edu.polyu.comp.comp2021.jungle.Controller.*;


/**
 *
 */
public class Printing {

    /** Public Static Method used to decide what error messages to be printed
     * @param errorcode Errorcode's number
     */
    public static void error (int errorcode ){
        printerror(errorcode);
    }

    /** Public Static Method used to decided what messages to be printed
     * @param printcode Printcode's number
     */
    public static void print(int printcode ){
        switch (printcode){
            case 1:
                System.out.println("The saveing command format should be : Save [path to save] ");
                break;
            case 2:
                System.out.println("The open file command format should be : Open [path to open] ");
                break;
            case 3:
                System.out.println("Moving chess command format should be : Move [from] [to]  / drag pieces for GUI");
                break;
            case 4 :
                System.out.println("Game successfully saved !");
                break;
            case 5 :
                System.out.println("Game successfully loaded!");
                break;
        }
    }

    /** Publci Static Method used to decide what error messages to be printed
     * @param errorcode actual error code for this specific project.
     *                  each code refer to a specific error occurs in the program.
     */
    private static void printerror(int errorcode ) {
        switch (errorcode){
            case 4:
                System.out.println("You can not move other player chess! ");
                break;
            case 5:
                System.out.println("You can not eat your own chess! ");
                break;
            case 6:
                System.out.println("Please input a valid destination code for moving chess! ");
                break;
            case 7:
                System.out.println("There is no chess in the moving point! ");
                break;
            case 8:
                System.out.println("move not possible! ");
                break;
            case 9 :
                System.out.println("Rank differernt is not valid ");
                break;
            case 10 :
                System.out.println("Incorrect input command ! ");
                break;
            case 1 :
                System.out.println("Can not open file please check the path is correct ! ");
                break;
            case 2 :
                System.out.println("cannot eat your own chess.");
            case 3 :
                System.out.println("You can not move other people's chess");

        }
    }

    /**
     * Fucntion to actual print the line to the user in the command line interface
     * @param s String being printed
     */
    public static void printing(String s ){
        System.out.println(s);
    }

    /**
     * Public Static Method used to print the help messages if the user inputs the help commnad
     * @return The string of the help content
     */
    public static String printhelp(){
        return "\n\nWelcome to the Jumgle Game developed by group 34\n"+
        "Command List : \n"+
        "For Movement : Move [from] [to]\n"+
        "For help : Help \n"+
        "For saving : Save [path] (input a space in path for using default file and replace \\ with \\\\ ) \n"+
        "For loading : Open [path] (input a space in path for using default file and replace \\ with \\\\ )\n"+
        "For surredner : surrender \n"+
        "For renew_game : renew_game\n"+
        " OOOOO   Is the Den        !!!!!  This is   _____  this is   #####   this is wall ~~~~~  This is river\n" +
        "O     O  the goal of game !     ! the trap |     | the land #######              ~     ~   \n"+
        "O     O                   !     !          |     |          #######              ~     ~  \n"+
        " OOOOO                     !!!!!           |_____|           #####                ~~~~~ \n "+
        "First player on top second player on bottom.\n\n";
    }

    /**
     * Public Static Method used to print the help messages if the user pressed the help button
     * @return The string that being printted
     */
    public static String printhelp_GUI(){
        return "\n\nWelcome to the Jumgle Game developed by group 34\n"+
                "Command List : \n"+
                "For Movement : drag the pieces in the board "+ "For help : click help buttom \n"+
                "For saving : click save default buttom for default save file and Save button for user input path\n"+
                "For loading : click Load default buttom fopr default save file and Load button for user input path\n"+
                "If input is needed please use console!\n"+
                "For surredner : click surrender \n"+
                "For renew_game : click the new_game button\n"+
                "First player on top and second player on bottom.\n\n";
    }



    /**
     * Public Static Method used to print the welcome messages when the user starts running the Jungle Game
     */
    public static void printwelcome(){
        System.out.println("     __________    ___    __      ___    __      ______     ___           ________    ");
        System.out.println("     |__    ___|  |  |   |  |    |   \\  |  |    /  __   \\   |  |         |  ______|   ");
        System.out.println("        |  |      |  |   |  |    |    \\ |  |   |  /  |__|   |  |         |  |         ");
        System.out.println("        |  |      |  |   |  |    |     \\|  |   | |  ____    |  |         |  |_____    ");
        System.out.println("        |  |      |  |   |  |    |  |\\     |   | | |_   |   |  |    __   |   _____|   ");
        System.out.println("    --\\ /  /      |  \\__/   /    |  |  \\   |   |  \\__|  /   |  |___|  |  |  |_____    ");
        System.out.println("    \\_____/        \\_______/     |__|   \\ _|    \\______/    |_________|  |________|   ");
        System.out.println();
        System.out.println("                    _______         _____        __        ___      ________       " );
        System.out.println("                   /  ___  |       /  __  \\     |  \\      /   |    |  ______|      " );
        System.out.println("                  |  /   \\_|      |  |  |  |    |   \\    /    |    |  |            " );
        System.out.println("                  | |    ___      |  |__|  |    | |\\ \\  /  /| |    |  |_____       " );
        System.out.println("                  | |   |_  |     |   __   |    | | \\ \\/  / | |    |   _____|     ");
        System.out.println("                  |  \\___| |      |  |  |  |    | |  \\   /  | |    |  |_____      ");
        System.out.println("                   \\_______/      |__|  |__|    |_|   \\_/   |_|    |________|     ");
    }

    /**
     *  Public Static Method used to print the option messages when the user starts running the Jungle Game
     */
    public static void print_starting_option(){
        System.out.println();
        System.out.println();
        System.out.println("                 THIS IS A COMMAND LINE GAME PLEASE INPUT THE COMMAND ");
        System.out.println("                         PLEASE INPUT THE RHS COMMAND BELOW ");
        System.out.println("                           START NEW GAME : new_game");
        System.out.println("                   START NEW GAME WITH GUI: new_game_gui");
        System.out.println("                                LOAD FILE : load_game [filepath] (empty for default save)");
        System.out.println("                                 GET HELP : help (game command)");
        System.out.println("                                GAME RULE : rule (general game rule)");
        System.out.println("                                     QUIT : quit");
        System.out.println();
        System.out.println();
    }

    /**
     *  Public Static Method used to print the rule messages if the user inputs the Rule commnad
     *  @return The string that being printted which is the rule content
     */
    public static String game_rule(){
        return "\n\nThe Jungle Game : "+
        "A jungle game is a chinese traditional game. Each player have 8 chesses on board\n"+
        "And for each chess it have a rank and the bigger rank can eat the chess with smaller rank.\n"+
        "The rank and the name of the animals are: Rat(1), Cat(2), Dog(3), Wolf(4), Leopard(5),Tiger(6),Lion(7), Elephant(8)\n"+
        "But Rat can eat the Elephant while Elephant is not allow to eat the Rat. \n"+
        "Rat can get into the river. Tiger and Lion can jump over the river when there is no Rat in the middle of the path.\n"+
        "Animals that get into the Trap will ranked down to zero. All opponent chess can eat that chess.\n"+
        "The first player that move a chess into the opponent den will decided as the Winner !\n "+
        "The game run in a turn base system only a valid move will lead to the next tern.\n"+
        "Saving in allow in this game while the path entered should be a txt file.\n"+
        "Loading game will overwrite the current game and the player name.\n\n";
    }


    /**
     * Public Static Method used to print the end messages if the one of the player wins the Jungle Game
     * @return The string that being printted which is the ending mesage
     */
    public static String game_end(){
        return "\n\n~~~~~~~~~~~~~ Thanks for playing the game ~~~~~~~~~~~~~ \n"+"         ~~~~~~~~~~~~~ GOOD BYE ~~~~~~~~~~~~~ \n\n";

    }

    /** Public Static Method used to print messages for the next player's turn
     * @param A The next player take turn
     */
    public static void printnext(Player A){
        System.out.println(A.getPlayer_name()+"'s turn : Please input the next command : ");
    }

    /**
     * Public Static Method used to print the ask_save messages if the user have not save the game
     * @return The string that being printted which ask the user for saving
     */
    public static String ask_save(){
        return "You havent save the game yet ! want save before quitting or loading? Save [file path] or N ";
    }

    /** Public Static Method used to print the win_game messages if the one of the players win the Jungle Game
     * @param winner    Player who have win the game
     */
    public static void win_game(Player winner){
        System.out.println("Congratulations ! "+ winner.getPlayer_name()+" has win the game !");
    }

    /** Public Static Method used to print the board of the Jungle game in the turn.
     * @param game The Jungle Game's board
     */
    public static void printboard(JungleGame game){
        Land temp ;
        Pieces temp1 ;
        final int LENGTH = 11;
        final int WIDTH = 9 ;
        final int BLOCK = 4;
        final int UPPPERWALL = 0;
        final int LOWERWALL = 10;
        for ( int i  = 0 ; i < LENGTH ; i ++ ){
            for ( int o = 0 ; o < BLOCK; o++){
                for ( int p = 0 ; p < WIDTH ; p++) {
                    temp = game.getBoard().getland_onBoard(i,p);
                    temp1 = game.getBoard().getchess_onBoard(i,p);
                    System.out.print(Grid_format.print_chess(o,temp1,temp));
                }
                if ( (i) != UPPPERWALL && (i) != LOWERWALL && o % 4 == 2 ){
                    System.out.println(" "+(10 - i));
                }
                else System.out.println();
            }
        }
        System.out.println("          A      B      C      D      E      F      G");

    }



}
