package hotciv.variants;

import hotciv.common.VictoryStrategy;
import hotciv.framework.MutableGame;
import hotciv.framework.Player;

public class ConquerAllCitiesOrWinThreeAttacksAfter20RoundsVictoryStrategy implements
    VictoryStrategy {

  final VictoryStrategy conquerAllCities = new ConquerAllCitiesVictoryStrategy();
  final VictoryStrategy successfulAttacks = new FirstPlayerToThreeSuccessfulAttacksVictoryStrategy();
  boolean resetBattleWins = false;

  @Override
  public Player getWinner(MutableGame game) {
    if (game.getRound() <= 20) {
      return conquerAllCities.getWinner(game);
    } else if (!resetBattleWins) {
      for (Player p : game.getBattleWins().keySet()) {
        game.getBattleWins().put(p, 0);
      }
      resetBattleWins = true;
    }
    return successfulAttacks.getWinner(game);
  }
}
