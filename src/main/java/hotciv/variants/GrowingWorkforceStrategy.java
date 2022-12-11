package hotciv.variants;

import hotciv.common.WorkforceStrategy;
import hotciv.framework.GameConstants;
import hotciv.framework.MutableCity;
import hotciv.framework.MutableGame;
import hotciv.framework.Position;
import hotciv.utility.Utility;
import java.util.Objects;

public class GrowingWorkforceStrategy implements WorkforceStrategy {

  @Override
  public void UpdatePopulation(Position pos, MutableCity city, MutableGame game) {
    if (city.getSize() < 9 && city.getFood() >= 5 + city.getSize() * 3) {
      city.setSize(city.getSize() + 1);
      city.setFood(0);
    }
  }

  @Override
  public void FillTreasury(Position pos, MutableCity city, MutableGame game) {
    // city provides 1 production and 1 food
    int production = 1, food = 1;
    if (city.getSize() > 1) {
      // Count quantity of each tile type surrounding city
      int plains = 0, oceans = 0, forests = 0, mountains = 0, hills = 0;
      for (Position p : Utility.get8neighborhoodOf(pos)) {
        String type = game.getTiles().get(p).getTypeString();
        switch (type) {
          case GameConstants.PLAINS -> plains++;
          case GameConstants.OCEANS -> oceans++;
          case GameConstants.FOREST -> forests++;
          case GameConstants.MOUNTAINS -> mountains++;
          case GameConstants.HILLS -> hills++;
        }
      }
      // population works tiles in priority order
      if (Objects.equals(city.getWorkforceFocus(), GameConstants.productionFocus)) {
        for (int i = 1; i < city.getSize(); i++) {
          if (forests > 0) {
            production += 3;
            forests--;
          } else if (hills > 0) {
            production += 2;
            hills--;
          } else if (mountains > 0) {
            production++;
            mountains--;
          } else if (plains > 0) {
            food += 3;
            plains--;
          } else if (oceans > 0) {
            food++;
            oceans--;
          }
        }
      } else if (Objects.equals(city.getWorkforceFocus(), GameConstants.foodFocus)) {
        for (int i = 1; i < city.getSize(); i++) {
          if (plains > 0) {
            food += 3;
            plains--;
          } else if (oceans > 0) {
            food++;
            oceans--;
          } else if (forests > 0) {
            production += 3;
            forests--;
          } else if (hills > 0) {
            production += 2;
            hills--;
          } else if (mountains > 0) {
            production++;
            mountains--;
          }
        }
      }
    }
    city.setTreasury(city.getTreasury() + production);
    city.setFood(city.getFood() + food);
  }
}
