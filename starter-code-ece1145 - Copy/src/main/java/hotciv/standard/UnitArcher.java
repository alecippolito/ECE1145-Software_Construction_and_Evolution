package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class UnitArcher extends UnitImpl {

    public UnitArcher(Player owner) {
        super(GameConstants.ARCHER, owner, 1, 3, 2);

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (o.getClass() != UnitArcher.class) { return false; }
        return super.equals(o);
    }
}