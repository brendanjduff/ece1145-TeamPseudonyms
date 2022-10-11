package hotciv.variants;

import hotciv.common.ArcherActionStrategy;
import hotciv.standard.UnitImpl;

public class FortifyArcherActionStrategy implements ArcherActionStrategy {

  @Override
  public void fortify(UnitImpl unit) {
        /* Fortify - double defensive strength, cannot move
        If already fortified, remove fortification */
    if (!unit.fortified()) {
      unit.setDefensiveStrength(unit.getDefensiveStrength() * 2);
      unit.fortify(true);
    } else {
      unit.setDefensiveStrength(unit.getDefensiveStrength() / 2);
    }
  }
}
