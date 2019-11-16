package hk.edu.polyu.comp.comp2021.jungle.View;

import hk.edu.polyu.comp.comp2021.jungle.Controller.JungleConsole;
import hk.edu.polyu.comp.comp2021.jungle.model.JungleGame;

/**
 * The application for the game to stat. The starting point of the game.
 */
public class Application {

    /**
     * Function to starting the gmae
     * @param args basic argument
     */
    public static void main(String[] args){


        JungleGame game = new JungleGame();
        JungleConsole console = new JungleConsole(game);
        console.start_of_game();


        // start playing the game
    }
}
