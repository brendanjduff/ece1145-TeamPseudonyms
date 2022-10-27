package hotciv.common;

import hotciv.framework.MutableGame;
import hotciv.framework.MutableUnit;

public interface BattleStrategy {

  boolean battle(MutableUnit attacker, MutableUnit defender, MutableGame game);
}
