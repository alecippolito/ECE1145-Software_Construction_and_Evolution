package hotciv.standard;

import hotciv.alternative.*;
import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.Player.*;
import java.util.*;

public class TestZetaCiv {
    private Game game;
    HashMap<Position, Tile> World;
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

        game = new GameImpl(new BetaFactory());
        World = new HashMap<>();
        for(int i = 0; i < GameConstants.WORLDSIZE; i++) {
            for( int j = 0; j < GameConstants.WORLDSIZE; j++) {
                World.put(new Position(i,j), new TileImpl(PLAINS));
            }
        }
        winnerStrategy = new WinnerStrategyZeta();
        p = new Position(0,0);
    }

    @Test
    public void winnerShouldBeNull(){
        // no actions so no winner
        assertNull(game.getWinner());
    }

    @Test
    public void winnerShouldBeBlue(){

    }

    @Test
    public void winnerShouldBeRed(){

    }
}
