package hotciv.unitconfig;

public class SettlerConfig implements UnitConfig {

  @Override
  public int getMaxMoveCount() {
    return 1;
  }

  @Override
  public int getAttackingStrength() {
    return 0;
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
  public boolean isFlying() {
    return false;
  }

  @Override
  public int getProductionCost() {
    return 30;
  }
}
