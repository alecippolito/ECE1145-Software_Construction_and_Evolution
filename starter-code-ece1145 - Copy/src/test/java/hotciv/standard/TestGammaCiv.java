package hotciv.standard;

import hotciv.alternative.ActionStrategyGamma;
import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.Player.*;
import java.util.*;


/** Testing for Gamma Civ
 */
public class TestGammaCiv {
    HashMap<Position,Tile> World;
    ActionStrategyGamma Gamma;
    Position p;

    //added by Jack to make it work: a hash map for the cities
    HashMap<Position,City> Cities = new HashMap<>();

    /**
     * Fixture for gammaciv testing.
     */
    @Before
    public void setUp() {
        World = new HashMap<>();
        for(int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for( int j = 0; j < GameConstants.WORLDSIZE; j++) {
                World.put(new Position(i,j), new TileImpl(PLAINS));
            }
        }
        Gamma = new ActionStrategyGamma();
        p = new Position(0,0);
    }

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

}
