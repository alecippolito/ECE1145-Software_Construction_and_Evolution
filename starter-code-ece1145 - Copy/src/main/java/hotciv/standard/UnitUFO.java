package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;
import java.util.Objects;


public class UnitUFO extends UnitImpl {

    public UnitUFO(Player owner) {
        super(GameConstants.UFO, owner);

    }

    @Override
    public void specialAction(HashMap<Position,Tile> w, HashMap<Position,City> c, Position p) {
        if (c != null)
            abductPopulation(p, c);
        else if (Objects.equals(w.get(p).getTypeString(), GameConstants.FOREST))
            abductForest(w, p);

    }

    private void abductPopulation(Position p, HashMap<Position,City> c) {
        c.get(p).changeSize(false);
        if (c.get(p).getSize() == 0)
            c.remove(p);
    }

    private void abductForest(HashMap<Position,Tile> w, Position p) {
        w.remove(p);
        w.put(p, new TileImpl(GameConstants.PLAINS));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (o.getClass() != UnitArcher.class) { return false; }
        return super.equals(o);
    }
}