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

public class SparseWorldLayoutStrategy implements WorldLayoutStrategy {

  @Override
  public HashMap<Position, TileImpl> placeTiles() {
    HashMap<Position, TileImpl> tiles = new HashMap<>();
    for (Position p : Utility.getWorldLayoutIterable()) {
      String type = GameConstants.PLAINS;
      if (p.equals(new Position(1, 0))) {
        type = GameConstants.OCEANS;
      }
      if (p.equals(new Position(2, 2))) {
        type = GameConstants.MOUNTAINS;
      }
      if (p.equals(new Position(0, 1))) {
        type = GameConstants.HILLS;
      }
      tiles.put(p, new TileImpl(type));
    }
    return tiles;
  }

  @Override
  public HashMap<Position, CityImpl> placeCities() {
    HashMap<Position, CityImpl> cities = new HashMap<>();
    cities.put(new Position(1, 1), new CityImpl(Player.RED));
    cities.put(new Position(4, 1), new CityImpl(Player.BLUE));
    return cities;
  }

  @Override
  public HashMap<Position, UnitImpl> placeUnits() {
    HashMap<Position, UnitImpl> units = new java.util.HashMap<>();
    units.put(new Position(2, 0), new UnitImpl(GameConstants.ARCHER, Player.RED));
    units.put(new Position(3, 2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
    units.put(new Position(4, 3), new UnitImpl(GameConstants.SETTLER, Player.RED));
    return units;
  }
}
