package hotciv.standard;

import hotciv.factory.EpsilonCivFactory;
import hotciv.framework.MutableGame;
import org.junit.Before;

public class TestEpsilonCiv {

  private MutableGame game;

  @Before
  public void setUp() {
    game = new GameImpl(new EpsilonCivFactory());
  }
}
