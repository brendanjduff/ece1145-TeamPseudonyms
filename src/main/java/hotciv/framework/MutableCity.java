package hotciv.framework;

public interface MutableCity extends City {

  void setOwner(Player player);

  void setProduction(String unitType);

  void setWorkforceFocus(String workforceFocus);

  boolean unitCostMet();

  void produceUnit();

  void setSize(int population);

  void setTreasury(int treasury);

  int getFood();

  void setFood(int food);
}
