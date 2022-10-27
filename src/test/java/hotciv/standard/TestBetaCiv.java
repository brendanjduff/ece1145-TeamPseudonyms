package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import hotciv.factory.BetaCivFactory;
import hotciv.framework.MutableCity;
import hotciv.framework.MutableGame;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

public class TestBetaCiv {

  private MutableGame game;

  @Before
  public void setUp() {
    game = new GameImpl(new BetaCivFactory());
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
    ((MutableCity) game.getCityAt(new Position(4, 1))).setOwner(Player.RED);
    assertThat(game.getWinner(), is(Player.RED));
  }
}
