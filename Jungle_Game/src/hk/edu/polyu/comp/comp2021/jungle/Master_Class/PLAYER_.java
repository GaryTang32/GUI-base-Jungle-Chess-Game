package hk.edu.polyu.comp.comp2021.jungle.Master_Class;

/** Public PLAYER_ class holds to two private field name and player_num
 *  PLAYER_ used to save the infromation of the two players and provide some basic function about the player
 *  such as the setting name and get the name of the player
 */
public class PLAYER_ {
    private String name ;
    private int player_num;

    /**
     * Public Constructor to initialize a PLAYER_ object
     * @param name player's name
     * @param num Player's unique number
     */
    protected PLAYER_(String name, int num){
        setname(name);
        setPlayer_num(num);
    }

    /**
     * Function used to set the player's name
     * @param name playger's name
     */
    private void setname (String name){
        this.name = name;
    }

    /**
     * function used to get the player's name
     * @return Player's name
     */
    public String getPlayer_name(){
        return this.name ;
    }

    /**
     * Function used to set up the number of the player
     * @param i number for setting up the player's number
     */
    private void setPlayer_num(int i){
        this.player_num = i ;
    }

    /**
     * function used to get the player's number.
     * @return the player's number
     */
    public int getPlayer_num(){
        return this.player_num;
    }

}
