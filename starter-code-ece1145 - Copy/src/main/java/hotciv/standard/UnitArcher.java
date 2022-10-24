package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class UnitArcher extends UnitImpl {

    boolean fortify;
    boolean checkMove;
    public UnitArcher(Player owner) {
        super(GameConstants.ARCHER, owner);
    }

    @Override
    public void action() {
        if (fortify) {
            removeFortify();
        }
        else {
            setFortify();
        }
    }

    private void setFortify() {
        unitStat.moveCount = 0;
        unitStat.defense = unitStat.defense * 2;
        fortify = true;
    }

    private void removeFortify() {
        if (!checkMove)
            unitStat.moveCount = 1;
        unitStat.defense = unitStat.defense / 2;
        fortify = false;
    }

    public void moved() {
        super.moved();
        checkMove = true;
    }

    public void resetMove() {
        super.resetMove();
        checkMove = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (o.getClass() != UnitArcher.class) { return false; }
        return super.equals(o);
    }
}
