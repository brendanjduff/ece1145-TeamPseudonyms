package hotciv.standard;

import hotciv.factory.ZetaCivFactory;
import hotciv.framework.MutableGame;
import org.junit.Before;

public class TestZetaCiv {

  private MutableGame game;

  @Before
  public void setUp() {
    game = new GameImpl(new ZetaCivFactory());
  }
}
