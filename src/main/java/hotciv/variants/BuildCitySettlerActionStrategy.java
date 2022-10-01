package hotciv.variants;

import hotciv.common.SettlerActionStrategy;
import hotciv.framework.City;
import hotciv.framework.Position;
import hotciv.framework.Unit;

import java.util.Map;

public class BuildCitySettlerActionStrategy implements SettlerActionStrategy {

    @Override
    public void buildCity(Map<Position, City> city, Map<Position, Unit> units, Position p) {
        /*
        Build city - remove settler from world and replace with a city of population size 1.
        Owner of city same as player who owned settler
         */
    }
    // TODO: Step 5, Implement behavior for GammaCiv
}
