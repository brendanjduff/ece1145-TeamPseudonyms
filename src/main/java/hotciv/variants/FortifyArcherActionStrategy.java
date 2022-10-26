package hotciv.variants;

import hotciv.common.ArcherActionStrategy;
import hotciv.framework.MutableUnit;
import hotciv.standard.UnitImpl;

public class FortifyArcherActionStrategy implements ArcherActionStrategy {

  @Override
  public void performAction(MutableUnit unit) {
    if(unit.isMoveable()) {
      unit.setMoveable(false);
      unit.setDefensiveStrength(unit.getDefensiveStrength()*2);
    } else {
      unit.setMoveable(true);
      unit.setDefensiveStrength(unit.getDefensiveStrength()/2);
    }
  }
}
