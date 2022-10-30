package hotciv.framework;

public interface AttackStrategy {
    public boolean attack(Unit attacker, Unit defender, int attackerAdjacentUnits, int attackerTerrainFactor,
                          int defenderAdjacentUnits, int defenderTerrainFactor);
}