package hotciv.utility;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import java.util.Iterator;

/**
 * Sep 2019: Updated the book's O computation utility functions.
 *
 * @author Henrik BÃ¦rbak Christensen, Aarhus University
 */
public class Utility2 {

  /**
   * get the terrain factor for the attack and defense strength according to the GammaCiv
   * specification
   *
   * @param game     the game the factor should be given for
   * @param position the position that the factor should be calculated for
   * @return the terrain factor
   */
  public static int getTerrainFactor(Game game, Position position) {
    // cities overrule underlying terrain
    if (game.getCityAt(position) != null) {
      return 3;
    }
    Tile t = game.getTileAt(position);
    if (t.getTypeString() == GameConstants.FOREST ||
        t.getTypeString() == GameConstants.HILLS) {
      return 2;
    }
    return 1;
  }

  /**
   * calculate the additional support a unit at position p owned by a given player gets from
   * friendly units on the given game.
   *
   * @param game     the game to calculate on
   * @param position the position of the unit whose support is wanted
   * @param player   the player owning the unit at position 'position'
   * @return the support for the unit, +1 for each friendly unit in the 8 neighborhood.
   */
  public static int getFriendlySupport(Game game, Position position,
      Player player) {
    Iterator<Position> neighborhood = Utility.get8neighborhoodIterator(position);
    Position p;
    int support = 0;
    while (neighborhood.hasNext()) {
      p = neighborhood.next();
      if (game.getUnitAt(p) != null &&
          game.getUnitAt(p).getOwner() == player) {
        support++;
      }
    }
    return support;
  }

}