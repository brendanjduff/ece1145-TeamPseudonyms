package hotciv.variants;

import hotciv.common.UnitActionStrategy;
import hotciv.framework.GameConstants;
import hotciv.framework.MutableGame;
import hotciv.framework.MutableUnit;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;

public class FortifyAndBuildCityActionStrategy implements UnitActionStrategy {

  @Override
  public void performAction(Position p, MutableGame game) {
    MutableUnit unit = game.getUnits().get(p);
    if (unit.getTypeString().equals(GameConstants.ARCHER)) {
      if (unit.isMovable()) {
        unit.setMovable(false);
        unit.setDefensiveStrength(unit.getDefensiveStrength() * 2);
      } else {
        unit.setMovable(true);
        unit.setDefensiveStrength(unit.getDefensiveStrength() / 2);
      }
    } else if (unit.getTypeString().equals(GameConstants.SETTLER)) {
      game.getCities().put(p, new CityImpl(unit.getOwner()));
      game.getUnits().remove(p);
    }
  }
}
