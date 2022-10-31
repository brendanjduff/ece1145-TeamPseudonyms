package hotciv.variants;

import hotciv.common.UnitActionStrategy;
import hotciv.framework.GameConstants;
import hotciv.framework.MutableGame;
import hotciv.framework.MutableUnit;
import hotciv.framework.Position;

public class FortifyAndBuildCityActionStrategy implements UnitActionStrategy {

  UnitActionStrategy settler = new BuildCityUnitActionStrategy();
  UnitActionStrategy archer = new FortifyUnitActionStrategy();

  @Override
  public void performAction(Position p, MutableGame game) {
    MutableUnit unit = game.getUnits().get(p);
    if (unit.getTypeString().equals(GameConstants.ARCHER)) {
      archer.performAction(p, game);
    } else if (unit.getTypeString().equals(GameConstants.SETTLER)) {
      settler.performAction(p, game);
    }
  }
}
