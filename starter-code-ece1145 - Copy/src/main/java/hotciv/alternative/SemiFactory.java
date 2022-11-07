package hotciv.alternative;

import hotciv.framework.*;

public class SemiFactory implements GameFactory {
    public worldBuild createWorldBuild(){
        return new DeltaBuild();
    }

    //Action strategy
    public ActionStrategy createActionStrategy(){
        return new ActionStrategyGamma();
    }

    //attack strategy
    public AttackStrategy createAttackStrategy(){
        return new AttackStrategyEpsilon();
    }

    //winner strategy
    public WinnerStrategy createWinnerStrategy(){
        return new WinnerStrategyEpsilon();
    }

    //aging strategy
    public AgingStrategy createAgingStrategy(){
        return new AgingStrategyBeta();
    }
}
