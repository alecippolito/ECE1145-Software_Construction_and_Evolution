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
  public Tile getTileAt( Position p ) { return null; }
  public Unit getUnitAt( Position p ) { return null; }
  public City getCityAt( Position p ) { return null; }
  public Player getPlayerInTurn() { return null; }
  public Player getWinner() { return null; }
  public int getAge() { return 0; }
  public boolean moveUnit( Position from, Position to ) {
    return false;
  }
  public void endOfTurn() {}
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
