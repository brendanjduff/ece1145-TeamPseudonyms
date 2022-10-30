package hotciv.framework;

import hotciv.utility.NumberGenerator;
import java.util.Map;

public interface MutableGame extends Game {

  Map<Position, Tile> getTiles();

  Map<Position, MutableCity> getCities();

  Map<Position, MutableUnit> getUnits();

  NumberGenerator getRNG();
}
