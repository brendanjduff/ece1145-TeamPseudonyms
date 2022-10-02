package hotciv.standard;
import hotciv.framework.*;

public class UnitImpl implements Unit {

    public UnitImpl(String type, Player player) {
        unitType = type;
        this.player = player;
        movement = 1;
        if(unitType.equals(GameConstants.ARCHER)) {
            defensiveStrength = 3;
        } else if (unitType.equals(GameConstants.LEGION)) {
            defensiveStrength = 2;
        } else if (unitType.equals(GameConstants.SETTLER)) {
            defensiveStrength = 3;
        }
        isFortified = false;
    }

    String unitType;
    Player player;

    int movement;
    int defensiveStrength;
    boolean isFortified;

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
        return movement;
    }

    @Override
    public int getDefensiveStrength() {
        return defensiveStrength;
    }

    public void setDefensiveStrength(int strength) { defensiveStrength = strength; }

    @Override
    public int getAttackingStrength() {
        return 0;
    }

    @Override
    public void setMoveCount(int count) { movement = count; }

    public boolean fortified(){
        return isFortified;
    }

    public void fortify(boolean fortify) { isFortified=fortify; }
}
