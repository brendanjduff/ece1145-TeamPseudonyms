package hotciv.tools;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.view.GfxConstants;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import minidraw.standard.NullTool;

public class ManageCityTool extends NullTool {

  Game game;

  Map<String, String> cityProductionLoop;
  Map<String, String> cityFocusLoop;

  public ManageCityTool(Game game) {
    this.game = game;
    cityProductionLoop = new HashMap<>();
    cityProductionLoop.put(GfxConstants.NOTHING, GameConstants.ARCHER);
    cityProductionLoop.put(GameConstants.ARCHER, GameConstants.LEGION);
    cityProductionLoop.put(GameConstants.LEGION, GameConstants.SETTLER);
    cityProductionLoop.put(GameConstants.SETTLER, GfxConstants.NOTHING);
    cityFocusLoop = new HashMap<>();
    cityFocusLoop.put(GameConstants.productionFocus, GameConstants.foodFocus);
    cityFocusLoop.put(GameConstants.foodFocus, GameConstants.productionFocus);
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {

  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    if ((x > GfxConstants.CITY_PRODUCTION_X
        && x < GfxConstants.CITY_PRODUCTION_X + GfxConstants.TILESIZE)
        && (y > GfxConstants.CITY_PRODUCTION_Y
        && y < GfxConstants.CITY_PRODUCTION_Y + GfxConstants.TILESIZE)
        && game.getTileFocus() != null) {
      game.changeProductionInCityAt(game.getTileFocus(),
          cityProductionLoop.get(game.getCityAt(game.getTileFocus()).getProduction()));
      game.setTileFocus(game.getTileFocus());
    }
    if ((x > GfxConstants.WORKFORCEFOCUS_X
        && x < GfxConstants.WORKFORCEFOCUS_X + GfxConstants.TILESIZE)
        && (y > GfxConstants.WORKFORCEFOCUS_Y
        && y < GfxConstants.WORKFORCEFOCUS_Y + GfxConstants.TILESIZE)
        && game.getTileFocus() != null) {
      game.changeWorkForceFocusInCityAt(game.getTileFocus(),
          cityFocusLoop.get(game.getCityAt(game.getTileFocus()).getWorkforceFocus()));
      game.setTileFocus(game.getTileFocus());
    }
  }

}
