package hotciv.variants;

import hotciv.common.VictoryStrategy;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.utility.Utility;
import java.util.Map;

public class ConquerAllCitiesVictoryStrategy implements VictoryStrategy {

  @Override
  public Player getWinner(int age, Map<Position, CityImpl> cities) {

    Player candidate = null;
    for (Position p : Utility.getWorldLayoutIterable()) {
      if (cities.containsKey(p)) {
        if (candidate == null) {
          candidate = cities.get(p).getOwner();
        }
        if (candidate != cities.get(p).getOwner()) {
          return null;
        }
      }
    }
    return candidate;
  }
}
