package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import hotciv.factory.EtaCivFactory;
import hotciv.framework.GameConstants;
import hotciv.framework.MutableGame;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

public class TestEtaCiv {

  private MutableGame game;

  @Before
  public void setUp() {
    game = new GameImpl(new EtaCivFactory());
  }

  @Test
  public void workforceFocusOfCityAtR1C1IsProduction() {
    assertThat(game, is(notNullValue()));
    game.changeWorkForceFocusInCityAt(new Position(1, 1), GameConstants.productionFocus);
    assertThat(game.getCityAt(new Position(1, 1)).getWorkforceFocus(), is(GameConstants.productionFocus));
  }

  @Test
  public void workforceFocusOfCityAtR1C1IsFood() {
    assertThat(game, is(notNullValue()));
    game.changeWorkForceFocusInCityAt(new Position(1, 1), GameConstants.foodFocus);
    assertThat(game.getCityAt(new Position(1, 1)).getWorkforceFocus(), is(GameConstants.foodFocus));
  }

}
