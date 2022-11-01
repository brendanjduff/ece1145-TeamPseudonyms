package hotciv.unitconfig;

public interface UnitConfig {

  int getMaxMoveCount();

  int getAttackingStrength();

  int getDefensiveStrength();

  boolean isMovable();

  boolean isFlying();

  int getProductionCost();

}
