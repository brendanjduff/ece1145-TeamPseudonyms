package hotciv.variants;

import hotciv.common.ArcherActionStrategy;
import hotciv.standard.UnitImpl;

public class NoArcherActionStrategy implements ArcherActionStrategy {

  @Override
  public void performAction(UnitImpl unit) {
    // No action taken
  }
}
