package hotciv.common;

import hotciv.framework.MutableGame;
import hotciv.framework.Position;

public interface UnitActionStrategy {

  void performAction(Position p, MutableGame game);
}
