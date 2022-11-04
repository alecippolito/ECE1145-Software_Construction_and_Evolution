package hotciv.alternative;

import hotciv.framework.*;

import java.util.ArrayList;
import java.util.HashMap;

public class WinnerStrategyAlpha implements WinnerStrategy {

    public Player getWinner(int age, ArrayList<Tile> cityTiles, HashMap<Player, Integer> winHashMap, int roundNumber, HashMap<Position, City> Cities){
        return null;
    }
    public Player getWinner(int age, ArrayList<Tile> cityTiles, HashMap<Player, Integer> winHashMap, int roundNumber){
        return null;
    }
    public Player getWinner(int age, HashMap<Position, City> Cities){
        if (age == -3000) {
            return Player.RED;
        }

        return null;
    }
    public void countAttack(HashMap<Player,Integer> attackCounts, Player player){

    }

}
