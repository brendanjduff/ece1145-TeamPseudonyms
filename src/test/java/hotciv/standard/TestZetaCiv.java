package hotciv.standard;

import hotciv.factory.ZetaCivFactory;
import hotciv.framework.MutableCity;
import hotciv.framework.MutableGame;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class TestZetaCiv {

  private MutableGame game;

  @Before
  public void setUp() {
    game = new GameImpl(new ZetaCivFactory());
  }

  @Test
  public void conquerAllCitiesToWin() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getWinner(), is(nullValue()));
    ((MutableCity) game.getCityAt(new Position(4, 1))).setOwner(Player.RED);
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void win3AttacksToWin() {
    assertThat(game, is(notNullValue()));
    for (int i = 0; i < 20; i++) {
      game.endOfTurn();
    }

  }

}
