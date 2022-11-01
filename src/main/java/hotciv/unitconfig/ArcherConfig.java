package hotciv.unitconfig;

import hotciv.framework.GameConstants;

public class ArcherConfig implements UnitConfig {

  @Override
  public String getTypeString() {
    return GameConstants.ARCHER;
  }

  @Override
  public int getMaxMoveCount() {
    return 1;
  }

  @Override
  public int getAttackingStrength() {
    return 2;
  }

  @Override
  public int getDefensiveStrength() {
    return 3;
  }

  @Override
  public boolean isMovable() {
    return true;
  }

  @Override
  public int getProductionCost() {
    return 10;
  }
}
