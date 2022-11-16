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

        game = new GameImpl(new ZetaFactory());
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
    public void winnerShouldBeBlueAfter20Rounds(){
        // have game in round 25
        int round = 0;
        for (int i = 0; i < 25; i++) {
            game.endOfTurn();
            game.endOfTurn();
            round ++;
        }
        // have blue get 3 wins
        hm.put(Player.BLUE, 3);
        // get winner
        Player winner = winnerStrategy.getWinner(game.getAge(), game.returnCities(), hm, 23);
        // compare winner
        assertEquals(winner,Player.BLUE);
    }

    @Test
    public void winnerShouldBeRedAfter20Round(){
        // have game in round 25
        int round = 0;
        for (int i = 0; i < 25; i++) {
            game.endOfTurn();
            game.endOfTurn();
            round ++;
        }
        // have red get 3 wins
        hm.put(Player.RED, 3);
        // get winner
        Player winner = winnerStrategy.getWinner(game.getAge(), game.returnCities(), hm, 23);
        // compare winner
        assertEquals(winner,Player.RED);
    }
}
