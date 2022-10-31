package hotciv.common;

import hotciv.framework.MutableGame;
import hotciv.framework.MutableUnit;
import hotciv.framework.Position;

public interface BattleStrategy {

  boolean battle(MutableUnit attacker, Position attackerPosition, MutableUnit defender,
      Position defenderPosition, MutableGame game);
}
