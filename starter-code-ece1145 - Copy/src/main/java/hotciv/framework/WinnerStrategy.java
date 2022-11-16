package hotciv.framework;

import java.util.*;

public interface WinnerStrategy {
    public Player getWinner(int age, HashMap<Position, City> Cities, HashMap<Player, Integer> winHashMap, int roundNumber);
    public void countAttack(HashMap<Player,Integer> attackCounts, Player player);
}
