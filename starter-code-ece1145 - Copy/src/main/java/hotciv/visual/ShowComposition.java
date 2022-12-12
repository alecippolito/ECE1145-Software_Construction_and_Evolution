package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;
import minidraw.standard.handlers.DragTracker;

/** Template code for exercise FRS 36.44.

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
public class ShowComposition {
  
  public static void main(String[] args) {
    Game game = new StubGame2();

    DrawingEditor editor = 
      new MiniDrawApplication( "Click and/or drag any item to see all game actions",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Click and drag any item to see Game's proper response.");

    // TODO: Replace the setting of the tool with your CompositionTool implementation.
    editor.setTool( new CompositionTool(game,editor) );
  }
}

class CompositionTool extends MoveTool{
  private Game game;
  protected Tool fChild;
  protected Tool cachedNullTool;
  protected Figure draggedFigure;

  public CompositionTool(Game game, DrawingEditor editor) {
    super(game,editor);
    this.game = game;
    this.fChild = this.cachedNullTool = new NullTool();
    this.draggedFigure = null;
  }
  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    super.mouseDown(e, x, y);
    Drawing model = this.editor().drawing();
    model.lock();
    this.draggedFigure = model.findFigure(x, y);

    if (this.draggedFigure != null && this.draggedFigure.getClass() == UnitFigure.class &&
            this.game.getUnitAt(new Position((super.fAnchorY - 18) / 30, (super.fAnchorX - 13) / 30)) != null &&
            this.game.getUnitAt(new Position((super.fAnchorY - 18) / 30, (super.fAnchorX - 13) / 30)).getOwner().equals(this.game.getPlayerInTurn())) {
      this.fChild = this.createDragTracker(this.draggedFigure);
      this.fChild.mouseDown(e, x, y);
    } else if (e.isShiftDown()) {
      model.clearSelection();
    } else {
      this.draggedFigure = null;
    }


    //end of Turn
    //if the inputs are at a specified point, the game reaches an end of turn
    if (x >= GfxConstants.TURN_SHIELD_X && x <= GfxConstants.TURN_SHIELD_X+GfxConstants.TILESIZE && y >= GfxConstants.TURN_SHIELD_Y && y <= GfxConstants.TURN_SHIELD_Y+GfxConstants.TILESIZE){
      game.endOfTurn();
    }

    //set Focus
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
