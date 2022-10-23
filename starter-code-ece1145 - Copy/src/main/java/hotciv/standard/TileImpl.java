package hotciv.standard;

import hotciv.framework.*;

import java.util.Objects;

public class TileImpl implements Tile {

    //define the internal variables
    public String type;
    public Player TileOwner = null;
    public Unit unit;

    public String getTypeString() {
        return type;
    }

    //constructor: set the Tile type
    TileImpl(String s){
        type = s;
    }


    //Add a position
    Position position;

    public void setTileType(String s)
    {
        //should be a string from the game Constants class

        //test if the input string is legitimate (later)

        type = s;
    }

    public void setOwner(Player p)
    {
        TileOwner = p;
    }

    public Player getOwner()
    {
        return TileOwner;
    }
    
    public Unit getUnit() {
        return unit;
    }
    
    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (o.getClass() != TileImpl.class) { return false; }
        TileImpl other = (TileImpl) o;
        return position.equals(other.position) && type.equals(other.type);
    }

}
