package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.Player.BLUE;
import static hotciv.framework.Player.RED;

public class AlphaBuild implements worldBuild{

    //Hold the data for the 2D Tile array
    private HashMap<Position,Tile> World;

    //Hold the data for the Cities
    private HashMap<Position,City> Cities;

    //create the world
    AlphaBuild(){

        Cities = new HashMap<>();

        //Define the size of the World
        World = new HashMap<>();

        //give Tiles to every position in the world
        //for now: initialize to PLAINS
        //loop through the World and initialize the tiles
        for (int r = 0; r < WORLDSIZE; r++) {
            for (int c = 0; c < WORLDSIZE; c++) {
                //have to define a new Tile pointer for each location to not have issues
                World.put(new Position(r,c),new TileImpl(PLAINS));
            }
        }


        //initialize certain points to be different Tiles

        //Mountain at (2,2)
        World.put(new Position(2,2),new TileImpl(MOUNTAINS));

        //Ocean at (1,0)
        World.put(new Position(1,0),new TileImpl(OCEANS));

        //Hills at (0,1)
        World.put(new Position(0,1),new TileImpl(HILLS));


        //initialize city locations
        //Red city at 1,1
        Cities.put(new Position(1,1) , new CityImpl(Player.RED,LEGION,productionFocus));

        //Blue city at 4,1
        Cities.put(new Position(4,1) , new CityImpl(Player.BLUE));
    }

    public HashMap returnTiles() {
        return World;
    }

    public HashMap<Position,City> returnCities(){
        return Cities;
    }
}
