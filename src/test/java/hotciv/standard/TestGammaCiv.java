package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.BuildCitySettlerActionStrategy;
import hotciv.variants.FortifyArcherActionStrategy;
import hotciv.variants.LinearAgingStrategy;
import hotciv.variants.RedWinsAt3000BCVictoryStrategy;
import hotciv.variants.SparseWorldLayoutStrategy;
import org.junit.Before;
import org.junit.Test;

public class TestGammaCiv {

  private Game game;

  @Before
  public void setUp() {
    game = new GameImpl(new RedWinsAt3000BCVictoryStrategy(),
        new LinearAgingStrategy(),
        new FortifyArcherActionStrategy(),
        new BuildCitySettlerActionStrategy(),
        new SparseWorldLayoutStrategy());
  }

  @Test
  public void performBuildCitySettlerActionOnR4C3() {
    assertThat(game, is(notNullValue()));
    game.performUnitActionAt(new Position(4, 3));

    assertThat(game.getCityAt(new Position(4, 3)).getOwner(), is(Player.RED));  //no change
  }

  @Test
  public void performFortifyArcherActionOnR2C0() {
    assertThat(game, is(notNullValue()));
    game.performUnitActionAt(new Position(2, 0));

    assertThat(((UnitImpl) game.getUnitAt(new Position(2, 0))).fortified(), is(true));  //no change
  }
}
