package hotciv.standard;

import hotciv.alternative.*;
import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.Player.*;
import java.util.*;


/** Testing for Epsilon Civ
 */

public class TestEpsilonCiv {

    private Game game;
    HashMap<Position,Tile> World;
    AttackStrategyEpsilon Epsilon;

    WinnerStrategy winnerStrategy;

    AttackStrategy attackStrategy;
    Position p;

    HashMap<Player, Integer> hm = new HashMap<Player, Integer>();

    //added by Jack to make it work: a hash map for the cities
    HashMap<Position,City> Cities = new HashMap<>();

    /**
     * Fixture for epsilon civ testing.
     */
    @Before
    public void setUp() {

        game = new GameImpl(new EpsilonFactory());
        World = new HashMap<>();
        for(int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for( int j = 0; j < GameConstants.WORLDSIZE; j++) {
                World.put(new Position(i,j), new TileImpl(PLAINS));
            }
        }
        attackStrategy = new AttackStrategyEpsilon();
        winnerStrategy = new WinnerStrategyEpsilon();
        p = new Position(0,0);
    }

    @Test
    public void testWinnerIsNull(){
        assertNull(game.getWinner());
    }


    @Test
    public void testWinnerIsRed(){
        hm.put(Player.RED, 3);
        assertEquals(winnerStrategy.getWinner(0, null, hm, 0), Player.RED );
    }

    @Test
    public void testWinnerIsBlue(){
        hm.put(Player.BLUE, 3);
        assertEquals(winnerStrategy.getWinner(0, null, hm, 0), Player.BLUE );
    }

    @Test
    public void testArcherAttackSettler() {
        assertFalse(attackStrategy.attack(new UnitArcher(Player.RED), new UnitSettler(Player.BLUE), 0, 1, 0, 1));
    }

    @Test
    public void testLegionAttackSettler() {
        assertTrue(attackStrategy.attack(new UnitLegion(Player.RED), new UnitSettler(Player.BLUE), 0, 1, 0, 1));
    }

    @Test
    public void testArcherAttackLegion() {
        assertFalse(attackStrategy.attack(new UnitArcher(Player.RED), new UnitLegion(Player.BLUE), 0, 1, 0, 1));
    }

    @Test
    public void testSettlerAttackLegion() {
        assertFalse(attackStrategy.attack(new UnitSettler(Player.RED), new UnitLegion(Player.BLUE), 0, 1, 0, 1));
    }

    @Test
    public void testLegionAttackArcher() {
        assertTrue(attackStrategy.attack(new UnitLegion(Player.RED), new UnitArcher(Player.BLUE), 0, 1, 0, 1));
    }

    @Test
    public void testSettlerAttackArcher() {
        assertFalse(attackStrategy.attack(new UnitSettler(Player.RED), new UnitArcher(Player.BLUE), 0, 1, 0, 1));
    }

    @Test
    public void testArcherOnForestAttackSettlerOnPlain() {
        assertTrue(attackStrategy.attack(new UnitArcher(Player.RED), new UnitSettler(Player.BLUE), 0, 2, 0, 1));
    }

    @Test
    public void testArcherOnForestAttackSettlerOnPlainWithThreeAdjacentUnits() {
        assertFalse(attackStrategy.attack(new UnitArcher(Player.RED), new UnitSettler(Player.BLUE), 0, 2, 3, 1));
    }

    @Test
    public void testLegionOnCityWithNoAdjacentUnitsAttackLegionOnPlainWithTwoAdjacentUnits() {
        assertTrue(attackStrategy.attack(new UnitLegion(Player.RED), new UnitArcher(Player.BLUE), 0, 3, 2, 1));
    }

    @Test
    public void testArcherOnPlainsAttackSettlerOnHills() {
        Position p1 = new Position(0,0);
        Position p2 = new Position(0,1);

        ((GameImpl) game).setTileTypeFromGame(p1, PLAINS);
        ((GameImpl) game).setTileTypeFromGame(p2, HILLS);

        ((GameImpl) game).setUnitAt(p1, new UnitArcher(Player.RED));
        ((GameImpl) game).setUnitAt(p2, new UnitSettler(Player.BLUE));

        game.moveUnit(p1, p2);
        assertNull(game.getUnitAt(p1));
        assertThat(game.getUnitAt(p2).getTypeString(), is(SETTLER));
    }

    @Test public void shouldGiveCorrectTerrainFactors() {
        Position p1 = new Position(0,1);
        Position p2 = new Position(1,0);
        Position p3 = new Position(0,0);
        Position p4 = new Position(1,1);

        ((GameImpl) game).setTileTypeFromGame(p1, PLAINS);
        ((GameImpl) game).setTileTypeFromGame(p2, HILLS);
        ((GameImpl) game).setTileTypeFromGame(p3, FOREST);
        // plains have multiplier 1
        assertThat(((GameImpl) game).getTerrainFactor(p1), is(1));
        // hills have multiplier 2
        assertThat(((GameImpl) game).getTerrainFactor(p2), is(2));
        // forest have multiplier 2
        assertThat(((GameImpl) game).getTerrainFactor(p3), is(2));
        // cities have multiplier 3
        assertThat(((GameImpl) game).getTerrainFactor(p4), is(3));
    }



}
