package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;



public class CityImpl implements City {

    //Has an owner
    Player CityOwner = null;

    //keep track of population size
    int population = 1;

    /** manually set the owner of the city
     * @param p Player
     */
    public void setOwner(Player p){
        CityOwner = p;
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
    public int getSize(){return 0;};

    /** return the treasury, i.e. the
     * number of 'money'/production in the
     * city's treasury which can be used to
     * produce a unit in this city
     * @return an integer, the amount of production
     * in the city treasury
     */
    public int getTreasury(){return 0;};

    /** return the type of unit this city is currently producing.
     * @return a string type defining the unit under production,
     * see GameConstants for valid values.
     */
    public String getProduction(){return "";};

    /** return the work force's focus in this city.
     * @return a string type defining the focus, see GameConstants
     * for valid return values.
     */
    public String getWorkforceFocus(){return "";};

    public int returnPopulation(){
        return population;
    }
}
