package hotciv.variants;

import hotciv.common.UnitActionStrategy;
import hotciv.framework.MutableGame;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;

public class BuildCityUnitActionStrategy implements UnitActionStrategy {

  @Override
  public void performAction(Position p, MutableGame game) {
    game.getCities().put(p, new CityImpl(game.getUnits().get(p).getOwner()));
    game.getUnits().remove(p);
    game.getObserver().worldChangedAt(p);
  }
}
