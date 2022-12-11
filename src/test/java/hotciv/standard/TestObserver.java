package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

import hotciv.common.BattleStrategy;
import hotciv.factory.AlphaCivFactory;
import hotciv.framework.GameConstants;
import hotciv.framework.MutableCity;
import hotciv.framework.MutableGame;
import hotciv.framework.MutableTile;
import hotciv.framework.MutableUnit;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.utility.Utility;
import hotciv.variants.AttackerWinsBattleStrategy;
import hotciv.variants.SparseWorldLayoutStrategy;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class TestObserver {

    private MutableGame game;
    private TestSpy observer;

    /**
     * Fixture for AlphaCiv testing.
     */
    @Before
    public void setUp() {
      game = new GameImpl(new AlphaCivFactory());
      observer = new TestSpy();
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


  //Todo FINISH THESE LATER





  @Test
  public void attackingUnitAlwaysWins() {
    assertThat(game, is(notNullValue()));
    MutableUnit attacker = new UnitImpl(GameConstants.ARCHER, Player.RED);
    MutableUnit defender = new UnitImpl(GameConstants.SETTLER, Player.BLUE);
    BattleStrategy battleStrategy = new AttackerWinsBattleStrategy();
    assertThat(
        battleStrategy.battle(attacker, new Position(0, 0), defender, new Position(0, 0), game),
        is(true));
  }

  @Test
  public void unitMustHaveMovementToMove() {
    assertThat(game, is(notNullValue()));
    ((MutableUnit) game.getUnitAt(new Position(2, 0))).decrementMoveCount();
    assertThat(game.moveUnit(new Position(2, 0), new Position(2, 1)), is(false));
  }


  @Test
  public void unitCannotMoveTwiceInOneTurn() {
    assertThat(game, is(notNullValue()));
    game.moveUnit(new Position(2, 0), new Position(2, 1));
    assertThat(game.getUnitAt(new Position(2, 1)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(2, 1)).getMoveCount(), is(0));
    assertThat(game.moveUnit(new Position(2, 1), new Position(3, 1)), is(false));
  }

  @Test
  public void unitCannotMoveFurtherThanMoveCount() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2, 0), new Position(4, 0)), is(false));
  }

  @Test
  public void cannotMoveIntoAllyUnit() {
    assertThat(game, is(notNullValue()));
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
    }
    assertThat(game.moveUnit(new Position(2, 0), new Position(2, 1)), is(true));
    assertThat(game.moveUnit(new Position(4, 3), new Position(3, 3)), is(true));
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
    }
    assertThat(game.moveUnit(new Position(2, 1), new Position(1, 1)), is(true));
    assertThat(game.moveUnit(new Position(3, 3), new Position(2, 3)), is(true));
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
    }
    assertThat(game.moveUnit(new Position(1, 1), new Position(1, 2)), is(true));
    assertThat(game.moveUnit(new Position(2, 3), new Position(1, 3)), is(true));
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
    }
    assertThat(game.moveUnit(new Position(1, 2), new Position(1, 3)), is(false));
    assertThat(game.moveUnit(new Position(1, 3), new Position(1, 2)), is(false));
  }

  @Test
  public void attackerMovesOnBattleWin() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2, 0), new Position(2, 1)), is(true));
    game.endOfTurn();
    assertThat(game.moveUnit(new Position(3, 2), new Position(2, 1)), is(true));
    assertThat(game.getUnitAt(new Position(2, 1)).getOwner(), is(Player.BLUE));
  }

  @Test
  public void takeOverOpponentsCityByMovingIntoIt() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2, 0), new Position(3, 1)), is(true));
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
    }
    assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.BLUE));
    assertThat(game.moveUnit(new Position(3, 1), new Position(4, 1)), is(true));
    assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.RED));
  }

  @Test
  public void moveIntoOwnCity() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2, 0), new Position(1, 1)), is(true));
  }

  @Test
  public void cannotMoveOpponentsUnit() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(3, 2), new Position(3, 1)), is(false));
  }

  @Test
  public void unitCannotMoveOntoOceanOrMountain() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2, 0), new Position(1, 0)), is(false));
    game.moveUnit(new Position(2, 0), new Position(2, 1));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getUnitAt(new Position(2, 1)).getMoveCount(), is(1));
    assertThat(game.moveUnit(new Position(2, 1), new Position(2, 2)), is(false));
  }

  @Test
  public void noMoveIfFromEqualsTo() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2,0), new Position(2,0)), is(false));
  }

  }
