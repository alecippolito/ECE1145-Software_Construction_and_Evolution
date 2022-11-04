package hotciv.alternative;

import hotciv.framework.*;

public class ZetaFactory implements GameFactory {
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
        return new WinnerStrategyZeta();
    }

    public AgingStrategy createAgingStrategy() {
        return new AgingStrategyAlpha();
    }
}
