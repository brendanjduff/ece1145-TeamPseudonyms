package hotciv.framework;

public interface MutableCity extends City {

  void setOwner(Player player);

  void setProduction(String unitType);

  void fillTreasury();

  boolean unitCostMet();

  void produceUnit();

  void setSize(int population);
}
