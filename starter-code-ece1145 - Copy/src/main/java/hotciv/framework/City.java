package hotciv.framework;

/** Represents a city in the game.

Responsibilities:
1) Knows its owner.
2) Knows its population size.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
public interface City {

  /** manually set the owner of the city
   * @param p Player
   */
  public void setOwner(Player p);


  /** return the owner of this city.
   * @return the player that controls this city.
   */
  public Player getOwner();
  
  /** return the size of the population.
   * @return population size.
   */
  public int getSize();

  /** return the treasury, i.e. the
   * number of 'money'/production in the
   * city's treasury which can be used to
   * produce a unit in this city
   * @return an integer, the amount of production
   * in the city treasury
   */
  public int getTreasury();

  /** return the type of unit this city is currently producing.
   * @return a string type defining the unit under production,
   * see GameConstants for valid values.
   */
  public String getProduction();

  /**
   * Add production to the city
   * @param p -> an ADDITIONAL amount of production
   */
  public void addProduction(int p);

  /**
   * Subtract production from the city
   * @param p the amount of production you want to subtract
   */
  public void subtractProduction(int p);

  /** return the work force's focus in this city.
   * @return a string type defining the focus, see GameConstants
   * for valid return values.
   */
  public String getWorkforceFocus();


  /**
   * Manually set the work force focus
   * @param s A GameConstant string defining the current city focus
   */
  public void setWorkforceFocus(String s);

  /**
   * Manually set which Unit the City is Trying to Produce
   * @param s the Unit the City wants to produce
   */
  public void setUnitFocus(String s);

  /**
   * Get the Unit Focus of the City
   * @return the game constant string of the Unit being produced
   */
  public String getUnitFocus();

  /**
   * Run this every time production is added to test if we can create a new Unit
   */
  public boolean NewUnitPossible();



}
