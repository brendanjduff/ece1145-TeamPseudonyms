package hotciv.variants;

import hotciv.common.ArcherActionStrategy;
import hotciv.standard.UnitImpl;

public class FortifyArcherActionStrategy implements ArcherActionStrategy {

  @Override
  public void performAction(UnitImpl unit) {
    unit.toggleFortification();
  }
}
