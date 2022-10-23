package hotciv.variants;

import hotciv.common.SettlerActionStrategy;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;
import java.util.Map;

public class NoSettlerActionStrategy implements SettlerActionStrategy {

  @Override
  public void performAction(Map<Position, CityImpl> city, Map<Position, UnitImpl> units, Position p) {
    // No action taken
  }
}
