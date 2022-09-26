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

 
  public Tile getTileAt( Position p ) {
    return getTile(p);
  }
 
  public Unit getUnitAt( Position p ) {
    return getTileAt(p).getUnit();
  }
 
  public void setUnitAt(Position p, Unit u) {
    getTileAt(p).setUnit(u);
  }
  public void removeUnitAt(Position p) {
    getTileAt(p).setUnit(null);
  }
 
  public City getCityAt( Position p ) { return null; }
 
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
    // increment 100 after every year
    if(endOfRound() == true){
      worldAge = worldAge + 100;
    }
    return worldAge;}
 
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
  public void performUnitActionAt( Position p ) {}


  //define the World constraints HERE, not the interface file
  private Tile[][] World;


  GameImpl()
  {
    //Define the size of the World
    World = new Tile[WORLDSIZE][WORLDSIZE];

    //give Tiles to every position in the world
    //for now: initialize to PLAINS
    //loop through the World and initialize the tiles
    for (int i = 0; i < WORLDSIZE; i++) {
      for (int j = 0; j < WORLDSIZE; j++) {

        //have to define a new Tile pointer for each location to not have issues
        Tile tempTile = new TileImpl();
        tempTile.setTileType(PLAINS);

        World[i][j] = tempTile;
      }
    }


    //initialize certain points to be different Tiles

    //Mountain at (2,2)
    World[2][2].setTileType(MOUNTAINS);

    //Ocean at (1,0)
    World[1][0].setTileType(OCEANS);

    //Hills at (0,1)
    World[0][1].setTileType(HILLS);


    //initialize city location
    //Red city at 1,1
    Position p1 = new Position(1,1);
    AddCityFromGame(p1);
    setOwnerFromGame(p1,RED);
    setUnitFocusFromGame(p1,LEGION);
    setWorkFocusFromGame(p1,productionFocus);

    //Blue city at 4,1
    p1 = new Position(4,1);
    AddCityFromGame(p1);
    setOwnerFromGame(p1,BLUE);
  }

  public Tile[][] returnWorld()
  {
    return World;
  }

  public void setTileTypeFromGame(Position p, String s)
  {
    World[p.getRow()][p.getColumn()].setTileType(s);
  }

  public Tile getTile(Position p){
    return World[p.getRow()][p.getColumn()];
  }

  public void setOwnerFromGame(Position pos, Player pl){
    World[pos.getRow()][pos.getColumn()].setOwner(pl);
  }

  public void AddCityFromGame(Position pos){
    World[pos.getRow()][pos.getColumn()].addCity();

    //Add the city to the list of positions with a city
    CityLocations.add(pos);
  }

  public void AddProductionEndOfRound(){

    //Loop through list of Positions with a city and add 6 production to each
    for (int i = 0; i < CityLocations.size(); i++){

      getTile(CityLocations.get(i)).returnCity().addProduction(6);
    }
  }

  public void setWorkFocusFromGame(Position p, String s){

    if (getTile(p).hasCity() == false){
      throw new RuntimeException("Entered position does not have a city");
    }

    getTile(p).returnCity().setWorkforceFocus(s);

  }

  public void setUnitFocusFromGame(Position p, String s){
    if (getTile(p).hasCity() == false){
      throw new RuntimeException("Entered position does not have a city");
    }

    getTile(p).returnCity().setUnitFocus(s);
  }

  public void createNewUnitFromCity(Position p){

    if (getTile(p).hasCity() == false){
      throw new RuntimeException("Entered position does not have a city");
    }

    //Define a unit
    String unit = getTile(p).returnCity().getUnitFocus();
    Unit tempUnit = null;
    int tempCost = 0;

    if (unit == ARCHER){
      tempUnit = new UnitArcher(getTile(p).getOwner());
      tempCost = 10;
    } else if (unit == LEGION) {
      tempUnit = new UnitLegion(getTile(p).getOwner());
      tempCost = 15;
    } else if (unit == SETTLER) {
      tempUnit = new UnitSettler(getTile(p).getOwner());
      tempCost = 30;
    }


    //step 2: determine the correct location to place the unit

    //at the city
    if (getTile(p).getUnit() == null){
      getTile(p).setUnit(tempUnit);
      getTile(p).returnCity().subtractProduction(tempCost);
      return;
    }

    //north of the city (one row up)
    if (p.getRow() != 0){
      Position newPos = new Position(p.getRow()+1,p.getColumn());
      getTile(newPos).setUnit(tempUnit);
      getTile(p).returnCity().subtractProduction(tempCost);
      return;
    }

    //east of the city (one column right)
    if (p.getColumn() != 15){
      Position newPos = new Position(p.getRow(),p.getColumn()+1);
      getTile(newPos).setUnit(tempUnit);
      getTile(p).returnCity().subtractProduction(tempCost);
      return;
    }

    //south of the city (one row down)
    if (p.getRow() != 15){
      Position newPos = new Position(p.getRow()-1,p.getColumn());
      getTile(newPos).setUnit(tempUnit);
      getTile(p).returnCity().subtractProduction(tempCost);
      return;
    }

    //west of the city (one column left)
    if (p.getColumn() != 0){
      Position newPos = new Position(p.getRow(),p.getColumn()-1);
      getTile(newPos).setUnit(tempUnit);
      getTile(p).returnCity().subtractProduction(tempCost);
    }

    //If all units are occupied, nothing is placed
  }
}
