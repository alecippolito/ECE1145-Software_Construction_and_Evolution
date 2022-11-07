package hotciv.standard;

import hotciv.alternative.*;
import hotciv.framework.*;
import hotciv.standard.*;

import org.junit.*;

import java.util.HashMap;

import static hotciv.framework.Player.RED;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static hotciv.framework.GameConstants.*;

public class TestSemiCiv {

    private Game game;
    ActionStrategy Gamma;
    HashMap<Position,City> Cities;
    HashMap<Position,Tile> World;

    @Before
    public void setUp() {
        game = new GameImpl(new SemiFactory());
        Gamma = ((GameImpl) game).returnActionStrategy();
        Cities = ((GameImpl) game).returnCities();
        World = ((GameImpl) game).returnWorld();
    }

    //DELTA CIV TESTS
    @Test
    public void testRedOwnCityAtR8C12(){
        //tiles and ownership should be initialized in this iteration's constructor for the game class
        Position p1 = new Position(8,12);
        assertThat(((GameImpl) game).cityExists(p1), is(true));
        assertThat(game.getCityAt(p1).getOwner(), is(Player.RED));
    }

    @Test
    public void testBlueOwnCityAtR4C5(){
        //tiles and ownership should be initialized in this iteration's constructor for the game class
        Position p1 = new Position(4,5);
        assertThat(((GameImpl) game).cityExists(p1), is(true));
        assertThat(game.getCityAt(p1).getOwner(), is(Player.BLUE));
    }


    //Since it would be tedious to test every single tile in the 2D array, we will test a handful of points
    @Test
    public void testOceanInRow0(){
        Position p1 = new Position(0,15);
        assertThat(game.getTileAt(p1).getTypeString(), is(OCEANS));
    }

    @Test
    public void testPlainsInRow2(){
        Position p1 = new Position(2,14);
        assertThat(game.getTileAt(p1).getTypeString(), is(PLAINS));
    }

    @Test
    public void testMountainInRow7(){
        Position p1 = new Position(7,13);
        assertThat(game.getTileAt(p1).getTypeString(), is(MOUNTAINS));
    }

    @Test
    public void testForestInRow8(){
        Position p1 = new Position(8,13);
        assertThat(game.getTileAt(p1).getTypeString(), is(FOREST));
    }

    @Test
    public void testHillsInRow14(){
        Position p1 = new Position(14,5);
        assertThat(game.getTileAt(p1).getTypeString(), is(HILLS));
    }


    /**
     * GAMMA CIV TESTING
     */

    @Test
    public void LegionDoesNothing() {
        Unit legion = new UnitLegion(Player.RED);
        Position p = new Position(0,0);
        ((TileImpl) World.get(p)).setUnit(legion);
        Gamma.performUnitActionAt(World, Cities,new Position(0,0));
        assertEquals(LEGION, ((TileImpl) World.get(p)).getUnit().getTypeString());
        assertEquals(2, legion.getDefensiveStrength());
        assertEquals(4, legion.getAttackingStrength());
        assertEquals(1, legion.getMoveCount());

    }

    @Test
    public void SettlerBuildsCityOwnedByRed() {
        Unit settler = new UnitSettler(Player.RED);
        Position p = new Position(0,0);
        ((TileImpl) World.get(p)).setUnit(settler);
        assertEquals(SETTLER, ((TileImpl) World.get(p)).getUnit().getTypeString());
        assertThat(Cities.containsKey(p),is(false));
        Gamma.performUnitActionAt(World, Cities, p);
        assertThat(((TileImpl) World.get(p)).getUnit(), is(nullValue()));
        assertEquals(RED, Cities.get(p).getOwner());
        assertEquals(ARCHER, ((CityImpl) Cities.get(p)).getUnitFocus());
        assertEquals(productionFocus, Cities.get(p).getWorkforceFocus());
    }

    @Test
    public void ArcherFortifiesAndCannotMove() {
        Unit archer = new UnitArcher(Player.RED);
        Position p = new Position(0,0);
        ((TileImpl) World.get(p)).setUnit(archer);
        assertEquals(ARCHER, ((TileImpl) World.get(p)).getUnit().getTypeString());
        assertEquals(3, archer.getDefensiveStrength());
        assertEquals(2, archer.getAttackingStrength());
        assertEquals(1, archer.getMoveCount());
        Gamma.performUnitActionAt(World, Cities,p);
        assertEquals(6, archer.getDefensiveStrength());
        assertEquals(2, archer.getAttackingStrength());
        assertEquals(0, archer.getMoveCount());
    }

    @Test
    public void ArcherRemovesFortifyAndCanMove() {
        Unit archer = new UnitArcher(Player.RED);
        Position p = new Position(0,0);
        ((TileImpl) World.get(p)).setUnit(archer);
        Gamma.performUnitActionAt(World, Cities, p);
        Gamma.performUnitActionAt(World, Cities, p);
        assertEquals(3, archer.getDefensiveStrength());
        assertEquals(2, archer.getAttackingStrength());
        assertEquals(1, archer.getMoveCount());
    }


    /**
     * EPSILON CIV TESTING - ATTACK AND WINNING
     */


    /**
     * BETA CIV TESTING - AGING
     */

    @Test
    public void worldAging4000BC_100BC(){
        int year = -4000;
        assertThat(game, is(notNullValue()));
        for (int i = 0; i < 39; i++){
            assertThat(game.getAge(), is(year));
            game.endOfTurn();
            game.endOfTurn();
            year += 100;
        }

    }

    @Test
    public void worldAgingAroundBirthOfChrist(){
        assertThat(game, is(notNullValue()));
        for (int i = 0; i < 38; i++){
            game.endOfTurn();
            game.endOfTurn();
            game.getAge();
        }
        assertThat(game.getAge(), is(-100));
        assertThat(game.getAge(), is(-1));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(1));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(),is(50));
    }

    @Test
    public void worldAging50AD_1750(){
        assertThat(game, is(notNullValue()));
        for (int i = 0; i < 41; i++){
            game.endOfTurn();
            game.endOfTurn();
            game.getAge();
        }
        int year = 50;
        for (int i = 0; i < 34; i++){
            assertThat(game.getAge(),is(year));
            game.endOfTurn();
            game.endOfTurn();
            year += 50;
        }
    }

    @Test
    public void worldAging1750_1900(){
        assertThat(game, is(notNullValue()));
        for (int i = 0; i < 75; i++){
            game.endOfTurn();
            game.endOfTurn();
            game.getAge();
        }
        int year = 1750;
        for (int i = 0; i < 6; i++){
            assertThat(game.getAge(),is(year));
            game.endOfTurn();
            game.endOfTurn();
            year += 25;
        }
    }

    @Test
    public void worldAging1900_1970(){
        assertThat(game, is(notNullValue()));
        for (int i = 0; i < 81; i++){
            game.endOfTurn();
            game.endOfTurn();
            game.getAge();
        }
        int year = 1900;
        for (int i = 0; i < 14; i++){
            assertThat(game.getAge(),is(year));
            game.endOfTurn();
            game.endOfTurn();
            year += 5;
        }
    }

    @Test
    public void worldAgingAfter1970(){
        assertThat(game, is(notNullValue()));
        for (int i = 0; i < 95; i++){
            game.endOfTurn();
            game.endOfTurn();
            game.getAge();
        }
        int year = 1970;
        for (int i = 0; i < 25; i++){
            assertThat(game.getAge(),is(year));
            game.endOfTurn();
            game.endOfTurn();
            year += 1;
        }
    }

}
