package hotciv.framework;

import hotciv.utility.NumberGenerator;
import java.util.Map;

public interface MutableGame extends Game {

  Map<Position, MutableTile> getTiles();

  Map<Position, MutableCity> getCities();

  Map<Position, MutableUnit> getUnits();

  Map<Player, Integer> getBattleWins();

  NumberGenerator getRNG();

  int getRound();

  GameObserver getObserver();
}
