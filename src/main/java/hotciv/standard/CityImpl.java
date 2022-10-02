package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City {

    public CityImpl(Player owner) {
        this.owner = owner;
        treasury = 0;
        production = "";
    }

    Player owner;
    int treasury;
    String production;

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public int getTreasury() {
        return treasury;
    }

    @Override
    public String getProduction() {
        return production;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }

    @Override
    public boolean endOfTurnProduction() {
        treasury += 6;
        if (production.equals(GameConstants.ARCHER) && treasury >= 10) {
            treasury -= 10;
            return true;
        }
        else if (production.equals(GameConstants.LEGION) && treasury >= 15) {
            treasury -= 15;
            return true;
        }
        else if(production.equals(GameConstants.SETTLER) && treasury >= 30) {
            treasury -= 30;
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void setProduction(String unitType) {
        production = unitType;
    }

    @Override
    public void setOwner(Player player) {
        owner = player;
    }
}