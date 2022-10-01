package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.Player.*;
import java.util.*;

/**
 * TEST DELTA CIV
 */
public class TestDeltaCiv {

    private Game game;

    @Before
    public void setUp(){
        game = new GameImpl(new DeltaBuild());
    }


    @Test
    public void testRedOwnCityAtR8C12(){
        //tiles and ownership should be initialized in this iteration's constructor for the game class
        Position p1 = new Position(8,12);
        assertThat(((TileImpl) game.getTileAt(p1)).hasCity(), is(true));
        assertThat(((TileImpl) game.getTileAt(p1)).returnCity().getOwner(), is(Player.RED));
    }

    @Test
    public void testBlueOwnCityAtR4C5(){
        //tiles and ownership should be initialized in this iteration's constructor for the game class
        Position p1 = new Position(4,5);
        assertThat(((TileImpl) game.getTileAt(p1)).hasCity(), is(true));
        assertThat(((TileImpl) game.getTileAt(p1)).returnCity().getOwner(), is(Player.BLUE));
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
}
