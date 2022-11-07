package hotciv.alternative;

import hotciv.framework.*;

public class GammaFactory implements GameFactory {
    public worldBuild createWorldBuild() {
        return new AlphaBuild();
    }
    public ActionStrategy createActionStrategy() {
        return new ActionStrategyGamma();
    }
    public AttackStrategy createAttackStrategy() {
        return null;
    }
    public WinnerStrategy createWinnerStrategy() {
        return new WinnerStrategyAlpha();
    }

    public AgingStrategy createAgingStrategy() {
        return new AgingStrategyAlpha();
    }
}
