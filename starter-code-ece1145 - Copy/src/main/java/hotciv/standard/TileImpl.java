package hotciv.standard;
import hotciv.framework.*;

import static hotciv.framework.GameConstants.*;


public class TileImpl implements Tile {

    //define the internal variables
    public String type;

    public void Tile(){
        type = "";
    }

    public String getTypeString() {
        return type;
    }

    public void setTileType(String s)
    {
        //should be a string from the game Constants class

        //test if the input string is legitimate (later)

        type = s;
    }


}
