package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.GameConstants.*;


public class CityImpl implements City {

    //Has an owner
    Player CityOwner = null;

    //keep track of population size
    int population = 1;

    //keep track of the treasury money/production
    int production = 0;

    //keep track of what the city is trying to produce
    String WorkFocus;
    String UnitFocus;

    //Every time the Unit focus is updated, change the internal cost of the unit
    int UnitCost = -1;


    /**
     * Constructor: Define the variables when you first call it
     */
    CityImpl(Player p, String UnitFocus, String WorkFocus){
        setOwner(p);
        setUnitFocus(UnitFocus);
        setWorkforceFocus(WorkFocus);
    }

    /**
     * Another constructor: Only containing the owner
     */
    CityImpl(Player p){
        setOwner(p);
    }


    /** manually set the owner of the city
     * @param p Player
     */
    public void setOwner(Player p){
        this.CityOwner = p;
    }

    /** return the owner of this city.
     * @return the player that controls this city.
     */
    public Player getOwner(){
        return CityOwner;
    };

    /** return the size of the population.
     * @return population size.
     */
    public int getSize(){return population;};

    /** return the treasury, i.e. the
     * number of 'money'/production in the
     * city's treasury which can be used to
     * produce a unit in this city
     * @return an integer, the amount of production
     * in the city treasury
     */
    public int getTreasury(){return production;};

    /** return the type of unit this city is currently producing.
     * @return a string type defining the unit under production,
     * see GameConstants for valid values.
     */
    public String getProduction(){return UnitFocus;};

    public void addProduction(int p){
        production += p;

        //In addition to adding production, we will check if there is enough production to make the required unit
    }

    public void subtractProduction(int p){
        production -= p;
    }

    /** return the work force's focus in this city.
     * @return a string type defining the focus, see GameConstants
     * for valid return values.
     */
    public String getWorkforceFocus(){return WorkFocus;};

    public void setWorkforceFocus(String s){
        WorkFocus = s;
    }

    public void setUnitFocus(String s){
        UnitFocus = s;

        if (s == GameConstants.ARCHER){

            //Cost of 10
            UnitCost = 10;

        } else if (s == GameConstants.LEGION) {

            //Cost of 15
            UnitCost = 15;

        } else if (s == GameConstants.SETTLER) {

            //Cost of 30
            UnitCost = 30;
        } else {
            UnitCost = -1;
        }
    }

    public String getUnitFocus(){
        return UnitFocus;
    }

    public boolean NewUnitPossible(){

        //Test if the focus is on production, not food
        if (WorkFocus == GameConstants.productionFocus){

            //Test if we have enough production to produce a unit
            if (production >= UnitCost && UnitCost != -1){

                //All tests passed, return true
                return true;
            }
        }
        return false;
    }
}
