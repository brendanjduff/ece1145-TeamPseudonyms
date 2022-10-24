package hotciv.common;

import hotciv.framework.Unit;

public interface BattleStrategy {

  boolean battle(Unit attacker, Unit defender);
}
