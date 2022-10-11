package hotciv.common;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import java.util.Map;

/* This strategy determines the winner */

public interface VictoryStrategy {

  public Player getWinner(int age, Map<Position, CityImpl> cities);
}
