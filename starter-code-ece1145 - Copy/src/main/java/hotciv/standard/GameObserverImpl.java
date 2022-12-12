package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.ImageFigure;
import minidraw.framework.*;
import minidraw.standard.*;
import java.awt.*;
import java.awt.event.*;

public class GameObserverImpl implements GameObserver {

    Game game;

    DrawingEditor editor;
    public GameObserverImpl(DrawingEditor editor){
        this.editor = editor;
        this.editor.showStatus("Current Player: RED, Age : -4000 BC");
    }

    //constructor used for the setFocusTool
    public GameObserverImpl(DrawingEditor editor, Game game){
        this.editor = editor;
        this.game = game;
    }

    public void worldChangedAt(Position pos){

    }

    public void turnEnds(Player nextPlayer, int age){
        //editor.drawing().turnEnds(nextPlayer,age);
    }

    public void tileFocusChangedAt(Position position){
        editor.drawing().requestUpdate();
    }
}
