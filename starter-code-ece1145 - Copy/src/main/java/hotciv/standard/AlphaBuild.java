package hotciv.standard;

import hotciv.framework.*;

import static hotciv.framework.GameConstants.*;
import static hotciv.framework.Player.BLUE;
import static hotciv.framework.Player.RED;

public class AlphaBuild implements worldBuild{

    //Hold the data for the 2D Tile array
    Tile[][] World;

    //create the world
    AlphaBuild(){

        //Define the size of the World
        World = new Tile[WORLDSIZE][WORLDSIZE];

        //give Tiles to every position in the world
        //for now: initialize to PLAINS
        //loop through the World and initialize the tiles
        for (int i = 0; i < WORLDSIZE; i++) {
            for (int j = 0; j < WORLDSIZE; j++) {

                //have to define a new Tile pointer for each location to not have issues
                Tile tempTile = new TileImpl(PLAINS);

                World[i][j] = tempTile;
            }
        }


        //initialize certain points to be different Tiles

        //Mountain at (2,2)
        World[2][2] = new TileImpl(MOUNTAINS);

        //Ocean at (1,0)
        World[1][0] = new TileImpl(OCEANS);

        //Hills at (0,1)
        World[0][1] = new TileImpl(HILLS);


        //initialize city location
        //Red city at 1,1
        ((TileImpl) World[1][1]).addCity(RED,LEGION,productionFocus);

        //Blue city at 4,1
        ((TileImpl) World[4][1]).addCity(BLUE,null,null);

    }

    public Tile[][] returnWorld() {
        return World;
    }
}
