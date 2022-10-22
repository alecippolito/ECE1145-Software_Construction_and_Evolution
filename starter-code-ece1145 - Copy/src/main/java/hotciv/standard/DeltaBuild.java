package hotciv.standard;

import hotciv.framework.*;

public class DeltaBuild implements worldBuild{


    //hold the data for the 2D Tile array
    Tile[][] theWorld;

    // A simple implementation to draw the map of DeltaCiv

    /** Define the world as the DeltaCiv layout */
    DeltaBuild(){
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
        theWorld = new Tile[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
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
                theWorld[r][c] = new TileImpl(type);
            }
        }

        //add cities at the desired locations
        //RED city at (8,12)
        ((TileImpl) theWorld[8][12]).addCity(Player.RED,null,null);

        //BLUE city at (4,5)
        ((TileImpl) theWorld[4][5]).addCity(Player.BLUE,null,null);
    }

    public Tile[][] returnWorld(){
        return theWorld;
    }

}
