package hotciv.variants;

import hotciv.common.BattleStrategy;
import hotciv.framework.MutableGame;
import hotciv.framework.MutableUnit;

public class AttackerWinsBattleStrategy implements BattleStrategy {

  @Override
  public boolean battle(MutableUnit attacker, MutableUnit defender, MutableGame game) {
    return true;
  }
}
