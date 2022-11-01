package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.MutableCity;
import hotciv.framework.Player;

public class CityImpl implements MutableCity {

  public CityImpl(Player owner) {
    this.owner = owner;
    treasury = 0;
    population = 1;
    production = "";
  }

  Player owner;
  int treasury;
  int population;
  String production;

  @Override
  public Player getOwner() {
    return owner;
  }

  @Override
  public int getSize() {
    return population;
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

  public void setOwner(Player player) {
    owner = player;
  }

  public void setProduction(String unitType) {
    production = unitType;
  }

  public void fillTreasury() {
    treasury += 6;
  }

  public boolean unitCostMet() {
    if (GameConstants.unitConfigs.containsKey(production)) {
      return treasury >= GameConstants.unitConfigs.get(production).getProductionCost();
    }
    return false;
  }

  public void produceUnit() {
    if (GameConstants.unitConfigs.containsKey(production)) {
      treasury -= GameConstants.unitConfigs.get(production).getProductionCost();
    }
  }

  @Override
  public void setSize(int population) {
    this.population = population;
  }
}