package hotciv.visual;

import hotciv.standard.GameObserverImpl;
import minidraw.standard.*;
import minidraw.framework.*;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.AbstractTool;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

/** Template code for exercise FRS 36.43.

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
public class ShowAction {

  public static void main(String[] args) {
    Game game = new StubGame2();

    DrawingEditor editor =
            new MiniDrawApplication("Shift-Click unit to invoke its action",
                    new HotCivFactory4(game));
    editor.open();
    //GameObserver observer = new GameObserverImpl(editor);
    //game.addObserver(observer);

    // TODO: Replace the setting of the tool with your ActionTool implementation.
    editor.setTool(new ActionTool(game,editor));
  }
}


  class ActionTool extends AbstractTool {
    private Game game;

    public ActionTool(Game game, DrawingEditor editor) {
      super(editor);
      this.game = game;
    }

    public void mouseDown(MouseEvent e, int x, int y) {
      super.mouseDown(e, x, y);
      Drawing model = this.editor().drawing();
      model.lock();
      Figure figure = model.findFigure(x, y);

      if (figure != null && figure.getClass() ==
              UnitFigure.class && this.game.getUnitAt(new Position((super.fAnchorY - 18) / 30, (super.fAnchorX - 13) / 30)) != null &&
              this.game.getUnitAt(new Position((super.fAnchorY - 18) / 30, (super.fAnchorX - 13) / 30)).getOwner().equals(this.game.getPlayerInTurn()) &&
              e.isShiftDown()) {

        Position p = GfxConstants.getPositionFromXY(x, y);
        this.game.performUnitActionAt(p);

      }
    }

    public void mouseUp(MouseEvent arg0, int arg1, int arg2) {
      this.editor().drawing().unlock();
    }
}


