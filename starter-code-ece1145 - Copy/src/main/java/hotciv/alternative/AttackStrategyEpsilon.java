package hotciv.alternative;

import hotciv.framework.AttackStrategy;
import hotciv.framework.Unit;

public class AttackStrategyEpsilon implements AttackStrategy {
    int attack, defense;

    public boolean attack(Unit attacker, Unit defender, int attackerAdjacentUnits, int attackerTerrainFactor,
                          int defenderAdjacentUnits, int defenderTerrainFactor) {

        attack = ((attacker.getAttackingStrength() + attackerAdjacentUnits) * attackerTerrainFactor);
        defense = ((defender.getDefensiveStrength() + defenderAdjacentUnits) * defenderTerrainFactor);

        if(attack > defense) return true;
        else return false;
    }

}
