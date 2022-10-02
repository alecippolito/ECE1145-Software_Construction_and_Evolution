package hotciv.alternative;

import hotciv.framework.*;
import hotciv.standard.*;

public class ActionStrategyGamma implements ActionStrategy {
    public void performUnitActionAt(Tile[][] world, Position p) {
        int row = p.getRow();
        int column = p.getColumn();
        Unit unit = ((TileImpl) world[row][column]).getUnit();
        if(unit.getTypeString().equals(GameConstants.SETTLER)) {
            Tile tile = world[row][column];
            ((TileImpl) tile).addCity(unit.getOwner(), GameConstants.ARCHER, GameConstants.productionFocus);
            ((TileImpl) tile).setUnit(null);
        } else {
            unit.action();
        }
    }
}