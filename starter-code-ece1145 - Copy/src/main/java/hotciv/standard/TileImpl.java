package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

import java.util.Objects;

public class TileImpl implements Tile {

    //define the internal variables
    public String type;
    public Player TileOwner = null;
    public Unit unit;

    //own a city inside a tile
    public City CityData;

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
        TileOwner = p;

        if (CityData.getOwner() == null){
            CityData.setOwner(p);
        }
    }

    public Player getOwner()
    {
        return TileOwner;
    }

    public boolean hasCity()
    {
        return Objects.nonNull(CityData);
    }

    public void addCity()
    {
        CityData = new CityImpl();

        //set the owner of the city to be the owner of the Tile it is on
        CityData.setOwner(TileOwner);
    }

    public void removeCity()
    {
        CityData = null;
    }
    
    public Unit getUnit() {
        return unit;
    }

    public City returnCity(){
        return CityData;
    }

}
