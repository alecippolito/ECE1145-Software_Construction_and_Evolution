package hotciv.alternative;

import hotciv.framework.*;

public class BetaFactory implements GameFactory {

    public worldBuild createWorldBuild() {
        return new BetaBuild();
    }
    public ActionStrategy createActionStrategy() {
        return null;
    }
    public AttackStrategy createAttackStrategy() {
        return null;
    }
    public WinnerStrategy createWinnerStrategy() {
        return null;
    }
    public AgingStrategy createAgingStrategy() {
        return new AgingStrategyBeta();
    }
}
