package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import java.awt.event.MouseEvent;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

public class SetFocusTool extends NullTool {

  private final DrawingEditor editor;
  Game game;

  public SetFocusTool(Game game, DrawingEditor editor) {
    this.editor = editor;
    this.game = game;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    super.mouseDown(e, x, y);
    Position p = GfxConstants.getPositionFromXY(x, y);
    if (p.getRow() < 15 && p.getColumn() < 15) {
      game.setTileFocus((GfxConstants.getPositionFromXY(x, y)));
    }
  }
}
