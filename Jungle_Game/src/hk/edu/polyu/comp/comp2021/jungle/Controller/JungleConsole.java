package hk.edu.polyu.comp.comp2021.jungle.Controller;
import hk.edu.polyu.comp.comp2021.jungle.View.INPUT_CLASS;
import hk.edu.polyu.comp.comp2021.jungle.View.JungleGameGraphic;
import hk.edu.polyu.comp.comp2021.jungle.View.Printing;
import hk.edu.polyu.comp.comp2021.jungle.model.*;
import javafx.application.Application;

import java.io.IOException;

/**
 * Public JungleConsole class for the user to start playing the Jungle Game
 * And this is the branching class
 */
public class JungleConsole {

    private JungleGame game ;
    private boolean playing = true ;
    private Player player_on_turn ;
    private boolean first_command = true ;
    private boolean saved = false ;
    private JungleGameGraphic graph ;
    private final Jungle_graphic_save_load temp;

    /**
     *  booelan indicator to let the program know it is gui mode or the command line mode
     */
    private static boolean GUI_indicator = false;

    /** Public Constructor to initialize the JungleConsole
     * @param game The Jungle Game
     */
    public JungleConsole(JungleGame game){
        this.game = game;
        this.player_on_turn = game.getplayerA();
        this.temp = new Jungle_graphic_save_load();
    }

    /**
     *  Chnage the status of the indicator to let the programme know the game is in GUI mode
     */
    public static void changeGUI_indicator(){
        GUI_indicator = true;
    }

    /**
     * To get the gui indicator which represent the mode of game true mean using GUI
     * false mean using command line
     * @return get the gui indicator of the game
     */
    private boolean getGui_indicator(){
        return GUI_indicator;
    }


    /**
     * Public Enum Command_key class consists of different commands that the user can use
     */
    public enum Command_key {

        /**
         * Save the curretn Jungle Game
         */
        Saving("save"),
        /**
         * Open and continue a saved Jungle Game
         */
        Open_saving("open"),
        /**
         * Ask the progamme for help about how to run the progamme
         */
        Get_help("help"),
        /**
         * Moving the chess
         */
        Move_chess("move"),
        /**
         * Exit the programme
         */
        Quit("quit"),
        /**
         * Start a new Jungle game
         */
        Start("new_game"),
        /**
         * Ask the programme to show the rules of Jungle Game
         */
        RULE("rule"),
        /**
         *  Load the save file from the beginning of the game before any initalisation
         */
        Load("load_game"),
        /**
         * Start the game with the GUI interface
         */
        GUI("new_game_gui"),
        /**
         * Load game file for the GUI interface as the loading algorithm is different
         */
        Open_GUI("open_gui"),
        /**
         * Checking end condition for the GUI game mode.
         */
        Check("check_end"),
        /**
         * surrender the game.
         */
        Surrender("surrender"),
        /**
         * Renew the game
         */
        renew_game("renew_game");




        private String command_key;

        Command_key(String commandStr) {
            this.command_key = commandStr;
        }

        /** Public Method used to get the command from the user
         * @return  The String command
         */
        String getCommand_key() {
            return command_key;
        }

        /** Publci Static Method used to retrun which command is inputed by user
         * @param command   The String command
         * @return  Command_key's type
         */
        static Command_key get_command(String command) {
            for (Command_key cmd : Command_key.values()) {
                if (cmd.getCommand_key().equals(command))
                    return cmd;
            }
            return null;
        }
    }

    /** Public Method used to check if the Jungle Game is start playing
     * @return  True if the game is playing, otherwise False
     */
    private boolean getplaying(){
        return this.playing;
    }

    private void change_playing(){
        this.playing = !this.playing;
    }

    private void change_first_command(){
        this.first_command = !this.first_command;
    }

    private void swap_player(){
        if (getPlayer_on_turn().equals(game.getplayerA())) this.player_on_turn = game.getplayerB();
        else this.player_on_turn = game.getplayerA();
    }

    /**
     * Public Method used for the starting of the Jungle Game
     */
    public void start_of_game(){
        String command ;
        String[] command_array;
        Printing.printwelcome();
        while (first_command) {
            Printing.print_starting_option();
            command = INPUT_CLASS.GET_COMMAND();
            if (command.isEmpty()) continue;
            command_array = command.split(" ");
            fetch_command(command_array);
            if ( !playing) return;
        }
    }

    /**
     *  Public Method used to get the next commnad from the user in the command line mode
     */
    private void geting_command(){
        String command ;
        String[] command_array;
        Printing.printboard(getgame());
        while (getplaying()) {
            Printing.printnext(getPlayer_on_turn());
            command = INPUT_CLASS.GET_COMMAND();
            if (command.isEmpty()) continue;
            command_array = command.split(" ");
            fetch_command(command_array);
            if (game.check_board(getPlayer_on_turn())){
                swap_player();
                Printing.win_game(getPlayer_on_turn());
                change_playing();
            }
        }
    }


    /**
     * Public Method used to decide what command to do according to the user's input
     * @param command command list for fetching the command
     * @return return is the move command run successfully mainly used for noticing the GUI moving command
     */
    public boolean fetch_command(String[] command) {
        Command_key temp;
        if ((temp = Command_key.get_command(command[0])) != null)
            switch (temp) {
                case Saving:
                    if (command.length > 2) {
                        Printing.print(1);
                    } else {
                        try{
                            if (command.length == 1 ) game.saving("auto.txt",game.getplayerA(),game.getplayerB(),getPlayer_on_turn());
                            else game.saving(command[1], game.getplayerA(),game.getplayerB(),getPlayer_on_turn());
                            Printing.print(4);
                        }
                        catch ( IOException e ){
                            Printing.print(1);
                        }
                        change_saved_game();
                    }
                    break;
                case Open_saving:
                    if ( command.length > 2) {
                        Printing.print(1);
                        break;
                    }
                    try{
                        if (check_saved()){
                            ask_for_save();
                        }
                        if (command.length == 1 ) change_player(game.open_file("auto.txt",getgame()));
                        else change_player(game.open_file(command[1],getgame()));
                        if ( !getGui_indicator()) Printing.printboard(getgame());
                        Printing.print(5);
                    }
                    catch ( IOException e ) {
                        Printing.print(1);
                    }
                    break;
                case Get_help:
                    Printing.printing(Printing.printhelp());
                    break;
                case Move_chess:
                    if (command.length != 3) Printing.print(3);
                    else if (game.move_chess(command[1], command[2], getPlayer_on_turn())) {
                        swap_player();
                        if ( !getGui_indicator()) Printing.printboard(getgame());
                        return true;
                    }
                    else {
                        if ( !getGui_indicator()) Printing.printboard(getgame());
                        Printing.print(3);
                    }
                    return false;
                case Quit:
                    if (check_saved()){
                        ask_for_save();
                    }
                    change_first_command();
                    Printing.printing(Printing.game_end());
                    change_playing();
                    break;
                case Start:
                    if (command.length != 1){
                        Printing.error(10);
                    }
                    else {
                        String nameA = INPUT_CLASS.getname("",1);
                        String nameB = INPUT_CLASS.getname(nameA,2);
                        change_first_command();
                        actual_start_game(nameA, nameB);
                        Printing.printing(Printing.printhelp());
                        Printing.printing(Printing.game_rule());
                        geting_command();
                    }
                    break;
                case RULE :
                    if (command.length != 1){
                        Printing.error(10);
                    }
                    else {
                       Printing.printing(Printing.game_rule());
                    }
                    break;
                case Load :
                    temp_start();
                    try{
                        if (command.length == 1 ) change_player(game.open_file("auto.txt",getgame()));
                        else change_player(game.open_file(command[1],getgame()));
                        Printing.print(5);
                    }
                    catch ( IOException e ) {
                        Printing.print(1);
                    }
                    change_first_command();
                    Printing.printing(Printing.printhelp());
                    Printing.printing(Printing.game_rule());
                    geting_command();
                    break;
                case GUI:
                    Application.launch(JungleGameGraphic.class);
                    change_first_command();
                    break;
                case Open_GUI:
                    try{
                        if (check_saved()){
                            ask_for_save();
                        }
                        if (command.length == 1 ) change_player(this.temp.open_file_gui("auto.txt",getgame(),this));
                        else change_player(this.temp.open_file_gui(command[1],getgame(),this));
                        Printing.print(5);
                    }
                    catch ( IOException e ) {
                        Printing.print(1);
                    }
                    break;
                case Check:
                    if (game.check_board(getPlayer_on_turn())){
                      swap_player();
                      Printing.win_game(getPlayer_on_turn());
                      change_playing();
                      return true;
                    }
                    return false;
                case Surrender:
                    swap_player();
                    Printing.win_game(getPlayer_on_turn());
                    change_playing();

                    break;
                case renew_game:
                    this.game = new JungleGame(getgame().getplayerA().getPlayer_name(),getgame().getplayerB().getPlayer_name());
                    if ( getPlayer_on_turn() != getgame().getplayerA()){
                        swap_player();
                    }
                    if ( !getGui_indicator()) Printing.printboard(getgame());
                    break;
                default:
                    Printing.error(10);
                    break;
            }
            return false;
    }




    private void actual_start_game(String A, String B){
        this.game = new JungleGame(A,B);
        this.player_on_turn = this.game.getplayerA();
    }


    private void temp_start(){
        this.game = new JungleGame(" ");
        this.player_on_turn = this.game.getplayerA();
    }

    /**
     *  Get the player who is playing at that turn
     * @return Player who are making move in that turn.
     */
    public  Player getPlayer_on_turn(){

        return this.player_on_turn;
    }

    /**
     * get the game of the JungleGame that used for loading and saving
     * @return The Jungle game object of the game
     */
    public  JungleGame getgame(){
        return this.game;
    }

    private void change_player(Player a ){
        this.player_on_turn = a;
    }

    private void change_saved_game(){
        this.saved = !this.saved;
    }

    private boolean check_saved(){
        return !get_saved();
    }

    private boolean get_saved(){
        return this.saved;
    }

    /**
     *  Get the graphic of the game for the model to do the loading
     * @return the currently using graphic
     */
    public JungleGameGraphic getGraph(){
        return this.graph;
    }

    /**
     * Set the graphic in the console to let the model loading and opening file
     * @param X the graphic used to display the gmae
     */
    public void setGraph(JungleGameGraphic X ){
        this.graph = X;
    }

    private void ask_for_save(){
        Printing.printing(Printing.ask_save());
        String command = INPUT_CLASS.GET_COMMAND();
        String[] command1 = command.split(" ");
        if ( command1[0].equals("save")) {
            fetch_command(command1);
        }
    }

}
