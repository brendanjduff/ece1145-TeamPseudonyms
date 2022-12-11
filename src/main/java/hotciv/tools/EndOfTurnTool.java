package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.MutableGame;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import java.awt.event.MouseEvent;
import minidraw.standard.NullTool;

public class EndOfTurnTool extends NullTool {

  Game game;

  public EndOfTurnTool(Game game) {
    this.game = game;
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {

  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    if ((x > GfxConstants.TURN_SHIELD_X && x < GfxConstants.TURN_SHIELD_X + GfxConstants.TILESIZE)
        && (y > GfxConstants.TURN_SHIELD_Y
        && y < GfxConstants.TURN_SHIELD_Y + GfxConstants.TILESIZE)) {
      game.endOfTurn();
      ((MutableGame)game).getObserver().worldChangedAt(new Position(0,0));
    }
  }

}
