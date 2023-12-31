package hotciv.standard;
import java.lang.*;


import hotciv.framework.*;

import java.util.ArrayList;
import java.util.HashMap;

import static hotciv.framework.GameConstants.*;

/** Skeleton implementation of HotCiv.
 
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

public class GameImpl implements Game {
  private Player currentPlayerInTurn = Player.RED;

  int numPlayers = 2;
  Player currentPlayer = Player.RED;
  int turnNumber = 0;
  int blueSize = 1;
  int redSize = 1;
  int roundNumber = 0;

  int age = -4000;

  //Store the world building strategy
  private worldBuild worldLayout;

  //hold the factory class here
  private GameFactory factory;

  //add strategy variables
  private ActionStrategy actionStrategy;
  private AttackStrategy attackStrategy;
  private WinnerStrategy winnerStrategy;

  private AgingStrategy agingStrategy;
  private HashMap<Player, Integer> winHashMap = new HashMap<Player, Integer>();


  //observer class
    protected GameObserver observer;

    /**
     * Constructor
     */
    public GameImpl(GameFactory factory) {
        this.factory = factory;

        this.worldLayout = factory.createWorldBuild();
        this.actionStrategy = factory.createActionStrategy();
        this.attackStrategy = factory.createAttackStrategy();
        this.winnerStrategy = factory.createWinnerStrategy();
        this.agingStrategy = factory.createAgingStrategy();
        winHashMap = new HashMap<>();
    }
 
  public Tile getTileAt( Position p ) {
    return worldLayout.returnTiles().get(p);
  }
 
  public Unit getUnitAt( Position p ) {
    return ((TileImpl) getTileAt(p)).getUnit();
  }

  public void setUnitAt(Position p, Unit u) {

        ((TileImpl) getTileAt(p)).setUnit(u);

      /**
       * UPDATE OBSERVER
       */
      updateObserversWorld(p);
  }
  public void removeUnitAt(Position p) {

        ((TileImpl) getTileAt(p)).setUnit(null);

      /**
       * UPDATE OBSERVER
       */
      updateObserversWorld(p);
  }
 
  public City getCityAt( Position p ) {
        if (worldLayout.returnCities().containsKey(p))
        {
            return worldLayout.returnCities().get(p);
        }
        else{
            return null;
        }
    }

   public Player getCurrentPlayer(){
        return currentPlayer;
   }
  public Player getPlayerInTurn() {
    if( currentPlayer == null){
      currentPlayer = Player.BLUE;
      return Player.RED;
    }
    if(currentPlayer == Player.RED) {
      currentPlayer = Player.BLUE;
      return Player.RED;
    }if(currentPlayer == Player.BLUE) {
      currentPlayer = Player.RED;
      return Player.BLUE;
      // return Player.BLUE; -- remove comment after more players can play
    }
    /**
     if(currentPlayer == Player.YELLOW) {
     currentPlayer = Player.YELLOW;
     return Player.YELLOW;
     }if(currentPlayer == Player.GREEN) {
     currentPlayer = Player.RED;
     return Player.GREEN;
     }
     **/
    return null;
  }
  // worldLayout.returnTiles()
    public Player getWinner() {
        Player winner = winnerStrategy.getWinner(getAge(),worldLayout.returnCities(),winHashMap, roundNumber);
        if (winner != null){ System.out.println(winner + " has won!");};
        return winner;
    }

public int getCurrentAge(){
        return age;
}
  public int getAge() {
      age = agingStrategy.getAge(endOfRound());
      System.out.println("Current Age is " + age);
      return age;
  }
 
  public boolean moveUnit( Position from, Position to ) {
        System.out.print(currentPlayerInTurn + " moves from " + from + " to " + to);

    Unit fUnit = getUnitAt(from);
    Unit tUnit = getUnitAt(to);

    // UFO boolean variable
    boolean checkUFO = fUnit.getTypeString().equals(GameConstants.UFO);

    //Check if terrain can be moved over
    Tile m = getTileAt(to);
    if ((m.getTypeString().equals(MOUNTAINS) || m.getTypeString().equals(OCEANS)) && !checkUFO)
      return false;

    Player attacker = fUnit.getOwner();
    Player defender;
    if (tUnit != null)
      defender = tUnit.getOwner();
    else
      defender = Player.BLANK;

    int attackerAdjacentUnits = 0;
    int attackerTerrainFactor = 1;
    int defenderAdjacentUnits = 0;
    int defenderTerrainFactor = 1;

    attackerAdjacentUnits = getNumberOfAdjacentUnits(from, attacker);
    defenderAdjacentUnits = getNumberOfAdjacentUnits(to, defender);
    attackerTerrainFactor = getTerrainFactor(from);
    defenderTerrainFactor = getTerrainFactor(to);


    //Check units if valid to control
    if (attacker != currentPlayerInTurn)
      return false;
    //Check units if valid to move into
    if ((Math.abs(from.getRow()-to.getRow()) > 1
            || Math.abs(from.getColumn()-to.getColumn()) > 1
            || ((Math.abs(from.getRow()-to.getRow()) == 0) && (Math.abs(from.getColumn()-to.getColumn()) == 0))) && !checkUFO) {
      return false;
    }
    //Check UFO movement
    else if ((Math.abs(from.getRow()-to.getRow()) > 2
            || Math.abs(from.getColumn()-to.getColumn()) > 2
            || ((Math.abs(from.getRow()-to.getRow()) == 0) && (Math.abs(from.getColumn()-to.getColumn()) == 0))) && checkUFO) {
      return false;
    }

    if (defender == currentPlayerInTurn) {
      return false;
    } else {

      if (tUnit != null) {
        // Unit conducts attack
        if (attackStrategy.attack(fUnit, tUnit, attackerAdjacentUnits, attackerTerrainFactor,
                defenderAdjacentUnits, defenderTerrainFactor)) {
          if (getCityAt(to) != null && !checkUFO) {
            getCityAt(to).setOwner(attacker);
          }
          setUnitAt(to, fUnit);
          removeUnitAt(from);
          fUnit.canMove();
          winnerStrategy.countAttack(winHashMap, attacker);
        }
        // Unit fails attack
        else {
          removeUnitAt(from);
        }
      }
      setUnitAt(to, fUnit);
      removeUnitAt(from);
      fUnit.canMove();


        /**
         * UPDATE OBSERVER
         */
        updateObserversWorld(to);
        updateObserversWorld(from);


      return true;
    }
  }
 
  public void endOfTurn() {
        System.out.println(currentPlayerInTurn + "'s turn is over");
    turnNumber++;
      /**
       * UPDATE OBSERVER
       */
      updateObserversTurn(getPlayerInTurn(),getAge());
  }
  public boolean endOfRound(){
    // this will only work if only 2 people are playing
    if(turnNumber % 2 != 0 || turnNumber == 0){
      return false;
    }
    System.out.println("End of round " + roundNumber);
    roundNumber++;
    AddProductionEndOfRound();
    return true;
  }
 
 private int getNumberOfAdjacentUnits(Position p, Player player) {

    int x = p.getRow();
    int y = p.getColumn();
    int numberOfAdjacentUnits = 0;

    if (getUnitAt(new Position(x, Math.min(GameConstants.WORLDSIZE-1, y+1))) != null
            && getUnitAt(new Position(x, Math.min(GameConstants.WORLDSIZE-1, y+1))).getOwner() == player) {
      numberOfAdjacentUnits++;
    }
    if (getUnitAt(new Position(Math.min(GameConstants.WORLDSIZE-1, x+1), Math.min(GameConstants.WORLDSIZE-1, y+1))) != null
            && getUnitAt(new Position(Math.min(GameConstants.WORLDSIZE-1, x+1), Math.min(GameConstants.WORLDSIZE-1, y+1))).getOwner() == player) {
      numberOfAdjacentUnits++;
    }
    if (getUnitAt(new Position(Math.min(GameConstants.WORLDSIZE-1, x+1), y)) != null
            && getUnitAt(new Position(Math.min(GameConstants.WORLDSIZE-1, x+1), y)).getOwner() == player) {
      numberOfAdjacentUnits++;
    }
    if (getUnitAt(new Position(Math.min(GameConstants.WORLDSIZE-1, x+1), Math.max(GameConstants.WORLDSIZE-1, y-1))) != null
            && getUnitAt(new Position(Math.min(GameConstants.WORLDSIZE-1, x+1), Math.max(GameConstants.WORLDSIZE-1, y-1))).getOwner() == player) {
      numberOfAdjacentUnits++;
    }
    if (getUnitAt(new Position(x, Math.max(GameConstants.WORLDSIZE-1, y-1))) != null
            && getUnitAt(new Position(x, Math.max(GameConstants.WORLDSIZE-1, y-1))).getOwner() == player) {
      numberOfAdjacentUnits++;
    }
    if (getUnitAt(new Position(Math.max(GameConstants.WORLDSIZE-1, x-1), Math.max(GameConstants.WORLDSIZE-1, y-1))) != null
            && getUnitAt(new Position(Math.max(GameConstants.WORLDSIZE-1, x-1), Math.max(GameConstants.WORLDSIZE-1, y-1))).getOwner() == player) {
      numberOfAdjacentUnits++;
    }
    if (getUnitAt(new Position(Math.max(GameConstants.WORLDSIZE-1, x-1), y)) != null
            && getUnitAt(new Position(Math.max(GameConstants.WORLDSIZE-1, x-1), y)).getOwner() == player) {
      numberOfAdjacentUnits++;
    }
    if (getUnitAt(new Position(Math.max(GameConstants.WORLDSIZE-1, x-1), Math.min(GameConstants.WORLDSIZE-1, y+1))) != null
            && getUnitAt(new Position(Math.max(GameConstants.WORLDSIZE-1, x-1), Math.min(GameConstants.WORLDSIZE-1, y+1))).getOwner() == player) {
      numberOfAdjacentUnits++;
    }

    return numberOfAdjacentUnits;
  }

  public int getTerrainFactor(Position p) {
    String terrain = getTileAt(p).getTypeString();

    if (getCityAt(p) != null){
      return 3;
    }
    else if(terrain.equals(GameConstants.FOREST) || terrain.equals(GameConstants.HILLS)) {
      return 2;
    }
    else {
      return 1;
    }
  }
 
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {

      /**
       * UPDATE OBSERVER
       */
      updateObserversWorld(p);
  }
  public void changeProductionInCityAt( Position p, String unitType ) {
        /**
       * UPDATE OBSERVER
       */
      updateObserversWorld(p);
    }
  public void performUnitActionAt( Position p ) {
        System.out.println(currentPlayerInTurn + " performing unit action at " + p);
    Unit unit = getUnitAt(p);
    if (unit != null && currentPlayerInTurn.equals(unit.getOwner())) {
      actionStrategy.performUnitActionAt(worldLayout.returnTiles(), worldLayout.returnCities(),p);
    }

      /**
       * UPDATE OBSERVER
       */
      updateObserversWorld(p);
  }

  public HashMap<Position, Tile> returnWorld()
  {
    return worldLayout.returnTiles();
  }

  public void setTileTypeFromGame(Position p, String s)
  {
    ((TileImpl) worldLayout.returnTiles().get(p)).setTileType(s);

      /**
       * UPDATE OBSERVER
       */
      updateObserversWorld(p);
  }

  public void setOwnerFromGame(Position p, Player pl){
    ((TileImpl) worldLayout.returnTiles().get(p)).setOwner(pl);
  }

  public void AddProductionEndOfRound(){

    //Loop through list of Positions with a city and add 6 production to each
    for (Position p: worldLayout.returnCities().keySet()){
      ((CityImpl) worldLayout.returnCities().get(p)).addProduction(6);
        /**
         * UPDATE OBSERVER
         */
        updateObserversWorld(p);
    }
  }

  public void createNewUnitFromCity(Position p){

    if (!worldLayout.returnCities().containsKey(p)){
      throw new RuntimeException("Entered position does not have a city");
    }

    //Define a unit
    String unit = ((CityImpl) getCityAt(p)).getUnitFocus();
    Unit tempUnit = null;
    int tempCost = 0;

    if (unit == ARCHER){
      tempUnit = new UnitArcher(((TileImpl) getTileAt(p)).getOwner());
      tempCost = 10;
    } else if (unit == LEGION) {
      tempUnit = new UnitLegion(((TileImpl) getTileAt(p)).getOwner());
      tempCost = 15;
    } else if (unit == SETTLER) {
      tempUnit = new UnitSettler(((TileImpl) getTileAt(p)).getOwner());
      tempCost = 30;
    }


    //step 2: determine the correct location to place the unit

    //at the city
    if (((TileImpl) getTileAt(p)).getUnit() == null){
      ((TileImpl) getTileAt(p)).setUnit(tempUnit);
      ((CityImpl) getCityAt(p)).subtractProduction(tempCost);

        /**
         * UPDATE OBSERVER
         */
        updateObserversWorld(p);
      return;
    }

    //north of the city (one row up)
    if (p.getRow() != 0){
      Position newPos = new Position(p.getRow()+1,p.getColumn());
      ((TileImpl) getTileAt(newPos)).setUnit(tempUnit);
      ((CityImpl) getCityAt(p)).subtractProduction(tempCost);

        /**
         * UPDATE OBSERVER
         */
        updateObserversWorld(p);
      return;
    }

    //east of the city (one column right)
    if (p.getColumn() != 15){
      Position newPos = new Position(p.getRow(),p.getColumn()+1);
      ((TileImpl) getTileAt(newPos)).setUnit(tempUnit);
      ((CityImpl) getCityAt(p)).subtractProduction(tempCost);

        /**
         * UPDATE OBSERVER
         */
        updateObserversWorld(p);
      return;
    }

    //south of the city (one row down)
    if (p.getRow() != 15){
      Position newPos = new Position(p.getRow()-1,p.getColumn());
      ((TileImpl) getTileAt(newPos)).setUnit(tempUnit);
      ((CityImpl) getCityAt(p)).subtractProduction(tempCost);

        /**
         * UPDATE OBSERVER
         */
        updateObserversWorld(p);
      return;
    }

    //west of the city (one column left)
    if (p.getColumn() != 0){
      Position newPos = new Position(p.getRow(),p.getColumn()-1);
      ((TileImpl) getTileAt(newPos)).setUnit(tempUnit);
      ((CityImpl) getCityAt(p)).subtractProduction(tempCost);

        /**
         * UPDATE OBSERVER
         */
        updateObserversWorld(p);
    }

    //If all units are occupied, nothing is placed

  }

    /**
     * CHECK TO SEE IF A CITY EXISTS AT A CERTAIN LOCATION
   */
  public boolean cityExists(Position p){
    return worldLayout.returnCities().containsKey(p);
  }

  /**
   * RETURN THE WHOLE LIST OF CITIES AS A HASH MAP
   * @return the hash map of all cities
   */
  public HashMap<Position,City> returnCities(){
    return worldLayout.returnCities();
  }

    /**
     * Return the Action strategy variable - used for testing
     */
    public ActionStrategy returnActionStrategy(){
        return this.actionStrategy;
    }
    public int getRoundNumber(){return roundNumber;}

    @Override
    public void addObserver(GameObserver observer) {
        this.observer = observer;
    }

    @Override
    public void setTileFocus(Position position) {
        //fill in code here

        /**
         * UPDATE OBSERVER
         */
        tileFocusedChangedAt(position);
    }

    /**
     *  Update the observers when called
     */
    public void updateObserversWorld(Position p){

        /**
         * UNITS AND CITY UPDATES FOR DISPLAY
         */
        if (observer != null) {
            observer.worldChangedAt(p);
        }
    }
    public void tileFocusedChangedAt(Position p){
        /**
         * A CHANGE IN THE TILE THE PLAYER IS FOCUSING ON
         */
        if (observer != null) {
            observer.tileFocusChangedAt(p);
        }
    }
    public void updateObserversTurn(Player nextPlayer, int age){
        /**
         * WHEN THE TURN ENDS; NEW PLAYER AND AGE
         */
        if (observer != null) {
            observer.turnEnds(nextPlayer, age);
        }
    }
}
