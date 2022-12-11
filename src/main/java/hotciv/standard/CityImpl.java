package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.MutableCity;
import hotciv.framework.Player;

public class CityImpl implements MutableCity {

  Player owner;
  int treasury;
  int population;
  int food;
  String production;
  String focus;
  public CityImpl(Player owner) {
    this.owner = owner;
    treasury = 0;
    food = 0;
    population = 1;
    production = "black";
    focus = GameConstants.productionFocus;
  }

  @Override
  public Player getOwner() {
    return owner;
  }

  public void setOwner(Player player) {
    owner = player;
  }

  @Override
  public int getSize() {
    return population;
  }

  @Override
  public void setSize(int population) {
    this.population = population;
  }

  @Override
  public int getTreasury() {
    return treasury;
  }

  @Override
  public void setTreasury(int treasury) {
    this.treasury = treasury;
  }

  @Override
  public String getProduction() {
    return production;
  }

  public void setProduction(String unitType) {
    production = unitType;
  }

  @Override
  public String getWorkforceFocus() {
    return focus;
  }

  @Override
  public void setWorkforceFocus(String workforceFocus) {
    focus = workforceFocus;
  }

  @Override
  public boolean unitCostMet() {
    if (GameConstants.unitConfigs.containsKey(production)) {
      return treasury >= GameConstants.unitConfigs.get(production).getProductionCost();
    }
    return false;
  }

  @Override
  public void produceUnit() {
    if (GameConstants.unitConfigs.containsKey(production)) {
      treasury -= GameConstants.unitConfigs.get(production).getProductionCost();
    }
  }

  @Override
  public int getFood() {
    return food;
  }

  @Override
  public void setFood(int food) {
    this.food = food;
  }
}