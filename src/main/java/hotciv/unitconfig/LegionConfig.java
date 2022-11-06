package hotciv.unitconfig;

public class LegionConfig implements UnitConfig {

  @Override
  public int getMaxMoveCount() {
    return 1;
  }

  @Override
  public int getAttackingStrength() {
    return 4;
  }

  @Override
  public int getDefensiveStrength() {
    return 2;
  }

  @Override
  public boolean isMovable() {
    return true;
  }

  @Override
  public boolean isFlying() {
    return false;
  }

  @Override
  public int getProductionCost() {
    return 15;
  }
}
