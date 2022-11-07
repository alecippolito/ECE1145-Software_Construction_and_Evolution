package hotciv.alternative;

import hotciv.framework.*;

public class ThetaFactory implements GameFactory {
    public worldBuild createWorldBuild() {
        return new AlphaBuild();
    }
    public ActionStrategy createActionStrategy() {
        return new ActionStrategyTheta();
    }
    public AttackStrategy createAttackStrategy() {
        return null;
    }
    public WinnerStrategy createWinnerStrategy() {
        return null;
    }

    public AgingStrategy createAgingStrategy() {
        return null;
    }
}