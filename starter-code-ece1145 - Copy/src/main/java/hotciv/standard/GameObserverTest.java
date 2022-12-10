package hotciv.standard;

import hotciv.alternative.SemiFactory;
import hotciv.framework.*;


public class GameObserverTest implements GameObserver {

    Game game;

    Position tempPosition;
    Player tempPlayer;
    int tempAge;

    GameObserverTest(Game game){
        this.game = game;
    }
    public void worldChangedAt(Position pos){
        tempPosition = pos;
    }

    public void turnEnds(Player nextPlayer, int age){
        tempPlayer = nextPlayer;
        tempAge = age;
    }

    public void tileFocusChangedAt(Position position){
        tempPosition = position;
    }

    //Accessor functions
    public int getTempAge(){
        return tempAge;
    }
    public Player getTempPlayer(){
        return tempPlayer;
    }
    public Position getTempPosition(){
        return tempPosition;
    }

    //function to test stuff
    public void manuallyUpdatePosition(Position p){
        tempPosition = p;
    }

}
