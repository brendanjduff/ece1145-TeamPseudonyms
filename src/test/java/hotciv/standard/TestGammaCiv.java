package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import hotciv.factory.GammaCivFactory;
import hotciv.framework.MutableGame;
import hotciv.framework.MutableUnit;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

public class TestGammaCiv {

  private MutableGame game;

  @Before
  public void setUp() {
    game = new GameImpl(new GammaCivFactory());
  }

  @Test
  public void performBuildCitySettlerActionOnR4C3() {
    assertThat(game, is(notNullValue()));
    game.performUnitActionAt(new Position(4, 3));
    assertThat(game.getCityAt(new Position(4, 3)).getOwner(), is(Player.RED));
  }

  @Test
  public void performFortifyArcherActionOnR2C0() {
    assertThat(game, is(notNullValue()));
    game.performUnitActionAt(new Position(2, 0));
    assertThat(((MutableUnit) game.getUnitAt(new Position(2, 0))).isMoveable(),
        is(false));
    assertThat((game.getUnitAt(new Position(2, 0))).getDefensiveStrength(),
        is(6));
  }
}
