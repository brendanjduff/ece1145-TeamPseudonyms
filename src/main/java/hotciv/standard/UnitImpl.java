package hotciv.standard;
import hotciv.framework.*;

public class UnitImpl implements Unit {

    public UnitImpl(String type, Player player) {
        unitType = type;
        this.player = player;
    }

    String unitType;
    Player player;

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
        return 0;
    }

    @Override
    public int getDefensiveStrength() {
        return 0;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
}
