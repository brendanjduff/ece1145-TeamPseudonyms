package hotciv.common;

import hotciv.framework.MutableCity;
import hotciv.framework.MutableGame;
import hotciv.framework.Position;

public interface WorkforceStrategy {

  void UpdatePopulation(Position pos, MutableCity city, MutableGame game);

  void FillTreasury(Position pos, MutableCity city, MutableGame game);
}
