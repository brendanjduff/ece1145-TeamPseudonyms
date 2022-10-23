package hotciv.common;

import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;
import java.util.Map;

/* This strategy performs the settler action */

public interface SettlerActionStrategy {

  public void performAction(Map<Position, CityImpl> city, Map<Position, UnitImpl> units,
      Position p);
}
