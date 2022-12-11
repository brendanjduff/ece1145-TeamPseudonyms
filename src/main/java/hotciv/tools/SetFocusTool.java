package hotciv.tools;

import hotciv.factory.GameFactory;
import hotciv.framework.Game;
import hotciv.framework.MutableGame;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class SetFocusTool extends NullTool {
    private final DrawingEditor editor;
    Game game;

    public SetFocusTool(Game game, DrawingEditor editor) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e,x,y);
        game.setTileFocus((GfxConstants.getPositionFromXY(x,y)));
    }
}
