package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

import static hotciv.view.GfxConstants.getPositionFromXY;

public class PerformActionTool extends NullTool {
    private final Game game;

    public PerformActionTool(Game game) {
        this.game=game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        //we dont do anything on mouse down
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        //we execute on mouse up
        if(e.isShiftDown()) {
            game.performUnitActionAt(getPositionFromXY(x, y));
        }
    }
}
