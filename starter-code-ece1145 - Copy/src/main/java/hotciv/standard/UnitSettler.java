package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;

public class UnitSettler extends UnitImpl {

    public UnitSettler(Player owner) {
        super(GameConstants.SETTLER, owner, 1, 3, 0);

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (o.getClass() != UnitArcher.class) { return false; }
        return super.equals(o);
    }
}