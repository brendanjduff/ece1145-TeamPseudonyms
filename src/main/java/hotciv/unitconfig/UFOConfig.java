package hotciv.unitconfig;

public class UFOConfig implements UnitConfig {

  @Override
  public int getMaxMoveCount() {
    return 2;
  }

  @Override
  public int getAttackingStrength() {
    return 1;
  }

  @Override
  public int getDefensiveStrength() {
    return 8;
  }

  @Override
  public boolean isMovable() {
    return true;
  }

  @Override
  public boolean isFlying() {
    return true;
  }

  @Override
  public int getProductionCost() {
    return 60;
  }

}
