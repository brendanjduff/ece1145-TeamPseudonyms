package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class UnitImpl implements Unit {

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
    fortified = false;
  }

  String unitType;
  Player player;

  int movement;
  int attackingStrength;
  int defensiveStrength;
  boolean fortified;

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
    if (fortified) {
      return 0;
    }
    return movement;
  }

  @Override
  public int getDefensiveStrength() {
    if (fortified) {
      return defensiveStrength * 2;
    }
    return defensiveStrength;
  }

  @Override
  public int getAttackingStrength() {
    return attackingStrength;
  }

  public void setMoveCount(int count) {
    movement = count;
  }

  public boolean isFortified() {
    return fortified;
  }

  public void toggleFortification() {
    fortified = !fortified;
  }
}
