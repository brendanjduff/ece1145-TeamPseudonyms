package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.MutableUnit;
import hotciv.framework.Player;
import hotciv.unitconfig.UnitConfig;

public class UnitImpl implements MutableUnit {

  public UnitImpl(String unitType, Player player) {
    this.player = player;
    this.unitType = unitType;
    UnitConfig config = GameConstants.unitConfigs.get(unitType);
    maxMoveCount = config.getMaxMoveCount();
    moveCount = maxMoveCount;
    attackingStrength = config.getAttackingStrength();
    defensiveStrength = config.getDefensiveStrength();
    movable = config.isMovable();
  }

  String unitType;
  Player player;

  int maxMoveCount;
  int moveCount;
  int attackingStrength;
  int defensiveStrength;
  boolean movable;

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
  public int getAttackingStrength() {
    return attackingStrength;
  }

  public void setMoveCount(int count) {
    moveCount = count;
  }

  @Override
  public boolean isMovable() {
    return movable;
  }

  @Override
  public void setMovable(boolean value) {
    movable = value;
  }

  @Override
  public void setDefensiveStrength(int value) {
    defensiveStrength = value;
  }
}
