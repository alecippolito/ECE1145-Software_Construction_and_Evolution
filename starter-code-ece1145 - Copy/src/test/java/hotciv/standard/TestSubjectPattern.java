package hotciv.standard;

import hotciv.alternative.AlphaFactory;
import hotciv.alternative.SemiFactory;
import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.standard.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;

public class TestSubjectPattern {

    private Game game;

    private GameObserver demoObserver;

    @Before
    public void setUp() {

        //set up a normal game, in this case alphaCiv
        game = new GameImpl(new AlphaFactory());

        //Add an observer
        demoObserver = new GameObserverTest(game);
        game.addObserver(demoObserver);
    }

    @Test
    public void testWorldChangedAt(){

        Position p = new Position(5,5);

        //setUnitAt
        Unit u = new UnitArcher(Player.BLUE);
        ((GameImpl) game).setUnitAt(p,u);
        assertThat(((GameObserverTest) demoObserver).getTempPosition(),is(p));

        //removeUnitAt
        ((GameObserverTest) demoObserver).manuallyUpdatePosition(new Position(1,1));
        assertThat(((GameObserverTest) demoObserver).getTempPosition(),is(new Position(1,1)));
        ((GameImpl) game).removeUnitAt(p);
        assertThat(((GameObserverTest) demoObserver).getTempPosition(),is(p));

        //changeWorkForceFocusInCityAt
        p = new Position(1,1);
        game.changeWorkForceFocusInCityAt(p,GameConstants.foodFocus);
        assertThat(((GameObserverTest) demoObserver).getTempPosition(),is(p));

        //changeProductionInCityAt
        p = new Position(4,1);
        game.changeProductionInCityAt(p,GameConstants.ARCHER);
        assertThat(((GameObserverTest) demoObserver).getTempPosition(),is(p));

        //performUnitActionAt
        p = new Position(5,5);
        game.performUnitActionAt(p);
        assertThat(((GameObserverTest) demoObserver).getTempPosition(),is(p));

        //AddProductionEndOfRound
        ((GameImpl) game).AddProductionEndOfRound();
        assertThat(((GameObserverTest) demoObserver).getTempPosition(),is(new Position(4,1)));

        //createNewUnitFromCity
        p = new Position(1,1);
        ((GameImpl) game).createNewUnitFromCity(p);
        assertThat(((GameObserverTest) demoObserver).getTempPosition(),is(p));

    }

    @Test
    public void testTurnEnds(){
        //endOfTurn
        game.endOfTurn();
        assertThat(((GameObserverTest) demoObserver).getTempPlayer(),is(game.getPlayerInTurn()));
        assertThat(((GameObserverTest) demoObserver).getTempAge(),is(game.getAge()));
        game.endOfTurn();
        assertThat(((GameObserverTest) demoObserver).getTempPlayer(),is(game.getPlayerInTurn()));
        assertThat(((GameObserverTest) demoObserver).getTempAge(),is(game.getAge()));

    }

    @Test
    public void testTileFocusChangedAt(){
        //not used at the moment
    }



}
