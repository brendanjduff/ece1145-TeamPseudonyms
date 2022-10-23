package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.ConquerAllCitiesVictoryStrategy;
import hotciv.variants.NoArcherActionStrategy;
import hotciv.variants.NoSettlerActionStrategy;
import hotciv.variants.ProgressiveAgingStrategy;
import hotciv.variants.SparseWorldLayoutStrategy;
import org.junit.Before;
import org.junit.Test;

public class TestBetaCiv {

  private Game game;

  @Before
  public void setUp() {
    game = new GameImpl(new ConquerAllCitiesVictoryStrategy(),
        new ProgressiveAgingStrategy(),
        new NoArcherActionStrategy(),
        new NoSettlerActionStrategy(),
        new SparseWorldLayoutStrategy());
  }

  @Test
  public void progAgingWorksCorrectlyInProgAgingStrategy() {
    assertThat(game, is(notNullValue()));
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(-3900));
    for (int i = 0; i < 2 * 38; i++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(-100));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getAge(), is(-1));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getAge(), is(1));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getAge(), is(50));
    for (int i = 0; i < 2 * 34; i++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(1750));
    for (int i = 0; i < 2 * 6; i++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(1900));
    for (int i = 0; i < 2 * 14; i++) {
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(1970));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getAge(), is(1971));
  }

  @Test
  public void conquerAllCitiesToWin() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getWinner(), is(nullValue()));
    ((CityImpl) game.getCityAt(new Position(4, 1))).setOwner(Player.RED);
    assertThat(game.getWinner(), is(Player.RED));
  }
}
