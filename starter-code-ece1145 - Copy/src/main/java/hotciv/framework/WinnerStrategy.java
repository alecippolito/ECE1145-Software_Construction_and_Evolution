package hotciv.framework;

import java.util.*;

public interface WinnerStrategy {
    public Player getWinner(int age, ArrayList<Tile> cityTiles, HashMap<Player, Integer> winHashMap, int roundNumber, HashMap<Position, City> Cities);
    public Player getWinner(int age, ArrayList<Tile> cityTiles, HashMap<Player, Integer> winHashMap, int roundNumber);
    public Player getWinner(int age, HashMap<Position, City> Cities);
    public void countAttack(HashMap<Player,Integer> attackCounts, Player player);
}
