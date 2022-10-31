package hotciv.alternative;

import hotciv.framework.*;

import java.util.ArrayList;
import java.util.HashMap;

public class WinnerStrategyZeta implements WinnerStrategy {
    private int i = 0;
    @Override
    public Player getWinner(int age, ArrayList<Tile> cityTiles, HashMap<Player, Integer> winHashMap, int roundNumber, HashMap<Position, City> Cities) {
        if(roundNumber <= 20){
            // beta strategy
            return getWinner(age, Cities);
        } else{
            // epsilon strategy
            return getWinner(age,cityTiles,winHashMap, roundNumber);
        }

    }

    @Override
    public Player getWinner(int age, ArrayList<Tile> cityTiles, HashMap<Player, Integer> winHashMap, int roundNumber) {
        Player winner = null;
        for(Player key : winHashMap.keySet()) {
            if(winHashMap.get(key) >= 3){
                winner = key;
            }else{}
        }
        return winner;
    }

    @Override
    public Player getWinner(int age, HashMap<Position, City> Cities) {
        Player winner = null;
        for (City city : Cities.values())
        {
            if(winner==null)
            {
                winner = city.getOwner();
            }
            else if(winner == city.getOwner())
            {
                continue;
            }
            else
            {
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
