package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.Player.*;
import java.util.*;


/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

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
public class TestAlphaCiv {
  private Game game;

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }
  @Test
  public void shouldHaveRedWinInYear3000(){
    assertThat(game, is(notNullValue()));
    if (game.getAge()== -3000){
      assertThat(game.getWinner(), is(Player.RED));
    }
  }
  @Test
  public void shouldStartAgeAtNegatve4000(){
    assertThat(game, is(notNullValue()));
    assertThat(game.getAge(), is(-4000));
  }
  @Test
  public void shouldHaveBlueAsSecondInTurn(){
    assertThat(game, is(notNullValue()));
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }
  @Test
  public void shouldDecreaseBy100YearsEachRound(){
    assertThat(game, is(notNullValue()));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getAge(), is(-3900));
  }

  @Test
  public void testTileIsOceanAtRow1Column0() {
    // hopefully this works


    //System.out.print(game.returnTile(p1).getTypeString());
    Position p1 = new Position(1,0);
    assertThat(game.getTile(p1).getTypeString(), is(OCEANS));
  }

  @Test
  public void testTileIsMountainAtRow2Column2() {

    Position p1 = new Position(2,2);
    assertThat(game.getTile(p1).getTypeString(), is(MOUNTAINS));
  }

  @Test
  public void testTileIsHillsAtRow0Column1() {
    Position p1 = new Position(0,1);
    assertThat(game.getTile(p1).getTypeString(), is(HILLS));
  }

  @Test
  public void RedHasArcherAtPosition() {
    Position p = new Position(2, 0);
    Unit unitArcher = new UnitArcher(Player.RED);
    game.setUnitAt(p, unitArcher);
    assertThat(game.getUnitAt(p).getTypeString(), is(ARCHER));
    assertThat(game.getUnitAt(p).getOwner(), is(Player.RED));
  }

  @Test
  public void RedHasSettlerAtPosition() {
    Position p = new Position(4, 3);
    Unit unitSettler = new UnitSettler(Player.RED);
    game.setUnitAt(p, unitSettler);
    assertThat(game.getUnitAt(p).getTypeString(), is(SETTLER));
    assertThat(game.getUnitAt(p).getOwner(), is(Player.RED));
  }

  @Test
  public void BlueHasLegionAtPosition() {
    Position p = new Position(3, 2);
    Unit unitLegion = new UnitLegion(Player.BLUE);
    game.setUnitAt(p, unitLegion);
    assertThat(game.getUnitAt(p).getTypeString(), is(LEGION));
    assertThat(game.getUnitAt(p).getOwner(), is(Player.BLUE));
  }
    
  @Test
  public void playerShouldNotMoveOverMountain() {
    Position p1 = new Position(3, 2);
    Position p2 = new Position(2, 2);
    game.setTileTypeFromGame(p2, MOUNTAINS);
    assertThat(game.moveUnit(p1, p2), is(false));
  }
    
  @Test
  public void playerShouldNotMoveOverOcean() {
    Position p1 = new Position(2, 0);
    Position p2 = new Position(1, 0);
    game.setTileTypeFromGame(p2, OCEANS);
    assertThat(game.moveUnit(p1, p2), is(false));
  }

  @Test
  public void testRedOwnCityAtRow1Column1() {

    //tiles and ownership should be initialized in this iteration's constructor for the game class
    Position p1 = new Position(1,1);
    assertThat(game.getTile(p1).hasCity(), is(true));
    assertThat(game.getTile(p1).getOwner(), is(Player.RED));
  }

  @Test
  public void testBlueOwnCityAtRow4Column1() {

    //tiles and ownership should be initialized in this iteration's constructor for the game class
    Position p1 = new Position(4,1);
    assertThat(game.getTile(p1).hasCity(), is(true));
    assertThat(game.getTile(p1).getOwner(), is(Player.BLUE));
  }

  @Test
  public void testCityPopulationSizeEqualsOne() {

    //Cities were defined in the constructor for this iteration (2)- so we will test both cities for their population size
    Position p1 = new Position(1, 1);
    Position p2 = new Position(4, 1);
    assertThat(game.getTile(p1).returnCity().getSize(), is(1));
    assertThat(game.getTile(p2).returnCity().getSize(), is(1));
  }

  @Test
  public void RedShouldNotMoveBlue() {
    Position p1 = new Position(3, 2);
    Position p2 = new Position(3, 1);
    Unit unitLegion = new UnitLegion(Player.BLUE);
    game.setUnitAt(p1, unitLegion);
    assertThat(game.moveUnit(p1, p2), is(false));
  }
    
  @Test
  public void AttackUnit() {
    Position p1 = new Position(3, 2);
    Position p2 = new Position(4, 3);
    Unit unitLegion = new UnitLegion(Player.RED);
    game.setUnitAt(p1, unitLegion);
    Unit unitSettler = new UnitSettler(Player.BLUE);
    game.setUnitAt(p2, unitSettler);
    assertThat(game.moveUnit(p1, p2), is(true));
    assertThat(game.getUnitAt(p2).getTypeString(), is(LEGION));
  }

  @Test
  public void TestAddingNewUnitWithEnoughProductionFromCity() {

    //Test 1: Add production to cities, the production number changes
    Position p1 = new Position(1,1);
    assertThat(game.getTileAt(p1).returnCity().getTreasury(), is(0));
    game.getTileAt(p1).returnCity().addProduction(10);
    assertThat(game.getTileAt(p1).returnCity().getTreasury(), is(10));


    //Test 2: If the production is high enough, a new Unit is created
    //For the city at (1,1), the UnitFocus is already defined in the constructor - Legion
    assertThat(game.getTile(p1).returnCity().getUnitFocus(), is(LEGION));
    assertThat(game.getTile(p1).returnCity().NewUnitPossible(), is(false));
    game.getTile(p1).returnCity().addProduction(5);
    assertThat(game.getTile(p1).returnCity().NewUnitPossible(), is(true));


    //Test 3: create a Unit either on or Adjacent to City
    if (game.getTileAt(p1).returnCity().NewUnitPossible() == true){
      game.createNewUnitFromCity(p1);

      //should be a unit on the tile with the city
      assertThat(game.getTile(p1).getUnit().getTypeString(), is(LEGION));

      //Test 4: Production is subtracted when a new unit is placed
      assertThat(game.getTile(p1).returnCity().getTreasury(), is(0));
    }

  }
}
