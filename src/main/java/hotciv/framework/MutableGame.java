package hotciv.framework;

import java.util.Map;

public interface MutableGame extends Game {

  Map<Position, Tile> getTiles();

  Map<Position, MutableCity> getCities();

  Map<Position, MutableUnit> getUnits();

  Map<Player, Integer> getBattleWins();
}
