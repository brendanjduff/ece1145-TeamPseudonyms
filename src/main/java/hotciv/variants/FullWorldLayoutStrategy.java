package hotciv.variants;

import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;
import hotciv.utility.Utility;
import java.util.HashMap;

public class FullWorldLayoutStrategy implements WorldLayoutStrategy {

  @Override
  public HashMap<Position, TileImpl> placeTiles() {
    String[] layout = new String[]{
        "...ooMooooo.....",
        "..ohhoooofffoo..",
        ".oooooMooo...oo.",
        ".ooMMMoooo..oooo",
        "...ofooohhoooo..",
        ".ofoofooooohhoo.",
        "...ooo..........",
        ".ooooo.ooohooM..",
        ".ooooo.oohooof..",
        "offfoooo.offoooo",
        "oooooooo...ooooo",
        ".ooMMMoooo......",
        "..ooooooffoooo..",
        "....ooooooooo...",
        "..ooohhoo.......",
        ".....ooooooooo.."};
    HashMap<Position, TileImpl> tiles = new HashMap<>();
    for (Position p : Utility.getWorldLayoutIterable()) {
      char tileChar = layout[p.getRow()].charAt(p.getColumn());
      String type = "error";
      if (tileChar == '.') {
        type = GameConstants.OCEANS;
      }
      if (tileChar == 'o') {
        type = GameConstants.PLAINS;
      }
      if (tileChar == 'M') {
        type = GameConstants.MOUNTAINS;
      }
      if (tileChar == 'f') {
        type = GameConstants.FOREST;
      }
      if (tileChar == 'h') {
        type = GameConstants.HILLS;
      }
      tiles.put(p, new TileImpl(type));
    }
    return tiles;
  }

  @Override
  public HashMap<Position, CityImpl> placeCities() {
    HashMap<Position, CityImpl> cities = new HashMap<>();
    cities.put(new Position(8, 12), new CityImpl(Player.RED));
    cities.put(new Position(4, 5), new CityImpl(Player.BLUE));
    return cities;
  }

  @Override
  public HashMap<Position, UnitImpl> placeUnits() {
    HashMap<Position, UnitImpl> units = new java.util.HashMap<>();
    units.put(new Position(3, 8), new UnitImpl(GameConstants.ARCHER, Player.RED));
    units.put(new Position(4, 4), new UnitImpl(GameConstants.LEGION, Player.BLUE));
    units.put(new Position(5, 5), new UnitImpl(GameConstants.SETTLER, Player.RED));
    return units;
  }
}
