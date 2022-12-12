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
                        new HotCivFactory4(game) );
        editor.open();
        editor.setTool( new CompositionTool(game,editor) );
    }
}
