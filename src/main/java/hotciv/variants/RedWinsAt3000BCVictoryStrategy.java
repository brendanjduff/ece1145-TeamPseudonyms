package hotciv.variants;

import hotciv.common.VictoryStrategy;
import hotciv.framework.MutableGame;
import hotciv.framework.Player;

public class RedWinsAt3000BCVictoryStrategy implements VictoryStrategy {

  @Override
  public Player getWinner(MutableGame game) {
    if (game.getAge() == -3000) {
      return Player.RED;
    } else {
      return null;
    }
  }
}
