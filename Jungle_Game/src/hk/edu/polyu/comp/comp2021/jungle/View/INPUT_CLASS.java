package hk.edu.polyu.comp.comp2021.jungle.View;

import java.util.Scanner;

/**
 *  public class for inputting command in the command line interface for this specific project.
 */
public class INPUT_CLASS {

    /**public static method to get the input form users and return to the console to decide what to do.
     *
     * @return The command inputed form the users
     */
    public static String GET_COMMAND(){
        String command ;
        Scanner input = new Scanner(System.in);
        command = input.nextLine().trim();
        return command;
    }

    /**
     * Function to get the player's name and normal checking
     * @param namea the exsisting player 1's name
     * @param i the player index
     * @return reutnrn the new inputted player name
     */
    public static String getname(String namea , int i){
        String name = " ";
        Scanner input = new Scanner(System.in);
        while ( name.equals(" ") ) {
            System.out.println("Please input the "+i+" player name : ");
            name = input.nextLine().trim();
            if (name.isEmpty()) System.out.println("Name can not be empty!");
            else if (name.equals(namea) ) {
                System.out.println("The name has already existed! ");
                name = " ";
            }
        }
        return name ;
    }


}
