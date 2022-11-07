package hotciv.standard;

import hotciv.alternative.ActionStrategyTheta;
import hotciv.alternative.ThetaFactory;
import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.Player.*;
import java.util.*;


/** Testing for Theta Civ
 */
public class TestThetaCiv {

    private Game game;
    HashMap<Position,Tile> World;
    ActionStrategyTheta Theta;
    Position p;

    //added by Jack to make it work: a hash map for the cities
    HashMap<Position,City> Cities = new HashMap<>();

    /**
     * Fixture for thetaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl(new ThetaFactory());
        World = new HashMap<>();
        for(int i = 0; i < WORLDSIZE; i++) {
            for( int j = 0; j < WORLDSIZE; j++) {
                World.put(new Position(i,j), new TileImpl(FOREST));
            }
        }
        Theta = new ActionStrategyTheta();
        p = new Position(0,0);
    }

    @Test
    public void initializeUFO() {
        Unit ufo = new UnitUFO(RED);
        Position p = new Position(0,0);
        ((TileImpl) World.get(p)).setUnit(ufo);
        assertEquals(UFO, ((TileImpl) World.get(p)).getUnit().getTypeString());
        assertEquals(8, ufo.getDefensiveStrength());
        assertEquals(1, ufo.getAttackingStrength());
        assertEquals(2, ufo.getMoveCount());

    }

    @Test
    public void actionCityUFO() {
        Unit ufo = new UnitUFO(Player.RED);
        Position p = new Position(1,1);
        ((TileImpl) World.get(p)).setUnit(ufo);
        assertEquals(UFO, ((TileImpl) World.get(p)).getUnit().getTypeString());
        Cities.put(p,new CityImpl(BLUE, GameConstants.ARCHER, GameConstants.productionFocus));
        Theta.performUnitActionAt(World, Cities, p);
        assertNull(Cities.get(p));
    }

    @Test
    public void actionForestUFO() {
        Unit ufo = new UnitUFO(Player.RED);
        Position p = new Position(1,1);
        ((TileImpl) World.get(p)).setUnit(ufo);
        assertEquals(UFO, ((TileImpl) World.get(p)).getUnit().getTypeString());
        assertNotNull(World);
        assertNotNull(Cities);
        Theta.performUnitActionAt(World, Cities, p);
        assertEquals(PLAINS, World.get(p).getTypeString());
    }

    @Test
    public void ufoMovesOverMountain() {
        Unit ufo = new UnitUFO(Player.RED);
        Position p1 = new Position(3, 2);
        Position p2 = new Position(2, 2);
        ((GameImpl) game).setUnitAt(p1, ufo);
        ((GameImpl) game).setTileTypeFromGame(p2, MOUNTAINS);
        assertNotNull(game.getUnitAt(p1));
        assertThat(game.moveUnit(p1, p2), is(true));
    }

    @Test
    public void ufoMovesOverOCEAN() {
        Unit ufo = new UnitUFO(Player.RED);
        Position p1 = new Position(3, 2);
        Position p2 = new Position(2, 2);
        ((GameImpl) game).setUnitAt(p1, ufo);
        ((GameImpl) game).setTileTypeFromGame(p2, OCEANS);
        assertNotNull(game.getUnitAt(p1));
        assertThat(game.moveUnit(p1, p2), is(true));
    }

    @Test
    public void ufoMovesTwoSpaces() {
        Unit ufo = new UnitUFO(Player.RED);
        Position p1 = new Position(3, 2);
        Position p2 = new Position(1, 2);
        ((TileImpl) World.get(p1)).setUnit(ufo);
        ((GameImpl) game).setUnitAt(p1, ufo);
        assertEquals(UFO, ((TileImpl) World.get(p1)).getUnit().getTypeString());
        assertNotNull(game.getUnitAt(p1));
        assertThat(game.moveUnit(p1, p2), is(true));
    }

}