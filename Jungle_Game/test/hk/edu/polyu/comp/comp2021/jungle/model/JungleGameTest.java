package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.View.JungleGameGraphic;
import hk.edu.polyu.comp.comp2021.jungle.View.Printing;
import hk.edu.polyu.comp.comp2021.jungle.model.*;
import hk.edu.polyu.comp.comp2021.jungle.Controller.*;
import javafx.application.Application;
import org.junit.Test;

import java.io.IOException;

/**
 * Testing for the whoel game
 */
public class JungleGameTest {

    /**
     * Test the jungle game constructor
     */
    @Test
    public void testJungleGameConstructor () {
        JungleGame game = new JungleGame("");
        JungleGame game1 = new JungleGame("C", "D");
        JungleGame game2 = new JungleGame();
        JungleGame game3 = new JungleGame("P","R");
        JungleConsole cons = new JungleConsole(game);
        JungleConsole cons2 = new JungleConsole(game1);
        JungleConsole cons3 = new JungleConsole(game3);
        assert game.getplayerA().getPlayer_name() == "A";
        assert game.getplayerA().getPlayer_num() == 1;
        assert game.getplayerB().getPlayer_name() == "B";
        assert game.getplayerB().getPlayer_num() == 2;
        assert game.check_input_valid(2, 2);
        assert !game.check_input_valid(0, 0);
        assert !game.check_board(cons.getPlayer_on_turn());
        assert !game1.check_board(cons2.getPlayer_on_turn());
        assert !game1.move_chess("A12", "A1", game.getplayerA());
        assert !game1.move_chess("A0", "A1", game.getplayerA());
        assert !game1.move_chess("A1", "A0", game.getplayerA());
        assert !game1.move_chess("A1", "A2", game.getplayerA());
        assert !game1.move_chess("A9", "C9", game.getplayerA());
        assert !game1.move_chess("C7", "C6", game.getplayerA());
        game1.getBoard().set_chess_on_bloard(1, 3, game.getBoard().getchess_onBoard(2, 2));
        assert !game1.move_chess("C9", "D9", game.getplayerA());
        assert game.move_chess("A9", "A8", game.getplayerA());
        assert game.getBoard().getchess_onBoard(2, 1).getrank() == 7;
        game1.getBoard().set_chess_on_bloard(3, 2, game.getBoard().getchess_onBoard(7, 3));
        assert !game1.move_chess("B7", "C7", game.getplayerB());
        assert !game1.move_chess("C7", "B7", game.getplayerA());
        assert !game1.move_chess("A1", "B1", game.getplayerA());
        cons3.getgame().getBoard().set_chess_on_bloard(6,1,cons3.getgame().getplayerA().getchess(1));
        assert game3.move_chess("A4", "A3", game3.getplayerA());
        assert !game3.move_chess("A5", "A4", game3.getplayerA());
        game1.getBoard().set_chess_on_bloard(7, 7, game.getBoard().getchess_onBoard(4, 3));
        assert !game1.move_chess("C7", "C6", game.getplayerA());
        assert game.getBoard().getchess_onBoard(1, 1).tostirng(7) != "Cat";
        assert game.getBoard().getchess_onBoard(3, 1).tostirng(1) != "Cat";
        Pieces lion1 = new Pieces(7, game.getplayerA());
        assert lion1.toString() != "Dog";
        //   assert game.getBoard().getchess_onBoard(1,1).getchess() != lion1.getchess();
        game.getBoard().set_chess_on_bloard(3, 6, game.getBoard().getchess_onBoard(1, 7));
        game.getBoard().set_chess_on_bloard(7, 6, game.getBoard().getchess_onBoard(7, 5));
        assert game.getBoard().getchess_onBoard(3, 6).getrank() == 6;
        assert game.getBoard().getchess_onBoard(7, 6).getrank() == 5;
        assert game.getBoard().check_move_possible(6, 3, 6, 7);
        game.getBoard().set_chess_on_bloard(5, 7, game.getBoard().getchess_onBoard(3, 6));
        game.getBoard().set_chess_on_bloard(5, 4, game.getBoard().getchess_onBoard(7, 6));
        assert game.getBoard().check_move_possible(7, 5, 4, 5);
        game.getBoard().set_chess_on_bloard(4, 4, game.getBoard().getchess_onBoard(5, 7));
        game.getBoard().set_chess_on_bloard(4, 7, game.getBoard().getchess_onBoard(5, 4));
        assert game.getBoard().check_move_possible(4, 4, 7, 4);
        assert !game.getBoard().check_move_possible(4, 4, 7, 3);
        game.getBoard().set_chess_on_bloard(3, 3, game.getBoard().getchess_onBoard(4, 3));
        assert !game.getBoard().check_land_possible(3, 3, 3, 4);
        assert game.getBoard().getchess_onBoard(7, 1).getrank() == 8;
        assert game.getBoard().getland_onBoard(1, 1).getland() == game.getBoard().getland_onBoard(2, 1).getland();
        assert game.getBoard().check_from_isempty(1, 1);
        assert game.getBoard().check_to_isempty(2, 1);
        assert game.getBoard().check_move_possible(1, 1, 2, 1);
        assert game.getBoard().check_Pieces_rank(7, 1, 6, 1, game.getplayerA());
        assert !game.getBoard().check_Pieces_rank(7, 1, 6, 1, game.getplayerB());
        assert game.getBoard().check_land_possible(1, 1, 2, 1);
        assert game.getBoard().check_land_possible(4, 2, 4, 3);
        assert !game.getBoard().check_land_possible(2, 4, 3, 1);
        assert game.getBoard().check_land_possible(2, 1, 3, 1);
        assert game.getBoard().getland_onBoard(1, 1).get_land_name() == "Land";
        assert game.getBoard().getland_onBoard(1, 1).move_possible(game.getBoard().getland_onBoard(2, 1)) == 1;
        assert game.getBoard().getchess_onBoard(7, 1).compare_Pieces_player(game.getBoard().getchess_onBoard(9, 1));
        JungleGame game4 = new JungleGame("");
        try {
            game4.saving("auto.txt", game3.getplayerA(), game3.getplayerB(), game3.getplayerA());
        } catch (IOException e ) {
            return;
        }

        try {
            game4.open_file("auto.txt", game3);
        } catch (IOException e) {
            return;
        }




        /*
        Check mouse go in river cant eat land piece and out river and mouse can eat elephant
         */
        assert game.getBoard().getchess_onBoard(3, 1).getrank() == 1;
        game.getBoard().move(1, 3, 1, 4);
        assert game.getBoard().check_land_possible(1, 4, 2, 4);
        game.getBoard().move(1, 4, 2, 4);
        assert game.getBoard().getchess_onBoard(4, 2).getrank() == 1;
        game.getBoard().move(2, 4, 2, 5);
        game.getBoard().move(2, 5, 2, 6);
        assert game.getBoard().getchess_onBoard(7, 1).getrank() == 8;
        game.getBoard().move(1, 7, 1, 6);
        assert !game.getBoard().check_land_possible(2, 6, 1, 6);
        assert game.getBoard().check_move_possible(2, 6, 1, 6);
        game.getBoard().move(1, 6, 1, 7);
        assert game.getBoard().check_land_possible(2, 6, 1, 6);
        game.getBoard().move(2, 6, 1, 6);
        assert game.getBoard().getchess_onBoard(6, 1).getrank() == 1;
        assert game.getBoard().getchess_onBoard(7, 1).getrank() == 8;
        assert game.getBoard().check_move_possible(1, 6, 1, 7);
        game.getBoard().move(1, 6, 1, 7);
        assert game.getplayerB().is_chess_dead(8);

        /*
        Check lion cant jump river when mouse blocked in river and lion can junp river eat pieces when no mouse in river block the road
         */
        assert game.getBoard().getchess_onBoard(7, 7).getrank() == 1;
        game.getBoard().move(7, 7, 7, 6);
        game.getBoard().move(7, 6, 6, 6);
        assert game.getBoard().getchess_onBoard(3, 5).getrank() == 4;
        game.getBoard().move(5, 3, 6, 3);
        assert game.getBoard().getchess_onBoard(9, 7).getrank() == 7;
        assert game.getBoard().check_move_possible(7, 9, 7, 8);
        game.getBoard().move(7, 9, 7, 8);
        assert game.getBoard().check_move_possible(7, 8, 6, 8);
        game.getBoard().move(7, 8, 7, 7);
        game.getBoard().move(7, 7, 6, 7);
        assert game.getBoard().getchess_onBoard(6, 6).getrank() == 1;
        assert game.getBoard().getchess_onBoard(3, 6).getrank() == 4;
        assert game.getBoard().getchess_onBoard(7, 6).getrank() == 7;
        assert !game.getBoard().check_move_possible(6, 7, 6, 3);
        game.getBoard().move(6, 6, 5, 6);
        assert game.getBoard().check_move_possible(6, 7, 6, 3);
        game.getBoard().move(6, 7, 6, 3);
        assert game.getplayerA().is_chess_dead(4);

        /*
        Check mouse can eat opponent's mouse in river
         */
        assert game.getBoard().getchess_onBoard(6, 5).getrank() == 1;
        game.getBoard().move(5, 6, 4, 6);
        game.getBoard().move(4, 6, 3, 6);
        assert game.getBoard().getchess_onBoard(7, 1).getrank() == 1;
        game.getBoard().move(1, 7, 1, 6);
        game.getBoard().move(1, 6, 2, 6);
        assert game.getBoard().check_move_possible(2, 6, 3, 6);
        game.getBoard().move(2, 6, 3, 6);
        assert game.getplayerB().is_chess_dead(1);

        /*
        Check lion in trap cant eat other piece and can be ate by cat
         */
        assert game.getBoard().getchess_onBoard(3, 6).getrank() == 7;
        game.getBoard().move(6, 3, 5, 3);
        game.getBoard().move(5, 3, 4, 3);
        game.getBoard().move(4, 3, 4, 2);
        assert game.getBoard().getchess_onBoard(2, 6).getrank() == 2;
        game.getBoard().move(6, 2, 5, 2);
        assert game.getBoard().getchess_onBoard(2, 4).getrank() == 7;
        assert game.getBoard().getchess_onBoard(2, 5).getrank() == 2;
        assert !game.getBoard().check_Pieces_rank(4, 2, 5, 2, game.getplayerA());
        assert !game.getBoard().check_Pieces_rank(5, 2, 4, 2, game.getplayerB());
        assert game.getBoard().check_move_possible(4, 2, 5, 2);
        assert game.getBoard().check_move_possible(5, 2, 4, 2);
        game.getBoard().move(5, 2, 4, 2);
        assert game.getplayerB().is_chess_dead(7);

        /*
        Check if mouse in opponet's den then win the game
         */
        assert game.getBoard().getchess_onBoard(6, 3).getrank() == 1;
        game.getBoard().move(3, 6, 4, 6);
        game.getBoard().move(4, 6, 4, 7);
        game.getBoard().move(4, 7, 4, 8);
        assert game.getBoard().check_land_possible(4, 8, 4, 9);
        game.getBoard().move(4, 8, 4, 9);
        assert game.getBoard().getchess_onBoard(9, 4).getrank() == 1;


        game.getBoard().set_chess_on_bloard(1, 3, game.getBoard().getchess_onBoard(2, 2));
        assert !game.getBoard().check_land_possible(3, 1, 4, 1);
        assert !game.getBoard().check_land_possible(1, 1, 0, 1);
        game.getBoard().WipeBoard();
        assert !game.getplayerA().check_is_empty();

    }

}