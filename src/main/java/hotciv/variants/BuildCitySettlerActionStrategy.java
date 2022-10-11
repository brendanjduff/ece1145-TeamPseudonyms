package hotciv.variants;

import hotciv.common.SettlerActionStrategy;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;
import java.util.Map;

public class BuildCitySettlerActionStrategy implements SettlerActionStrategy {

  @Override
  public void buildCity(Map<Position, CityImpl> city, Map<Position, UnitImpl> units, Position p) {
    /* Build city - remove settler from world and replace with a city of population size 1.
    Owner of city same as player who owned settler */
    city.put(p, new CityImpl(units.get(p).getOwner()));
    units.remove(p);
  }
}
