package hotciv.variants;

import hotciv.common.UnitActionStrategy;
import hotciv.framework.GameConstants;
import hotciv.framework.MutableGame;
import hotciv.framework.MutableUnit;
import hotciv.framework.Position;

public class Fortify_BuildCity_UnitActionStrategy implements UnitActionStrategy {

  final UnitActionStrategy settler = new BuildCityUnitActionStrategy();
  final UnitActionStrategy archer = new FortifyUnitActionStrategy();

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
