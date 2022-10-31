package hotciv.variants;

import hotciv.common.BattleStrategy;
import hotciv.framework.MutableGame;
import hotciv.framework.MutableUnit;
import hotciv.framework.Position;

public class AttackerWinsBattleStrategy implements BattleStrategy {

  @Override
  public boolean battle(MutableUnit attacker, Position attackerPosition, MutableUnit defender,
      Position defenderPosition, MutableGame game) {
    return true;
  }
}
