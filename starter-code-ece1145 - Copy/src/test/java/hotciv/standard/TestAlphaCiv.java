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
    // TODO: reenable the assert below to get started...
    // assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  /** REMOVE ME. Not a test of HotCiv, just an example of what
      matchers the hamcrest library has... */
  @Test
  public void shouldDefinetelyBeRemoved() {
    // Matching null and not null values
    // 'is' require an exact match
    String s = null;
    assertThat(s, is(nullValue()));
    s = "Ok";
    assertThat(s, is(notNullValue()));
    assertThat(s, is("Ok"));

    // If you only validate substrings, use containsString
    assertThat("This is a dummy test", containsString("dummy"));

    // Match contents of Lists
    List<String> l = new ArrayList<String>();
    l.add("Bimse");
    l.add("Bumse");
    // Note - ordering is ignored when matching using hasItems
    assertThat(l, hasItems(new String[] {"Bumse","Bimse"}));

    // Matchers may be combined, like is-not
    assertThat(l.get(0), is(not("Bumse")));
  }

  @Test
  public void testTileIsOceanAtRow1Column0() {
    // hopefully this works


    //System.out.print(game.returnTile(p1).getTypeString());
    Position p1 = new Position(1,0);
    game.setTileType(p1,OCEANS);
    assertThat(game.getTile(p1).getTypeString(), is(OCEANS));
  }

  @Test
  public void testTileIsMountainAtRow2Column2() {

    Position p1 = new Position(2,2);
    game.setTileType(p1,MOUNTAINS);
    assertThat(game.getTile(p1).getTypeString(), is(MOUNTAINS));
  }
    
  @Test
  public void playerShouldNotMoveOverMountain() {
    Position p1 = new Position(3,2);
    Position p2 = new Position(2,2);
    game.setTileType(p2,MOUNTAINS);
    assertThat(game.moveUnit(p1, p2), is(false));
  }
    
  @Test
  public void playerShouldNotMoveOverOcean() {
    Position p1 = new Position(2,0);
    Position p2 = new Position(1,0);
    game.setTileType(p2,OCEANS);
    assertThat(game.moveUnit(p1, p2), is(false));

  @Test
  public void testRedOwnCityAtRow1Column1() {

    //tiles and ownership should be initialized in this iteration's constructor for the game class
    Position p1 = new Position(1,1);
    game.setCityStatusFromGame(p1,true);
    game.setOwnerFromGame(p1,RED);
    assertThat(game.getTile(p1).hasCity(), is(true));
    assertThat(game.getTile(p1).getOwner(), is(Player.RED));
  }

  @Test
  public void testBlueOwnCityAtRow4Column1() {

    //tiles and ownership should be initialized in this iteration's constructor for the game class
    Position p1 = new Position(4,1);
    game.setOwnerFromGame(p1,BLUE);
    game.setCityStatusFromGame(p1,true);
    assertThat(game.getTile(p1).hasCity(), is(true));
    assertThat(game.getTile(p1).getOwner(), is(Player.BLUE));
  }
}