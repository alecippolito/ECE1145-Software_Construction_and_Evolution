package hotciv.alternative;

import java.util.ArrayList;
import java.util.HashMap;
import hotciv.framework.Player;
import hotciv.framework.Tile;
import hotciv.framework.WinnerStrategy;
import hotciv.standard.*;

public class WinnerStrategyEpsilon implements WinnerStrategy {

    private int i = 0;
    public Player getWinner(int age, ArrayList<Tile> cityTiles, HashMap<Player, Integer> winHashMap, int roundNumber) {
        Player winner = null;
        for(Player key : winHashMap.keySet()) {
            if(winHashMap.get(key) >= 3){
                winner = key;
            }
        }
        return winner;

    }

    public void countAttack(HashMap<Player, Integer> attackCounts, Player player) {
        attackCounts.put(player, i+1);
    }
}
