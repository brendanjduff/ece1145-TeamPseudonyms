package hotciv.common;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;

/** This strategy determines the winner
 */

public interface VictoryStrategy {
    // TODO: Step 1, define interface
    public Player getWinner(int age, java.util.Map<Position, City> cities);
}
