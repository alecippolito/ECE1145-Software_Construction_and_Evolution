package hotciv.visual;

import hotciv.alternative.SemiFactory;
import hotciv.standard.GameImpl;
import minidraw.standard.*;
import minidraw.framework.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import hotciv.framework.*;
import hotciv.view.*;
import hotciv.stub.*;

public class HotCivGUI {

    public static void main(String[] args) {
        Game game = new GameImpl(new SemiFactory());

        DrawingEditor editor =
                new MiniDrawApplication( "HotCiv",
                        new HotCivFactory3(game) );
        editor.open();
        TextFigure tf = new TextFigure("4000 BC",
                new Point(GfxConstants.AGE_TEXT_X,
                        GfxConstants.AGE_TEXT_Y) );
        editor.drawing().add(tf);
        editor.setTool( new ChangeAgeTool2(tf) );
    }
}

class ChangeAgeTool2 extends NullTool {
    private TextFigure textFigure;
    public ChangeAgeTool2(TextFigure tf) {
        textFigure = tf;
    }
    int count = 0;
    public void mouseDown(MouseEvent e, int x, int y) {
        count++;
        textFigure.setText( ""+(4000-count*100)+" BC" );
    }
}
