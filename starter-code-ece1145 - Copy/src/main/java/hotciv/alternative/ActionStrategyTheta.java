package hotciv.alternative;

import hotciv.framework.*;
import hotciv.standard.*;
import java.util.HashMap;

public class ActionStrategyTheta implements ActionStrategy {
    public void performUnitActionAt(HashMap<Position,Tile> world, HashMap<Position,City> Cities, Position p) {
        Unit unit = ((TileImpl) world.get(p)).getUnit();
        if (unit.getTypeString().equals(GameConstants.UFO)) {
            unit.specialAction(world, Cities, p);
        } else if(unit.getTypeString().equals(GameConstants.SETTLER)) {
            Tile tile = world.get(p);
            Cities.put(p,new CityImpl(unit.getOwner(), GameConstants.ARCHER, GameConstants.productionFocus));
            ((TileImpl) tile).setUnit(null);
        } else {
            unit.action();
        }
    }
}
