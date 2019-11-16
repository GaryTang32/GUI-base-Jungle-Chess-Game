package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.Master_Class.LAND_;

import java.util.HashMap;
import java.util.Map;

/** Public Land class extends from LAND_
 *  Land class used to store the type of different land
 *  And provide some basic function of the land
 */
public class Land extends LAND_ {

    private Landtype land ;

    private enum Landtype {

        Wall("Wall"), Water("Water"), Land("Land"), Trap("Trap"), Den("Den");

        Landtype(String name) {
            setname(name);
        }


        public static Landtype getland(String name) {
            if (LandMap == null) {
                LandMap = new HashMap();
                for (Landtype l : Landtype.values()) {
                    LandMap.put(l.getname(), l);
                }
            }
            return LandMap.get(name);
        }

        public String getname(){
            return this.name;
        }

        private void setname(String name){
            this.name = name;
        }

        private String name;
        private static Map<String, Landtype> LandMap;

    }

    /** Public Constructor to initialize the the landtype of the land
     * @param land The name of the land
     */
    public Land(String land){
        set_landtype(land);
    }

    private void set_landtype(String land){
        this.land = Landtype.valueOf(land);
    }

    /** Public Method used to return the landtype of the land
     * @return The landtype of the land
     */
    public Landtype getland(){
        return this.land;
    }

    /** Public Static Method used to return the type of land if it exists in the Landtype
     * @param land The name of the land
     * @return  The type of land exist in Landtype, otherwise null
     */
    public static Landtype getLandType(String land){
        for (Landtype i : Landtype.values()){
            if ( i == Landtype.getland(land)){
                return i;
            }
        }
        return null;
    }

    /** Public Method used to return the name of the land
     * @return  The name of the land
     */
    public String get_land_name(){
        return this.land.getname();
    }

    /** Public Method used to check if the location to land the player wants to move the chess to is possible
     * @param B The type of land
     * @return  An interger cases according to different types of land situatiosn
     *          all possible conbination of the movement
     */
    public int move_possible( Land B){
        if ( this.getland() == B.getland() ) return 1;
        else if ( this.getland() == getLandType("Land") && B.getland() == getLandType("Trap")) return 2;
        else if ( this.getland() == getLandType("Trap") && B.getland() == getLandType("Den")) return 3;
        else if ( this.getland() == getLandType("Land") && B.getland() == getLandType("Water")) return 4;
        else if ( this.getland() == getLandType("Trap") && B.getland() == getLandType("Land")) return 5;
        else if ( this.getland() == getLandType("Water") && B.getland() == getLandType("Land")) return 6;
        else if ( B.getland() == getLandType("Wall")) return 7;
        else return 0;
    }

}
