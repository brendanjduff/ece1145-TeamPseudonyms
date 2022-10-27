package hotciv.variants;

import hotciv.common.VictoryStrategy;
import hotciv.framework.MutableGame;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.utility.Utility;

public class ConquerAllCitiesVictoryStrategy implements VictoryStrategy {

  @Override
  public Player getWinner(MutableGame game) {

    Player candidate = null;
    for (Position p : Utility.getWorldLayoutIterable()) {
      if (game.getCities().containsKey(p)) {
        if (candidate == null) {
          candidate = game.getCities().get(p).getOwner();
        }
        if (candidate != game.getCities().get(p).getOwner()) {
          return null;
        }
      }
    }
    return candidate;
  }
}
