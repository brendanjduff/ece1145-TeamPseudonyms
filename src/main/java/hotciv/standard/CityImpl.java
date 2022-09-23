package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City {

    public CityImpl(Player owner) {
        this.owner = owner;
        treasury = 0;
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
    public void startOfTurn(Player player) {
        if(player == owner) {
            treasury += 6;
        }
    }

    @Override
    public void setProduction(String unitType) {
        production = unitType;
    }
}