package hotciv.common;

import hotciv.framework.MutableGame;
import hotciv.framework.Player;

/* This strategy determines the winner */

public interface VictoryStrategy {

  public Player getWinner(MutableGame game);
}
