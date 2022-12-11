package hotciv.variants;

import hotciv.common.UnitActionStrategy;
import hotciv.framework.GameConstants;
import hotciv.framework.MutableCity;
import hotciv.framework.MutableGame;
import hotciv.framework.Position;

public class AbductUnitActionStrategy implements UnitActionStrategy {

  @Override
  public void performAction(Position p, MutableGame game) {
    if (game.getCities().containsKey(p)) {
      MutableCity city = game.getCities().get(p);
      city.setSize(city.getSize() - 1);
      if (city.getSize() <= 0) {
        game.getCities().remove(p);
        game.getObserver().worldChangedAt(p);
      }
    } else if (game.getTiles().get(p).getTypeString().equals(GameConstants.FOREST)) {
      game.getTiles().get(p).setTypeString(GameConstants.PLAINS);
    }
  }
}
