package hotciv.utility;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

  Random r = new Random();

  public int d6() {
    return r.nextInt(6) + 1;
  }

}
