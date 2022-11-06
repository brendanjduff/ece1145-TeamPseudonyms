package hotciv.variants;

import hotciv.common.UnitActionStrategy;
import hotciv.framework.MutableGame;
import hotciv.framework.MutableUnit;
import hotciv.framework.Position;

public class FortifyUnitActionStrategy implements UnitActionStrategy {

  @Override
  public void performAction(Position p, MutableGame game) {
    MutableUnit unit = game.getUnits().get(p);
    if (unit.isMovable()) {
      unit.setMovable(false);
      unit.setDefensiveStrength(unit.getDefensiveStrength() * 2);
    } else {
      unit.setMovable(true);
      unit.setDefensiveStrength(unit.getDefensiveStrength() / 2);
    }
  }
}
