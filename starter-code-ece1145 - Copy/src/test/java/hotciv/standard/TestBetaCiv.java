package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.Player.*;
import java.util.*;

public class TestBetaCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new AlphaBuild());
    }

    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void winnerIsFirstToConquerAllCities() {
        // implement
    }

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









