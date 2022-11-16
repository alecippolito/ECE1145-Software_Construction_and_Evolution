package hotciv.alternative;

import hotciv.framework.*;

import java.util.ArrayList;
import java.util.HashMap;

public class WinnerStrategyAlpha implements WinnerStrategy {

    public Player getWinner(int age, HashMap<Position, City> Cities, HashMap<Player, Integer> winHashMap, int roundNumber){
        if (age == -3000) {
            return Player.RED;
        }
        return null;
    }
    public void countAttack(HashMap<Player,Integer> attackCounts, Player player){

    }

}
