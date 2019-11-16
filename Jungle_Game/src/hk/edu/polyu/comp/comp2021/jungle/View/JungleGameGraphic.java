package hk.edu.polyu.comp.comp2021.jungle.View;

import hk.edu.polyu.comp.comp2021.jungle.Controller.JungleConsole;
import hk.edu.polyu.comp.comp2021.jungle.model.JungleGame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.*;

/** The JungleGameGraphic is the class for the whole Gui mode but the caculation are still doing the the model package
 * The graphic is connected with the controler
 *
 */
public class JungleGameGraphic extends Application {


    private double from_X;
    private double from_Y;
    private double to_X;
    private double to_Y;
    private final double horizontal_offset = 66;
    private final double vertical_offset = 65.5;
    private final double base_position = 38;
    private final int INT_to_CHAR = 65;
    private final int NUMBER_OFFSET = 47;
    private final JungleConsole cons ;
    private final int forn_size1 = 30;
    private final int fron_size2 = 20;
    private final int window_width =870;
    private final int window_hength = 900;
    private final int number_of_chess = 17;
    private static final int diameter = 30;
    private static final String[] checking = new String[1];
    static  {checking[0] = "check_end";}

    private static final Circle red1 = new Circle(0,0,diameter);
    private static final Circle red2 = new Circle(0,0,diameter);
    private static final Circle red3 = new Circle(0,0,diameter);
    private static final Circle red4 = new Circle(0,0,diameter);
    private static final Circle red5 = new Circle(0,0,diameter);
    private static final Circle red6 = new Circle(0,0,diameter);
    private static final Circle red7 = new Circle(0,0,diameter);
    private static final Circle red8 = new Circle(0,0,diameter);

    private static final Circle blue1 = new Circle(0,0,diameter);
    private static final Circle blue2 = new Circle(0,0,diameter);
    private static final Circle blue3 = new Circle(0,0,diameter);
    private static final Circle blue4 = new Circle(0,0,diameter);
    private static final Circle blue5 = new Circle(0,0,diameter);
    private static final Circle blue6 = new Circle(0,0,diameter);
    private static final Circle blue7 = new Circle(0,0,diameter);
    private static final Circle blue8 = new Circle(0,0,diameter);
    private final Circle tempCircle = new Circle();
    private Pane board ;
    private BorderPane b;
    private Text textbox ;
    private Text textbox1;
    private JungleGame game;
    private Circle moving_chess = tempCircle;
    private Text player1_name;
    private Text player2_name;

    private enum display_Piece  {
        Rat1(1,red1), Cat1(2, red2), Dog1(3,red3), Wolf1(4,red4), Leopard1(5,red5),Tiger1(6,red6),Lion1(7,red7), Elephant1(8,red8),
        Rat2(9,blue1), Cat2(10,blue2), Dog2(11,blue3), Wolf2(12,blue4), Leopard2(13,blue5),Tiger2(14,blue6),Lion2(15,blue7), Elephant2(16,blue8);

        display_Piece(int rank, Circle C) {
            setCircle(C);
            setrank(rank);
        }

        private static display_Piece getpiece(int rank) {
            if (PieceMap == null) {
                PieceMap = new HashMap();
                for (display_Piece p : display_Piece.values()) {
                    PieceMap.put(p.getrank(), p);
                }
            }
            return PieceMap.get(rank);
        }

        int getrank(){
            return this.rank;
        }

        Circle getCircle(){
            return this.circle;
        }


        private void setrank(int rank){
            this.rank = rank;
        }
        private void setCircle(Circle C){
            this.circle = C;
        }

        private Circle circle;
        private int rank;
        private static Map<Integer, display_Piece> PieceMap;
    }

    /**
     * Construtor that used to make the basic element of the GUI mode
     *
     */
    public JungleGameGraphic(){
        String nameA = INPUT_CLASS.getname("",1);
        String nameB = INPUT_CLASS.getname(nameA,2);
        this.game = new JungleGame(nameA, nameB);
        this.cons = new JungleConsole(game);
        JungleConsole.changeGUI_indicator();
        cons.setGraph(this);
    }

    /**
     *
     * Function used to start the GUI interface
     * @param primary the stage for the GUI
     */
    @Override
    public void start(Stage primary) {
        b = new BorderPane();
        Scene scene = new Scene( b, window_width, window_hength );
        b.setCenter(addmiddle());
        b.setTop(addHBox());
        b.setBottom(addbottomHBox());
        b.setLeft(addleft(game.getplayerA().getPlayer_name(),game.getplayerB().getPlayer_name()));
        b.setRight(addright());
        primary.setTitle("The Jungle Game");
        primary.setScene(scene);
        primary.show();
    }

    /**
     * Function used to add the header box in the GUI interface
     * @return The Hbox for the top of game
     */
    private HBox addHBox() {
        final int default_padding  =10;
        final int SIZE_X = 800;
        final int SIZE_Y = 100;
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(default_padding, default_padding, default_padding, default_padding));
        hbox.setPrefSize(SIZE_X,SIZE_Y);
        hbox.setSpacing(10);
        Text text = new Text();
        String text1 = "~!!~ THE JUNGLE GAME ~!!~";
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(Font.font("Arial", FontWeight.BOLD, forn_size1));
        text.setText(text1);
        hbox.getChildren().add(text);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    }

    /**
     * Fucntion used to add the bottom pane box of the game
     * @return The flowpane which include the bottom box element
     */
    private FlowPane addbottomHBox() {
        final int default_padding = 10;
        final int SIZE_X = 900;
        final int SIZE_Y = 180;
        final double scale = 2.8;
        final double size = 5.0;
        FlowPane hbox = new FlowPane();
        hbox.setPadding(new Insets(default_padding, default_padding, default_padding, default_padding));
        hbox.setPrefSize(SIZE_X,SIZE_Y);
        textbox = new Text();
        textbox.setText(cons.getPlayer_on_turn().getPlayer_name()+"'s turn please drag the piece.");
        textbox1 = new Text();
        textbox1.setText(Printing.printhelp_GUI());
        Pane display = new Pane();
        Pane display1 = new Pane();
        display1.setPrefSize(SIZE_X,(SIZE_Y/size)*scale);
        display.setPrefSize(SIZE_X,(SIZE_Y/size));
        display.getChildren().add(textbox);
        display.getChildren().add(textbox1);
        hbox.getChildren().add(display);
        hbox.getChildren().add(display1);
        return hbox;
    }


    /**
     *  Function used to construct the left hand size user information
     * @param nameA The first player's name
     * @param nameB The second player's name
     * @return The VBox of the left hand size of the jungle game
     */
    private VBox addleft(String nameA, String nameB) {
        final int default_padding  =10;
        final int SIZE_X = 200;
        final int SIZE_Y = 600;
        VBox vertical = new VBox();
        vertical.setPadding(new Insets(default_padding, default_padding, default_padding, default_padding));
        vertical.setPrefSize(SIZE_X,SIZE_Y);
        vertical.setPadding(new Insets(10));
        vertical.setSpacing(8);
        player1_name = new Text("(TOP)Player 1 :\n "+nameA+"\n\n\n\n\n\n\n\n\n\n\n\n\n");
        player1_name.setFont(Font.font("Arial", FontWeight.BOLD, fron_size2));
        vertical.getChildren().add(player1_name);
        player2_name = new Text("(BOTTOM)Player 2 :\n "+nameB);
        player2_name.setFont(Font.font("Arial", FontWeight.BOLD, fron_size2));
        vertical.getChildren().add(player2_name);
        return vertical;
    }


    /**
     * Function used to construct the right hand side button list.
     * @return Vbox of the right hand size for the jungle game
     */
    private VBox addright() {
        final int default_padding  =10;
        final int SIZE_X = 200;
        final int SIZE_Y = 600;
        VBox vertical = new VBox();
        vertical.setPadding(new Insets(default_padding, default_padding, default_padding, default_padding));
        vertical.setPrefSize(SIZE_X,SIZE_Y);
        vertical.setPadding(new Insets(10));
        vertical.setSpacing(8);
        Button save = new Button("Save default Game");
        Button load = new Button("Load default Game");
        Button saveP = new Button("Save Game ");
        Button loadP = new Button("Load Game");
        Button rule = new Button("Rule");
        Button help = new Button("Help");
        Button surrentder = new Button("Surrender");
        Button new_game = new Button ( "new_game");
        vertical.getChildren().add(save);
        vertical.getChildren().add(saveP);
        vertical.getChildren().add(load);
        vertical.getChildren().add(loadP);
        vertical.getChildren().add(rule);
        vertical.getChildren().add(help);
        vertical.getChildren().add(surrentder);
        vertical.getChildren().add(new_game);
        save.setOnAction(new SaveHandler());
        saveP.setOnAction(new SaveHandlerP());
        load.setOnAction(new loadHandler());
        loadP.setOnAction(new loadHandlerP());
        rule.setOnAction(new ruleHandler());
        help.setOnAction(new helpHandler());
        surrentder.setOnAction(new surrenderhandler());
        new_game.setOnAction(new new_gamehandler());
        return vertical;
    }


    /**
     * Fucntion used to construct the board and the chess and the default location of the pieces
     * @return The Main board of the Jungle GUI
     */
    private Pane addmiddle() {

        board = new Pane();
        ImageView view = new ImageView();
        Image image = new Image("board.PNG");
        view.setImage(image);
        board.getChildren().add(view);

        Image Red1 = new Image("red1.PNG");
        red1.setCenterX(6*horizontal_offset+base_position);
        red1.setCenterY(6*vertical_offset+base_position);
        red1.setFill(new ImagePattern(Red1));
        red1.setStroke(Color.BLACK);
        board.getChildren().add(red1);
        red1.setOnMouseDragged(new MouseHandler(red1));
        red1.setOnMousePressed( new Mouseclick(red1));
        red1.setOnMouseReleased(new Mouserelease(red1));

        Image Red2 = new Image("red2.PNG");
        red2.setCenterX(1*horizontal_offset+base_position);
        red2.setCenterY(7*vertical_offset+base_position);
        red2.setFill(new ImagePattern(Red2));
        red2.setStroke(Color.BLACK);
        board.getChildren().add(red2);
        red2.setOnMouseDragged(new MouseHandler(red2));
        red2.setOnMousePressed( new Mouseclick(red2));
        red2.setOnMouseReleased(new Mouserelease(red2));


        Image Red3 = new Image("red3.PNG");
        red3.setCenterX(5*horizontal_offset+base_position);
        red3.setCenterY(7*vertical_offset+base_position);
        red3.setFill(new ImagePattern(Red3));
        red3.setStroke(Color.BLACK);
        board.getChildren().add(red3);
        red3.setOnMouseDragged(new MouseHandler(red3));
        red3.setOnMousePressed( new Mouseclick(red3));
        red3.setOnMouseReleased(new Mouserelease(red3));


        Image Red4 = new Image("red4.PNG");
        red4.setCenterX(2*horizontal_offset+base_position);
        red4.setCenterY(6*vertical_offset+base_position);
        red4.setFill(new ImagePattern(Red4));
        red4.setStroke(Color.BLACK);
        board.getChildren().add(red4);
        red4.setOnMouseDragged(new MouseHandler(red4));
        red4.setOnMousePressed( new Mouseclick(red4));
        red4.setOnMouseReleased(new Mouserelease(red4));

        Image Red5 = new Image("red5.PNG");
        red5.setCenterX(4*horizontal_offset+base_position);
        red5.setCenterY(6*vertical_offset+base_position);
        red5.setFill(new ImagePattern(Red5));
        red5.setStroke(Color.BLACK);
        board.getChildren().add(red5);
        red5.setOnMouseDragged(new MouseHandler(red5));
        red5.setOnMousePressed( new Mouseclick(red5));
        red5.setOnMouseReleased(new Mouserelease(red5));

        Image Red6 = new Image("red6.png");
        red6.setCenterX(0*horizontal_offset+base_position);
        red6.setCenterY(8*vertical_offset+base_position);
        red6.setFill(new ImagePattern(Red6));
        red6.setStroke(Color.BLACK);
        board.getChildren().add(red6);
        red6.setOnMouseDragged(new MouseHandler(red6));
        red6.setOnMousePressed( new Mouseclick(red6));
        red6.setOnMouseReleased(new Mouserelease(red6));

        Image Red7 = new Image("red7.png");
        red7.setCenterX(6*horizontal_offset+base_position);
        red7.setCenterY(8*vertical_offset+base_position);
        red7.setFill(new ImagePattern(Red7));
        red7.setStroke(Color.BLACK);
        board.getChildren().add(red7);
        red7.setOnMouseDragged(new MouseHandler(red7));
        red7.setOnMousePressed( new Mouseclick(red7));
        red7.setOnMouseReleased(new Mouserelease(red7));

        Image Red8 = new Image("red8.png");
        red8.setCenterX(0*horizontal_offset+base_position);
        red8.setCenterY(6*vertical_offset+base_position);
        red8.setFill(new ImagePattern(Red8));
        red8.setStroke(Color.BLACK);
        board.getChildren().add(red8);
        red8.setOnMouseDragged(new MouseHandler(red8));
        red8.setOnMousePressed( new Mouseclick(red8));
        red8.setOnMouseReleased(new Mouserelease(red8));


        Image Blue1 = new Image("blue1.png");
        blue1.setCenterX(0*horizontal_offset+base_position);
        blue1.setCenterY(2*vertical_offset+base_position);
        blue1.setFill(new ImagePattern(Blue1));
        blue1.setStroke(Color.WHITE);
        board.getChildren().add(blue1);
        blue1.setOnMouseDragged(new MouseHandler(blue1));
        blue1.setOnMousePressed( new Mouseclick(blue1));
        blue1.setOnMouseReleased(new Mouserelease(blue1));

        Image Blue2 = new Image("blue2.png");
        blue2.setCenterX(5*horizontal_offset+base_position);
        blue2.setCenterY(1*vertical_offset+base_position);
        blue2.setFill(new ImagePattern(Blue2));
        blue2.setStroke(Color.WHITE);
        board.getChildren().add(blue2);
        blue2.setOnMouseDragged(new MouseHandler(blue2));
        blue2.setOnMousePressed( new Mouseclick(blue2));
        blue2.setOnMouseReleased(new Mouserelease(blue2));

        Image Blue3 = new Image("blue3.png");
        blue3.setCenterX(1*horizontal_offset+base_position);
        blue3.setCenterY(1*vertical_offset+base_position);
        blue3.setFill(new ImagePattern(Blue3));
        blue3.setStroke(Color.WHITE);
        board.getChildren().add(blue3);
        blue3.setOnMouseDragged(new MouseHandler(blue3));
        blue3.setOnMousePressed( new Mouseclick(blue3));
        blue3.setOnMouseReleased(new Mouserelease(blue3));

        Image Blue4 = new Image("blue4.png");
        blue4.setCenterX(4*horizontal_offset+base_position);
        blue4.setCenterY(2*vertical_offset+base_position);
        blue4.setFill(new ImagePattern(Blue4));
        blue4.setStroke(Color.WHITE);
        board.getChildren().add(blue4);
        blue4.setOnMouseDragged(new MouseHandler(blue4));
        blue4.setOnMousePressed( new Mouseclick(blue4));
        blue4.setOnMouseReleased(new Mouserelease(blue4));

        Image Blue5 = new Image("blue5.png");
        blue5.setCenterX(2*horizontal_offset+base_position);
        blue5.setCenterY(2*vertical_offset+base_position);
        blue5.setFill(new ImagePattern(Blue5));
        blue5.setStroke(Color.WHITE);
        board.getChildren().add(blue5);
        blue5.setOnMouseDragged(new MouseHandler(blue5));
        blue5.setOnMousePressed( new Mouseclick(blue5));
        blue5.setOnMouseReleased(new Mouserelease(blue5));

        Image Blue6 = new Image("blue6.png");
        blue6.setCenterX(6*horizontal_offset+base_position);
        blue6.setCenterY(0*vertical_offset+base_position);
        blue6.setFill(new ImagePattern(Blue6));
        blue6.setStroke(Color.WHITE);
        board.getChildren().add(blue6);
        blue6.setOnMouseDragged(new MouseHandler(blue6));
        blue6.setOnMousePressed( new Mouseclick(blue6));
        blue6.setOnMouseReleased(new Mouserelease(blue6));

        Image Blue7 = new Image("blue7.png");
        blue7.setCenterX(0*horizontal_offset+base_position);
        blue7.setCenterY(0*vertical_offset+base_position);
        blue7.setFill(new ImagePattern(Blue7));
        blue7.setStroke(Color.WHITE);
        board.getChildren().add(blue7);
        blue7.setOnMouseDragged(new MouseHandler(blue7));
        blue7.setOnMousePressed( new Mouseclick(blue7));
        blue7.setOnMouseReleased(new Mouserelease(blue7));

        Image Blue8 = new Image("blue8.png");
        blue8.setCenterX(6*horizontal_offset+base_position);
        blue8.setCenterY(2*vertical_offset+base_position);
        blue8.setFill(new ImagePattern(Blue8));
        blue8.setStroke(Color.WHITE);
        board.getChildren().add(blue8);
        blue8.setOnMouseDragged(new MouseHandler(blue8));
        blue8.setOnMousePressed( new Mouseclick(blue8));
        blue8.setOnMouseReleased(new Mouserelease(blue8));
        return board;

    }

    /**
     * Save handler for the sace buttom it will save the current game with default file
     */
    class SaveHandler implements EventHandler<ActionEvent>{

        /**
         * default constructor of the handler
         */
        SaveHandler(){}
        @Override
        public void handle(ActionEvent event) {
            String[] command = new String[1];
            command[0] = "save";
            cons.fetch_command(command);
        }

    }

    /**
     * Save handler for the sace buttom it will save the current game with the path
     */
    class SaveHandlerP implements EventHandler<ActionEvent>{

        /**
         * default constructor of the handler
         */
        SaveHandlerP(){}
        @Override
        public void handle(ActionEvent event) {
            Printing.printing("Plase input the path you wanted to save in the console : save [Path] ('close' for exit save)");
            String command = INPUT_CLASS.GET_COMMAND();
            String[] command1 = command.split(" ");
            while ( !command1[0].equals("save") && !command1[0].equals("close") ){
                Printing.printing("invalid input. Plase input the path you wanted to save in the console : save [Path] ");
                command = INPUT_CLASS.GET_COMMAND();
                command1 = command.split(" ");
            }
            if ( command1[0].equals("close") ) return;
            cons.fetch_command(command1);
        }

    }

    /**
     *  Loading handler for the load button
     *  it will load the saved game with the given path
     */
    class loadHandlerP implements EventHandler<ActionEvent>{
        /**
         * default constructor of the load handdler.
         */
        loadHandlerP(){}
        @Override
        public void handle(ActionEvent event) {
            Printing.printing("Plase input the path you wanted to save in the console : open_gui [Path] ('close' for exit open) ");
            String command = INPUT_CLASS.GET_COMMAND();
            String[] command1 = command.split(" ");
            while ( !command1[0].equals("open_gui") && !command1[0].equals("close") ){
                Printing.printing("invalid input. Plase input the path you wanted to save in the console : open_gui [Path] ");
                command = INPUT_CLASS.GET_COMMAND();
                command1 = command.split(" ");
                System.out.println(command1[0]);
            }
            if ( command1[0].equals("close") ) return;
            wipe_board();
            cons.fetch_command(command1);
            update_player();
            update_turn();
        }

    }


    /**
     *  Loading handler for the load button
     *  it will load the saved game with the default location
     */
    class loadHandler implements EventHandler<ActionEvent>{
        /**
         * default constructor of the load handdler.
         */
        loadHandler(){}
        @Override
        public void handle(ActionEvent event) {

            String[] command = new String[1];
            command[0] = "open_gui";
            cons.fetch_command(command);
            update_player();
            update_turn();
        }

    }

    /**
     * The rule handdler for the rule button it will displayer the rule when clicked
     */
    class ruleHandler implements EventHandler<ActionEvent>{
        /**
         * default constructor of the rule handdler.
         */
        ruleHandler(){}

        @Override
        public void handle(ActionEvent event) {
          update_dispaly1(Printing.game_rule());
        }

    }

    /**
     * The help handler for the help button
     */
    class helpHandler implements EventHandler<ActionEvent>{
        /**
         *
         * default constructor of the help handdler.
         */
        helpHandler(){}

        @Override
        public void handle(ActionEvent event) {
          update_dispaly1(Printing.printhelp_GUI());
        }

    }

    /**
     * The surrender handler for the surrender button
     */
    class surrenderhandler implements EventHandler<ActionEvent>{
        /**
         *
         * default constructor of the surrender handdler.
         */
        surrenderhandler(){}

        @Override
        public void handle(ActionEvent event) {
            String[] command = new String[1];
            command[0] = "surrender";
            cons.fetch_command(command);
            game.getBoard().WipeBoard();
            Win();
        }

    }

    /**
     * The new game handler for the surrender button
     */
    class new_gamehandler implements EventHandler<ActionEvent>{
        /**
         *
         * default constructor of the surrender handdler.
         */
        new_gamehandler(){}

        @Override
        public void handle(ActionEvent event) {
            String[] command = new String[1];
            command[0] = "renew_game";
            cons.fetch_command(command);
            wipe_board();
            update_turn();
            update_player();
        }

    }



    /**
     * The drag handler for hte chess to move when the player drag the pieces
     */
    class MouseHandler implements EventHandler<MouseEvent>{
        private final Circle text;

        /**
         * set the handler
         * @param text The circle that being moved
         */
        MouseHandler(Circle text){this.text = text;}
        @Override
        public void handle(MouseEvent event) {
            text.toFront();
            double X = (event.getX()-5)/ horizontal_offset;
            double Y = (event.getY()-5)/ vertical_offset;
            if ( X >= 0  && X < 7 && Y >= 0 && Y < 9) {
                text.setCenterX(event.getX());
                text.setCenterY(event.getY());
            }

        }
    }

    /**
     * The click handler for the Pieces to get the inital location of the ches and select the moving chess
     */
    class Mouseclick implements EventHandler<MouseEvent>{
        private final Circle circle;

        /**
         *
         * @param circle that was clicked
         */
        Mouseclick(Circle circle){this.circle = circle;}
        @Override
        public void handle(MouseEvent event) {
            moving_chess = tempCircle;
            from_X = (event.getX()-5)/ horizontal_offset ;
            from_Y = (event.getY()-5)/ vertical_offset ;
            moving_chess = circle;
         //   moving_chess = check_destination(from_X,from_Y);
        }
    }

    /**
     * The release handler for the Pieces.
     * This function will record the final positon the piece move and pass the claise
     */
    class Mouserelease implements EventHandler<MouseEvent>{
        private final Circle circle;

        /**
         *
         * @param circle Pieces that relased with the
         */
        Mouserelease(Circle circle){this.circle = circle;}
        @Override
        public void handle(MouseEvent event) {
            Circle eaten;
            to_X = (event.getX()-5)/ horizontal_offset  ;
            to_Y = (event.getY()-5)/ vertical_offset ;
            if (testing_move((int)from_X, (int)from_Y, (int)to_X, (int)to_Y)) {
                eaten = check_destination(to_X, to_Y);
                move_chess_on_display((int)to_X,(int)to_Y, circle);
                if ( eaten != moving_chess ){
                    delete_node(eaten);
                }
                if (cons.fetch_command(checking)){
                    game.getBoard().WipeBoard();
                    Win();
                }
                else {
                    update_turn();
                }
            }
            else {
                move_chess_on_display((int)from_X, (int)from_Y, circle);
            }

        }
    }

    /**
     * Fucntion used to concvert the GUI move into the command line command so that can pass to the controller and decide the move
     * @param A_X x-coordinate of the chess of from location
     * @param A_Y y-coordinate of the chess of from location
     * @param B_X x-coordinate of the chess of to location
     * @param B_Y y-coordinate of the chess of to location
     * @return Boolean status of the movement if the move is true will update the GUI board
     */

    private boolean testing_move(int A_X, int A_Y, int B_X, int B_Y) {
        String[] command = new String[3];
        command[0] = "move";
        String temp = String.valueOf((char)(A_X+INT_to_CHAR))+String.valueOf((char)((10-A_Y)+NUMBER_OFFSET));
        String temp2 = String.valueOf((char)(B_X+INT_to_CHAR))+String.valueOf((char)((10-B_Y)+NUMBER_OFFSET));
        command[1] = temp;
        command[2] = temp2;
        return this.cons.fetch_command(command);
    }

    /**
     * Function used to actually move the chess in the GUI mode
     * @param X The X coordinate that being moved to
     * @param Y The Y coordinate that being moved to
     * @param circle Piece that being moved
     */
    private void move_chess_on_display(int X, int Y, Circle circle) {
        circle.setCenterX((X*horizontal_offset)+base_position);
        circle.setCenterY((Y*vertical_offset)+base_position);
    }

    /**
     * Functioni used to get the chess form the given coordiante in the GUI board
     * @param X The X coordinate that need to check
     * @param Y The Y coordinate that need to check
     * @return the circle in the destination if the board is empty then
     */
    private Circle check_destination(double X, double Y) {
       int A,B;
       int C,D;
       C = (int)X;
       D = (int)Y;
        Circle temp;
        for (int i = 1; i < number_of_chess; i++) {
            temp = display_Piece.getpiece(i).getCircle();
            A =(int) ((temp.getCenterX()-5 )/ horizontal_offset);
            B =(int) ((temp.getCenterY()-5 )/ vertical_offset);
            if (A == C && B == D && temp != moving_chess) {
                return temp;
            }

        }
        return tempCircle;
    }


    private void delete_node(Circle C ){
        C.setCenterX(-100);
        C.setCenterY(-100);
        board.getChildren().remove(C);
    }

    /**
     * Function used to deleted the dead pieces in the loading file
     * @param index the pieces that being deleted
     */
    public void delete(int index){
        Circle C = display_Piece.getpiece(index).getCircle();
        delete_node(C);
    }

    /**
     * Funcino used to move the chess from loading the file
     * @param index the Piece that being mvoed
     * @param X The X coordinate of the destination
     * @param Y The Y coordinate of the destination
     */
    public void move( int index, int X, int Y){
        Circle C = display_Piece.getpiece(index).getCircle();
        move_chess_on_display(X,Y,C);
    }

    private void update_turn(){
        textbox.setText(cons.getPlayer_on_turn().getPlayer_name()+"'s turn please drag the piece.");
    }

    private void update_dispaly1(String a){
        textbox1.setText(a);
    }

    private void Win(){
        textbox.setText(cons.getPlayer_on_turn().getPlayer_name()+" has win the jungle game! "+cons.getPlayer_on_turn().getPlayer_name()+" has win the jungle game! "+cons.getPlayer_on_turn().getPlayer_name()+" has win the jungle game! ");
        if ( cons.getPlayer_on_turn().getPlayer_name().equals(game.getplayerA().getPlayer_name())){
            player1_name.setText("(TOP)Player 1 :\n "+game.getplayerA().getPlayer_name()+"\n(WINNER)\n\n\n\n\n\n\n\n\n\n\n");
        }
        else{
            player2_name.setText("(BOTTOM)Player 2 :\n "+game.getplayerB().getPlayer_name()+"(WINNER)");
        }
    }




    private void update_player(){
        player1_name.setText("(TOP)Player 1 :\n "+game.getplayerA().getPlayer_name()+"\n\n\n\n\n\n\n\n\n\n\n\n\n");
        player2_name.setText("(BOTTOM)Player 2 :\n "+game.getplayerB().getPlayer_name());
    }

    private void wipe_board(){
        b.setCenter(null);
        b.setCenter(addmiddle());
    }

    /**
     * Main Function that used to start the GUI game
     * @param args argument
     */
    public static void main(String[] args) {
        Application.launch();
    }

}
