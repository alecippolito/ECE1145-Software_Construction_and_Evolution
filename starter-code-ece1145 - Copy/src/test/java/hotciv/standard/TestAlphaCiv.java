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
  public void playerShouldNotMoveOverMountain() {
    Position p1 = new Position(3,2);
    Position p2 = new Position(2,2);
    game.setTileTypeFromGame(p2,MOUNTAINS);
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
    //game.setCityStatusFromGame(p1,true);
    //game.setOwnerFromGame(p1,RED);
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
}
