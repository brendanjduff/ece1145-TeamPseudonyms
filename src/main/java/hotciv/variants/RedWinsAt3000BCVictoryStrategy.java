package hotciv.variants;

import hotciv.common.VictoryStrategy;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import java.util.Map;

public class RedWinsAt3000BCVictoryStrategy implements VictoryStrategy {

  @Override
  public Player getWinner(int age, Map<Position, CityImpl> cities) {
    if (age == -3000) {
      return Player.RED;
    } else {
      return null;
    }
  }
}
