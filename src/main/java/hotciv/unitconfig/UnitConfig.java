package hotciv.unitconfig;

public interface UnitConfig {

  String getTypeString();

  int getMaxMoveCount();

  int getAttackingStrength();

  int getDefensiveStrength();

  boolean isMovable();

  int getProductionCost();

}
