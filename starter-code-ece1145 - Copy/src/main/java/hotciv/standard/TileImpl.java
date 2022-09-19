package hotciv.standard;
import hotciv.framework.*;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.Player.*;


public class TileImpl implements Tile {

    //define the internal variables
    public String type;
    public Player owner;

    public boolean cityStatus = false;

    public String getTypeString() {
        return type;
    }

    public void setTileType(String s)
    {
        //should be a string from the game Constants class

        //test if the input string is legitimate (later)

        type = s;
    }

    public void setOwner(Player p)
    {
        //System.out.print(p);
        owner = p;
        //System.out.print(owner + "\n");
    }

    public Player getOwner()
    {
        //System.out.print(owner + "\n");
        return owner;
    }

    public boolean hasCity()
    {
        return cityStatus;
    }

    public void changeCityStatus(boolean status)
    {
        cityStatus = status;
    }


}
