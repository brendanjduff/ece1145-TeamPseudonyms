package hotciv.variants;

import hotciv.common.BattleStrategy;
import hotciv.framework.Unit;

public class AttackerWinsBattleStrategy implements BattleStrategy {

  @Override
  public boolean battle(Unit attacker, Unit defender) {
    return true;
  }
}
