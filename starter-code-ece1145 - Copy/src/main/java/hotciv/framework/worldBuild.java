package hotciv.framework;
import static hotciv.framework.GameConstants.*;
import hotciv.framework.*;

import java.util.HashMap;


/**
 * Used for DeltaCiv in Iteration 3
 * Abstract how the world will be initially built
 */

public interface worldBuild {


    /**
     * Initialize how the World will be built
     * @return a 2D Tile Array specifying the initial board
     */
    public HashMap<Position, Tile> returnTiles();

    public HashMap<Position, City> returnCities();

    public String getT();

}
