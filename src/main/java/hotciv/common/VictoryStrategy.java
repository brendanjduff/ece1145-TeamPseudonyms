package hotciv.common;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

/* This strategy determines the winner */

public interface VictoryStrategy {

  public Player getWinner(int age, java.util.Map<Position, City> cities);
}
