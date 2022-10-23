package hotciv.framework;

import java.util.HashMap;

public interface ActionStrategy {
    public void performUnitActionAt(Tile[][] World, HashMap<Position,City> Cities, Position p);
}
