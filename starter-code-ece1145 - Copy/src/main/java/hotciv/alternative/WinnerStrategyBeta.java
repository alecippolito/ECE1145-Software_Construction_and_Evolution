package hotciv.alternative;

import java.util.ArrayList;
import java.util.HashMap;

import hotciv.framework.*;

public class WinnerStrategyBeta implements WinnerStrategy {
    private int i = 0;
    @Override
    public Player getWinner(int age, HashMap<Position, City> Cities, HashMap<Player, Integer> winHashMap, int roundNumber) {
        Player winner = null;
        for (City city : Cities.values()){
            if(winner==null){
                winner = city.getOwner();
            }
            else if(winner == city.getOwner()){
                continue;
            }
            else{
                return null;
            }
        }
        return winner;
    }

    @Override
    public void countAttack(HashMap<Player, Integer> attackCounts, Player player) {
        attackCounts.put(player, i+1);
    }
}
