package hotciv.common;

import hotciv.framework.MutableCity;
import hotciv.framework.MutableUnit;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import java.util.HashMap;

/* This strategy creates the world from tiles */

public interface WorldLayoutStrategy {

  /**
   * set all world tiles in a 16x16 grid
   *
   * @return hashmap containing the world tiles, which must contain a tile at each position in the
   * 16x16, 0-indexed grid
   */
  public HashMap<Position, Tile> placeTiles();

  /**
   * place starting cities
   *
   * @return hashmap containing the starting cities
   */
  public HashMap<Position, MutableCity> placeCities();

  /**
   * place starting units
   *
   * @return hashmap containing the starting units
   */
  public HashMap<Position, MutableUnit> placeUnits();
}
