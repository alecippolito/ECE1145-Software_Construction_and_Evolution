package hotciv.alternative;

import hotciv.framework.*;

public class DeltaFactory implements GameFactory {
    public worldBuild createWorldBuild() {
        return new DeltaBuild();
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
        return null;
    }
}
