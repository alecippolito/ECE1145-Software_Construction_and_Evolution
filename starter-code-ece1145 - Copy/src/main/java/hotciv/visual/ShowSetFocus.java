package hotciv.visual;

import hotciv.standard.GameObserverImpl;
import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

import static java.lang.Math.floor;

/** Template code for exercise FRS 36.40.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowSetFocus {
  
  public static void main(String[] args) {
    Game game = new StubGame2();

    DrawingEditor editor = 
      new MiniDrawApplication( "Click any tile to set focus",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Click a tile to see Game's setFocus method being called.");

    editor.setTool( new setFocusTool(game) );
  }
}

class setFocusTool extends NullTool{

  private Game game;

  public setFocusTool(Game game){
    this.game = game;
  }

  public void mouseDown(MouseEvent e, int x, int y){
    //retrieve the Location based on the mouse click location
    //only take action if the click is actually on the grid
    if (x >= GfxConstants.MAP_OFFSET_X && x <= GfxConstants.MAP_OFFSET_X+(16*GfxConstants.TILESIZE)){
      if (y >= GfxConstants.MAP_OFFSET_Y && y <= GfxConstants.MAP_OFFSET_Y+(16*GfxConstants.TILESIZE)){
        Position p = GfxConstants.getPositionFromXY(x,y);

        //run the setTileFocus function
        game.setTileFocus(p);
      }
    }
  }
}
