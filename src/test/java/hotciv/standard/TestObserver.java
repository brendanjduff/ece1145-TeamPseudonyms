package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import hotciv.common.BattleStrategy;
import hotciv.factory.AlphaCivFactory;
import hotciv.framework.GameConstants;
import hotciv.framework.MutableGame;
import hotciv.framework.MutableUnit;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.AttackerWinsBattleStrategy;
import org.junit.Before;
import org.junit.Test;

public class TestObserver {

    private MutableGame game;
    private SpyObserver observer;

    /**
     * Fixture for AlphaCiv testing.
     */
    @Before
    public void setUp() {
      game = new GameImpl(new AlphaCivFactory());
      observer = new SpyObserver();
      game.addObserver(observer);
    }


  @Test
  public void shouldObserveEndOfTurn() {
    assertThat(game, is(notNullValue()));
    game.endOfTurn();
    assertThat(observer.pop(), is("turnEnds(BLUE,-4000)"));
    game.endOfTurn();
    assertThat(observer.pop(), is("turnEnds(RED,-3900)"));
  }

  @Test
  public void shouldObserveTileFocus() {
    assertThat(game, is(notNullValue()));
    game.setTileFocus(new Position(10,10));
    assertThat(observer.pop(), is("tileFocusChangedAt(10,10)"));
  }

  @Test
  public void shouldObserveUnitProduction() {
    assertThat(game, is(notNullValue()));
    game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
      observer.pop();
    }
    assertThat(game.getUnitAt(new Position(1, 1)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(observer.pop(), is("worldChangedAt(1,1)"));
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
      observer.pop();
    }
    assertThat(game.getUnitAt(new Position(0, 1)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(observer.pop(), is("worldChangedAt(0,1)"));
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
      observer.pop();
    }
    assertThat(game.getUnitAt(new Position(0, 2)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(observer.pop(), is("worldChangedAt(0,2)"));
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
      observer.pop();
    }
    assertThat(game.getUnitAt(new Position(1, 2)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(observer.pop(), is("worldChangedAt(1,2)"));
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
      observer.pop();
    }
    assertThat(game.getUnitAt(new Position(2, 1)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(observer.pop(), is("worldChangedAt(2,1)"));
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
      observer.pop();
    }
    assertThat(game.getUnitAt(new Position(0, 0)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(observer.pop(), is("worldChangedAt(0,0)"));
  }

  @Test
  public void shouldObserveMoveAndBattle() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2, 0), new Position(2, 1)), is(true));
    assertThat(observer.pop(), is("worldChangedAt(2,1)"));
    assertThat(observer.pop(), is("worldChangedAt(2,0)"));
    game.endOfTurn();
    observer.pop();
    assertThat(game.moveUnit(new Position(3, 2), new Position(2, 1)), is(true));
    assertThat(game.getUnitAt(new Position(2, 1)).getOwner(), is(Player.BLUE));
    assertThat(observer.pop(), is("worldChangedAt(2,1)"));
    assertThat(observer.pop(), is("worldChangedAt(3,2)"));
  }

  @Test
  public void shouldObserveCityTakeover() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2, 0), new Position(3, 1)), is(true));
    observer.pop();
    observer.pop();
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
      observer.pop();
    }
    assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.BLUE));
    assertThat(game.moveUnit(new Position(3, 1), new Position(4, 1)), is(true));
    assertThat(observer.pop(), is("worldChangedAt(4,1)"));
    assertThat(observer.pop(), is("worldChangedAt(4,1)"));
    assertThat(observer.pop(), is("worldChangedAt(3,1)"));
    assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.RED));
  }
  }
