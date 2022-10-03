package hotciv.common;

import hotciv.framework.City;
import hotciv.framework.Position;
import hotciv.framework.Unit;

/* This strategy performs the settler action */

public interface SettlerActionStrategy {

  public void buildCity(java.util.Map<Position, City> city, java.util.Map<Position, Unit> units,
      Position p);
}
