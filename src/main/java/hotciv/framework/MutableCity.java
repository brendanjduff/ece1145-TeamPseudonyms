package hotciv.framework;

public interface MutableCity extends City {

  void setOwner(Player player);

  void setProduction(String unitType);

  boolean unitCostMet();

  void produceUnit();

  void setSize(int population);

  void setTreasury(int treasury);
}
