package hotciv.variants;

import hotciv.common.AgingStrategy;

public class LinearAgingStrategy implements AgingStrategy {

  @Override
  public int incrementAge(int age) {
    age += 100;
    return age;
  }
}
