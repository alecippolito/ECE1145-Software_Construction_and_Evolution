package hotciv.alternative;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;

import java.util.HashMap;

public class DeltaBuild implements worldBuild{


    //hold the data for the 2D Tile array
    private HashMap<Position,Tile> theWorld;

    //hold the data for the cities
    private HashMap<Position,City> Cities;

    // A simple implementation to draw the map of DeltaCiv

    /** Define the world as the DeltaCiv layout */
    DeltaBuild(){

        Cities = new HashMap<>();

        // Basically we use a 'data driven' approach - code the
        // layout in a simple semi-visual representation, and
        // convert it to the actual Game representation.
        String[] layout =
                new String[] {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };
        // Conversion...
        theWorld = new HashMap<>();
        String line;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                theWorld.put( p, new TileImpl(type));
            }
        }

        //add cities at the desired locations
        //RED city at (8,12)
        Cities.put(new Position(8,12) , new CityImpl(Player.RED,GameConstants.SETTLER,GameConstants.productionFocus));

        //BLUE city at (4,5)
        Cities.put(new Position(4,5) , new CityImpl(Player.BLUE,GameConstants.ARCHER,GameConstants.foodFocus));
    }

    public HashMap<Position, Tile> returnTiles(){
        return theWorld;
    }

    public HashMap<Position,City> returnCities(){
        return Cities;
    }
}
