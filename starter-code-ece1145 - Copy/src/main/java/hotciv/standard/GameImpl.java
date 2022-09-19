package hotciv.standard;

import hotciv.framework.*;

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
 
  public Tile getTileAt( Position p ) {
    return getTile(p);
  }
  public Unit getUnitAt( Position p ) {
    return getTileAt(p).getUnit();
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
  public int getAge() {  /** return the age of the world. Negative numbers represent a world
   * age BC (-4000 equals 4000 BC) while positive numbers are AD.
   *  @return world age.
   */
    // increment 100 after every year
    if(endOfRound() == true){
      worldAge = worldAge + 100;
    }
    return worldAge;}
  public boolean moveUnit( Position from, Position to ) {
    Unit fUnit = getUnitAt(from);
    Unit tUnit = getUnitAt(to);
    Tile m = getTileAt(to);
    //Check if unit can move to position and is not blocked by mountains or oceans
    if (m.getTypeString().equals(MOUNTAINS) || m.getTypeString().equals(OCEANS))
      return false;
    //Check units if valid to control or move into
    else if (!fUnit.getOwner().equals(currentPlayerInTurn) || (tUnit.getOwner().equals(currentPlayerInTurn) && fUnit.getOwner().equals(currentPlayerInTurn)))
      return false;
    else
      return true;
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
  private Tile[][] World = new Tile[WORLDSIZE][WORLDSIZE];


  GameImpl()
  {
    //Define the size of the World
    //World = new Tile[WORLDSIZE][WORLDSIZE];

    //give Tiles to every position in the world
    //for now: initialize to PLAINS
    Tile initialTile = new TileImpl();
    initialTile.setTileType(PLAINS);
    //System.out.print(initialTile.getOwner() + " ");
    //System.out.print(initialTile.hasCity() + "\n");
    //loop through the World and initialize the tiles
    for (int i = 0; i < WORLDSIZE; i++) {
      for (int j = 0; j < WORLDSIZE; j++) {
        World[i][j] = initialTile;
      }
    }
  }

  public Tile[][] returnWorld()
  {
    return World;
  }

  public void setTileType(Position p, String s)
  {
    World[p.getRow()][p.getColumn()].setTileType(s);
  }

  public Tile getTile(Position p){
    return World[p.getRow()][p.getColumn()];
  }

  public void setOwnerFromGame(Position pos, Player pl){
    World[pos.getRow()][pos.getColumn()].setOwner(pl);
  }

  public void setCityStatusFromGame(Position pos, boolean status){
    World[pos.getRow()][pos.getColumn()].changeCityStatus(status);
  }
}
