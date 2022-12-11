package hotciv.standard;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class GameObserverNULL implements GameObserver {

  @Override
  public void worldChangedAt(Position pos) {

  }

  @Override
  public void turnEnds(Player nextPlayer, int age) {

  }

  @Override
  public void tileFocusChangedAt(Position position) {

  }
}
