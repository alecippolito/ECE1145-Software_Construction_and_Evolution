package hotciv.alternative;

import hotciv.framework.*;

public class BetaFactory implements GameFactory {

    public worldBuild createWorldBuild() {
        return new AlphaBuild();
    }
    public ActionStrategy createActionStrategy() {
        return null;
    }
    public AttackStrategy createAttackStrategy() {
        return null;
    }
    public WinnerStrategy createWinnerStrategy() {
        return new WinnerStrategyBeta();
    }
    public AgingStrategy createAgingStrategy() {
        return new AgingStrategyBeta();
    }
}
