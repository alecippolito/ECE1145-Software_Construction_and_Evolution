package hotciv.alternative;

import hotciv.framework.*;

public class AlphaFactory implements GameFactory {

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
        return new WinnerStrategyAlpha();
    }
    public AgingStrategy createAgingStrategy() {
        return new AgingStrategyAlpha();
    }
}
