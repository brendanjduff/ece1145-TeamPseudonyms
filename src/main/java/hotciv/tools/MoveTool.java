package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import hotciv.view.UnitFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;

import java.awt.event.MouseEvent;

public class MoveTool extends SelectionTool {
    private Game game;
    private Position from;

    public MoveTool(Game game, DrawingEditor editor) {
        super(editor);
        this.game = game;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        super.mouseDown(e, x, y);

        if(draggedFigure instanceof UnitFigure) {
            from = GfxConstants.getPositionFromXY(x, y);
        } else {
            fChild = new NullTool();
            from = null;
        }
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        super.mouseUp(e, x, y);
        if(from != null) {
            game.moveUnit(from, GfxConstants.getPositionFromXY(x, y));
        }
    }

    @Override
    protected Tool createAreaTracker() { return new NullTool(); }
}