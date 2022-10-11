package hotciv.variants;

import hotciv.common.VictoryStrategy;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import java.util.ArrayList;
import java.util.Map;

public class ConquerAllCitiesVictoryStrategy implements VictoryStrategy {

  @Override
  public Player getWinner(int age, Map<Position, CityImpl> cities) {
    ArrayList<Player> owners = new ArrayList<Player>();

    owners.add(cities.get(new Position(1, 1)).getOwner());
    owners.add(cities.get(new Position(4, 1)).getOwner());

    if (owners.get(0) == owners.get(1)) {
      return owners.get(0);
    } else {
      return null;
    }
  }
}
