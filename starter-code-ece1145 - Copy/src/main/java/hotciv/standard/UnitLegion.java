package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class UnitLegion extends UnitImpl {

    public UnitLegion(Player owner) {
        super(GameConstants.LEGION, owner, 1, 2, 4);

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (o.getClass() != UnitArcher.class) { return false; }
        return super.equals(o);
    }
}