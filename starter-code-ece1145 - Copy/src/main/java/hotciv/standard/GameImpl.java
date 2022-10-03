package hotciv.standard;
import java.lang.*;


import hotciv.framework.*;

import java.sql.Time;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.Player.*;

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
  Player currentPlayer = null;
  int worldAge = -4000;
  int turnNumber = 0;
  int blueSize = 1;
  int redSize = 1;

  //List containing every Location with a city
  ArrayList<Position> CityLocations = new ArrayList<Position>();

  //Store the world building strategy
  worldBuild build;

  //add an ActionStategy variable
  private ActionStrategy actionStrategy;


 
  public Tile getTileAt( Position p ) {
    return World[p.getRow()][p.getColumn()];
  }
 
  public Unit getUnitAt( Position p ) {
    return ((TileImpl) getTileAt(p)).getUnit();
  }

  public void setUnitAt(Position p, Unit u) {
    ((TileImpl) getTileAt(p)).setUnit(u);
  }
  public void removeUnitAt(Position p) {
    ((TileImpl) getTileAt(p)).setUnit(null);
  }
 
  public City getCityAt( Position p ) { return ((TileImpl) getTileAt(p)).returnCity();}
 
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
  public Player getWinner() {
    Player winner= Player.RED;
    // TODO: implement algorithm to determine winner
    if (getAge() == -3000){
      return winner;
    }
    return null; }

  public int getAge() {
    if(endOfRound() == true){
      // worldAge varies increments after every round
      if (worldAge < -100){
        worldAge += 100;
      } else if (worldAge < -1){
        worldAge += 99;
      } else if (worldAge == -1 ) {
        worldAge += 2;
      } else if (worldAge == 1){
        worldAge += 49;
      } else if (worldAge < 1750){
        worldAge += 50;
      } else if (worldAge < 1900){
        worldAge += 25;
      } else if (worldAge < 1970){
        worldAge += 5;
      } else {
        worldAge += 1;
      }
    }
    return worldAge;
  }
 
  public boolean moveUnit( Position from, Position to ) {
    Unit fUnit = getUnitAt(from);
    Unit tUnit = getUnitAt(to);
    Tile m = getTileAt(to);
    //Check if terrain can be moved over
    if (m.getTypeString().equals(FOREST) || m.getTypeString().equals(HILLS) || m.getTypeString().equals(PLAINS)) {
      //Check units if valid to control or move into
      if (getUnitAt(from).getOwner() != currentPlayerInTurn)
        return false;
      else {
        if (tUnit.getOwner() != currentPlayerInTurn) {
          //Remove and replace unit if being attacked
          removeUnitAt(to);
          setUnitAt(to, fUnit);
          removeUnitAt(from);
          return true;
        } else if (tUnit.getOwner() == currentPlayerInTurn) {
          return false;
        }
        return true;
      }
    } else
      return false;
  }
 
  public void endOfTurn() {
    turnNumber++;
  }
  public boolean endOfRound(){
    // this will only work if only 2 people are playing
    if(turnNumber % 2 != 0 || turnNumber == 0){
      return false;
    } return true;
  }
 
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {
    Unit unit = getUnitAt(p);
    if (unit != null && currentPlayerInTurn.equals(unit.getOwner())) {
      actionStrategy.performUnitActionAt(World, p);
    }
  }


  //define the World constraints HERE, not the interface file
  private Tile[][] World;


  GameImpl(worldBuild buildMode)
  {
    this.build = buildMode;

    World = build.createWorld();
  }

  public Tile[][] returnWorld()
  {
    return World;
  }

  public void setTileTypeFromGame(Position p, String s)
  {
    ((TileImpl) World[p.getRow()][p.getColumn()]).setTileType(s);
  }

  public void setOwnerFromGame(Position pos, Player pl){
    ((TileImpl) World[pos.getRow()][pos.getColumn()]).setOwner(pl);
  }

  public void AddCityFromGame(Position pos, Player owner, String unitFocus, String workFocus){
    ((TileImpl) World[pos.getRow()][pos.getColumn()]).addCity(owner, unitFocus, workFocus);

    //Add the city to the list of positions with a city
    CityLocations.add(pos);
  }

  public void AddProductionEndOfRound(){

    //Loop through list of Positions with a city and add 6 production to each
    for (int i = 0; i < CityLocations.size(); i++){

      ((CityImpl) getCityAt(CityLocations.get(i))).addProduction(6);
    }
  }

  public void setWorkFocusFromGame(Position p, String s){

    if (!((TileImpl) getTileAt(p)).hasCity()){
      throw new RuntimeException("Entered position does not have a city");
    }

    ((CityImpl) getCityAt(p)).setWorkforceFocus(s);

  }

  public void setUnitFocusFromGame(Position p, String s){
    if (!((TileImpl) getTileAt(p)).hasCity()){
      throw new RuntimeException("Entered position does not have a city");
    }

    ((CityImpl) getCityAt(p)).setUnitFocus(s);
  }

  public void createNewUnitFromCity(Position p){

    if (!((TileImpl) getTileAt(p)).hasCity()){
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
      return;
    }

    //north of the city (one row up)
    if (p.getRow() != 0){
      Position newPos = new Position(p.getRow()+1,p.getColumn());
      ((TileImpl) getTileAt(newPos)).setUnit(tempUnit);
      ((CityImpl) getCityAt(p)).subtractProduction(tempCost);
      return;
    }

    //east of the city (one column right)
    if (p.getColumn() != 15){
      Position newPos = new Position(p.getRow(),p.getColumn()+1);
      ((TileImpl) getTileAt(newPos)).setUnit(tempUnit);
      ((CityImpl) getCityAt(p)).subtractProduction(tempCost);
      return;
    }

    //south of the city (one row down)
    if (p.getRow() != 15){
      Position newPos = new Position(p.getRow()-1,p.getColumn());
      ((TileImpl) getTileAt(newPos)).setUnit(tempUnit);
      ((CityImpl) getCityAt(p)).subtractProduction(tempCost);
      return;
    }

    //west of the city (one column left)
    if (p.getColumn() != 0){
      Position newPos = new Position(p.getRow(),p.getColumn()-1);
      ((TileImpl) getTileAt(newPos)).setUnit(tempUnit);
      ((CityImpl) getCityAt(p)).subtractProduction(tempCost);
    }

    //If all units are occupied, nothing is placed
  }
}
