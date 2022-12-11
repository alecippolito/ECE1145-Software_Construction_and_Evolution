package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;
import java.awt.event.MouseEvent;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.AbstractTool;
import minidraw.standard.NullTool;
import minidraw.standard.handlers.DragTracker;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Template code for exercise FRS 36.39.

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
public class ShowMove {

  public static void main(String[] args) {
    Game game = new StubGame2();

    DrawingEditor editor =
            new MiniDrawApplication("Move any unit using the mouse",
                    new HotCivFactory4(game));
    editor.open();
    editor.showStatus("Move units to see Game's moveUnit method being called.");

    // TODO: Replace the setting of the tool with your UnitMoveTool implementation.
    editor.setTool(new MoveTool(game, editor));
  }
}

  class MoveTool extends AbstractTool {
    private Game game;
    protected Tool fChild;
    protected Tool cachedNullTool;
    protected Figure draggedFigure;

    public MoveTool(Game game, DrawingEditor editor) {
      super(editor);
      this.game = game;
      this.fChild = this.cachedNullTool = new NullTool();
      this.draggedFigure = null;
    }

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

    }

    public void mouseDrag(MouseEvent e, int x, int y) {
      this.fChild.mouseDrag(e, x, y);
    }

    public void mouseUp(MouseEvent arg0, int arg1, int arg2) {
      if (this.draggedFigure != null) {
        Position p1 = GfxConstants.getPositionFromXY(super.fAnchorX, super.fAnchorY);
        Position p2 = GfxConstants.getPositionFromXY(arg1, arg2);
        System.out.println("-- Game / moveUnit called: (" + p1.getRow() + "," + p1.getColumn() + ") ->" + "(" + p2.getRow() + "," + p2.getColumn() + ")");

        if (!this.game.moveUnit(new Position(p1.getRow(), p1.getColumn()), new Position(p2.getRow(), p2.getColumn()))) {
          this.draggedFigure.moveBy(super.fAnchorX - arg1, super.fAnchorY - arg2);
        }
      }

      this.editor().drawing().unlock();
      this.fChild = this.cachedNullTool;
      this.draggedFigure = null;
    }

    protected Tool createDragTracker(Figure f) {
      return new DragTracker(this.editor(), f);
    }
}

