package hotciv.standard;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;
import java.util.Stack;

public class SpyObserver implements GameObserver {

  final Stack<String> log = new Stack<>();

  public String pop() {
    return log.pop();
  }

  @Override
  public void worldChangedAt(Position pos) {
    log.push("worldChangedAt(" + pos.getRow() + "," + pos.getColumn() + ")");
  }

  @Override
  public void turnEnds(Player nextPlayer, int age) {
    log.push("turnEnds(" + nextPlayer + "," + age + ")");
  }

  @Override
  public void tileFocusChangedAt(Position position) {
    log.push("tileFocusChangedAt(" + position.getRow() + "," + position.getColumn() + ")");
  }
}
