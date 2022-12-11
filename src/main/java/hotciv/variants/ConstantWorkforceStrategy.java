package hotciv.variants;

import hotciv.common.WorkforceStrategy;
import hotciv.framework.MutableCity;
import hotciv.framework.MutableGame;
import hotciv.framework.Position;

public class ConstantWorkforceStrategy implements WorkforceStrategy {

  @Override
  public void UpdatePopulation(Position pos, MutableCity city, MutableGame game) {
    // do nothing
  }

  @Override
  public void FillTreasury(Position pos, MutableCity city, MutableGame game) {
    city.setTreasury(city.getTreasury() + 6);
  }
}
