package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.MutableUnit;
import hotciv.framework.Player;
import hotciv.unitconfig.UnitConfig;

@SuppressWarnings("CanBeFinal")
public class UnitImpl implements MutableUnit {

  String unitType;
  Player player;
  int maxMoveCount;
  int moveCount;
  int attackingStrength;
  int defensiveStrength;
  boolean movable;
  boolean flying;

  public UnitImpl(String unitType, Player player) {
    this.player = player;
    this.unitType = unitType;
    UnitConfig config = GameConstants.unitConfigs.get(unitType);
    maxMoveCount = config.getMaxMoveCount();
    moveCount = maxMoveCount;
    attackingStrength = config.getAttackingStrength();
    defensiveStrength = config.getDefensiveStrength();
    movable = config.isMovable();
    flying = config.isFlying();
  }

  @Override
  public String getTypeString() {
    return unitType;
  }

  @Override
  public Player getOwner() {
    return player;
  }

  @Override
  public int getMoveCount() {
    if (!movable) {
      return 0;
    }
    return moveCount;
  }

  @Override
  public int getDefensiveStrength() {
    return defensiveStrength;
  }

  @Override
  public void setDefensiveStrength(int defensiveStrength) {
    this.defensiveStrength = defensiveStrength;
  }

  @Override
  public int getAttackingStrength() {
    return attackingStrength;
  }

  @Override
  public void decrementMoveCount() {
    moveCount--;
  }

  @Override
  public void resetMoveCount() {
    moveCount = maxMoveCount;
  }

  @Override
  public boolean isMovable() {
    return movable;
  }

  @Override
  public boolean isFlying() {
    return flying;
  }

  @Override
  public void setMovable(boolean movable) {
    this.movable = movable;
  }
}
