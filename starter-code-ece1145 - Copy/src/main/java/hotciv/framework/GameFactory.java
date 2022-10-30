package hotciv.framework;

public interface GameFactory {

    //worldBuild strategy
    public worldBuild createWorldBuild();

    //Action strategy
    public ActionStrategy createActionStrategy();

    //attack strategy
    public AttackStrategy createAttackStrategy();

    //winner strategy
    public WinnerStrategy createWinnerStrategy();
}
