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

/** Template code for exercise FRS 36.42.

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
public class ShowEndOfTurn {
  
  public static void main(String[] args) {
    Game game = new StubGame2();

    DrawingEditor editor = 
      new MiniDrawApplication( "Click top shield to end the turn",  
                               new HotCivFactory4(game) );
    editor.open();

    editor.setTool( new EndOfTurnTool(game) );
  }
}


class EndOfTurnTool extends NullTool {
  private Game game;
  public EndOfTurnTool(Game g) {
    game = g;
  }
  public void mouseDown(MouseEvent e, int x, int y) {
    //if the inputs are at a specified point, the game reaches an end of turn
    if (x >= GfxConstants.TURN_SHIELD_X && x <= GfxConstants.TURN_SHIELD_X+GfxConstants.TILESIZE && y >= GfxConstants.TURN_SHIELD_Y && y <= GfxConstants.TURN_SHIELD_Y+GfxConstants.TILESIZE){
      game.endOfTurn();
    }
  }
}
