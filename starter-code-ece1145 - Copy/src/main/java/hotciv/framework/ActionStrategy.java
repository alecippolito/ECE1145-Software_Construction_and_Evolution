package hotciv.framework;

public interface ActionStrategy {
    public void performUnitActionAt(Tile[][] World, Position p);
}
