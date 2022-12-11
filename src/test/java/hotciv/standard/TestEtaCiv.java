package hotciv.standard;

import hotciv.factory.EtaCivFactory;
import hotciv.framework.MutableGame;
import org.junit.Before;

public class TestEtaCiv {

  private MutableGame game;

  @Before
  public void setUp() {
    game = new GameImpl(new EtaCivFactory());
  }

}
