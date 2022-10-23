package hotciv.framework;

import java.util.HashMap;

public interface ActionStrategy {
    public void performUnitActionAt(HashMap<Position,Tile> World, HashMap<Position,City> Cities, Position p);
}
