package hotciv.standard;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import minidraw.framework.DrawingEditor;

public class GameObserverImpl implements GameObserver {

    DrawingEditor editor;
    public GameObserverImpl(DrawingEditor editor){
        this.editor = editor;
        this.editor.showStatus("Current Player: RED, Age : -4000 BC");
    }

    public void worldChangedAt(Position pos){

    }

    public void turnEnds(Player nextPlayer, int age){
        String yeartype;
        if (age > 0){
            yeartype = "AD";
        }
        else {
            yeartype = "BC";
        }
        editor.showStatus("Current Player: "+nextPlayer+", Age : "+age+" "+yeartype);
    }

    public void tileFocusChangedAt(Position position){

    }
}
