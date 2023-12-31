package hotciv.alternative;

import hotciv.framework.*;

public class EpsilonFactory implements GameFactory {

    public worldBuild createWorldBuild() {
        return new AlphaBuild();
    }
    public ActionStrategy createActionStrategy() {
        return null;
    }
    public AttackStrategy createAttackStrategy() {
        return new AttackStrategyEpsilon();
    }
    public WinnerStrategy createWinnerStrategy() {
        return new WinnerStrategyEpsilon();
    }

    public AgingStrategy createAgingStrategy() {
        return new AgingStrategyAlpha();
    }
}
