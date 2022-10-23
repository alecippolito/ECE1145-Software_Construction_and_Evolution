package hotciv.alternative;

import hotciv.framework.*;
import hotciv.standard.*;
import java.util.HashMap;

public class ActionStrategyGamma implements ActionStrategy {
    public void performUnitActionAt(Tile[][] world, HashMap<Position,City> Cities, Position p) {
        int row = p.getRow();
        int column = p.getColumn();
        Unit unit = ((TileImpl) world[row][column]).getUnit();
        if(unit.getTypeString().equals(GameConstants.SETTLER)) {
            Tile tile = world[row][column];
            Cities.put(p,new CityImpl(unit.getOwner(), GameConstants.ARCHER, GameConstants.productionFocus));
            ((TileImpl) tile).setUnit(null);
        } else {
            unit.action();
        }
    }
}