package hotciv.utility;

import java.util.Random;

public class RNG {

  static Random r = new Random();

  public static int d6() {
    return r.nextInt(6) + 1;
  }

}
