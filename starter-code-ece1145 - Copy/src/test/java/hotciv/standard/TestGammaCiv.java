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
    Tile[][] World;
    ActionStrategyGamma Gamma;
    Position p;

    /**
     * Fixture for gammaciv testing.
     */
    @Before
    public void setUp() {
        World = new Tile[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        for(int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for( int j = 0; j < GameConstants.WORLDSIZE; j++) {
                World[i][j] = new TileImpl ();
            }
        }
        Gamma = new ActionStrategyGamma();
        p = new Position(0,0);
    }

    @Test
    public void LegionDoesNothing() {
        Unit legion = new UnitLegion(Player.RED);
        World[0][0].setUnit(legion);
        Gamma.performUnitActionAt(World, new Position(0,0));
        assertEquals(LEGION, World[0][0].getUnit().getTypeString());
        assertEquals(2, legion.getDefensiveStrength());
        assertEquals(4, legion.getAttackingStrength());
        assertEquals(1, legion.getMoveCount());

    }

    @Test
    public void SettlerBuildsCityOwnedByRed() {
        Unit settler = new UnitSettler(Player.RED);
        World[0][0].setUnit(settler);
        assertEquals(SETTLER, World[0][0].getUnit().getTypeString());
        assertThat(World[0][0].returnCity(), is(nullValue()));
        Gamma.performUnitActionAt(World, p);
        assertThat(World[0][0].getUnit(), is(nullValue()));
        assertEquals(RED, World[0][0].returnCity().getOwner());
        assertEquals(ARCHER, World[0][0].returnCity().getUnitFocus());
        assertEquals(productionFocus, World[0][0].returnCity().getWorkforceFocus());
    }

    @Test
    public void ArcherFortifiesAndCannotMove() {
        Unit archer = new UnitArcher(Player.RED);
        World[0][0].setUnit(archer);
        assertEquals(ARCHER, World[0][0].getUnit().getTypeString());
        assertEquals(3, archer.getDefensiveStrength());
        assertEquals(2, archer.getAttackingStrength());
        assertEquals(1, archer.getMoveCount());
        Gamma.performUnitActionAt(World, p);
        assertEquals(6, archer.getDefensiveStrength());
        assertEquals(2, archer.getAttackingStrength());
        assertEquals(0, archer.getMoveCount());
    }

    @Test
    public void ArcherRemovesFortifyAndCanMove() {
        Unit archer = new UnitArcher(Player.RED);
        World[0][0].setUnit(archer);
        Gamma.performUnitActionAt(World, p);
        Gamma.performUnitActionAt(World, p);
        assertEquals(3, archer.getDefensiveStrength());
        assertEquals(2, archer.getAttackingStrength());
        assertEquals(1, archer.getMoveCount());
    }

}
