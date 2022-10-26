package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.MutableUnit;
import hotciv.framework.Player;

public class UnitImpl implements MutableUnit {

  public UnitImpl(String type, Player player) {
    unitType = type;
    this.player = player;
    movement = 1;
    if (unitType.equals(GameConstants.ARCHER)) {
      attackingStrength = 2;
      defensiveStrength = 3;
    } else if (unitType.equals(GameConstants.LEGION)) {
      attackingStrength = 4;
      defensiveStrength = 2;
    } else if (unitType.equals(GameConstants.SETTLER)) {
      attackingStrength = 0;
      defensiveStrength = 3;
    }
    moveable = true;
  }

  String unitType;
  Player player;

  int movement;
  int attackingStrength;
  int defensiveStrength;
  boolean moveable;

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
    if (!moveable) {
      return 0;
    }
    return movement;
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
    movement = count;
  }

  @Override
  public boolean isMoveable() {
    return moveable;
  }

  @Override
  public void setMoveable(boolean value) {
    moveable = value;
  }

  @Override
  public void setDefensiveStrength(int value) {
    defensiveStrength = value;
  }
}
