package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import hotciv.factory.ThetaCivFactory;
import hotciv.framework.GameConstants;
import hotciv.framework.MutableGame;
import hotciv.framework.MutableUnit;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

public class TestThetaCiv {

  private MutableGame game;

  @Before
  public void setUp() {
    game = new GameImpl(new ThetaCivFactory());
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
    assertThat(((MutableUnit) game.getUnitAt(new Position(2, 0))).isMovable(), is(false));
    assertThat((game.getUnitAt(new Position(2, 0))).getDefensiveStrength(), is(6));
  }

  @Test
  public void UFOShouldMoveOnMountains() {
    assertThat(game, is(notNullValue()));
    game.getUnits().put(new Position(0, 0), new UnitImpl(GameConstants.UFO, Player.RED));
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    assertThat(game.moveUnit(new Position(0, 0), new Position(0, 1)), is(true));
    assertThat(game.getUnitAt(new Position(0, 1)).getTypeString(), is(GameConstants.UFO));
    assertThat(game.getUnitAt(new Position(0, 1)).getMoveCount(), is(1));
  }

  @Test
  public void UFOShouldMoveOnOceans() {
    assertThat(game, is(notNullValue()));
    game.getUnits().put(new Position(0, 0), new UnitImpl(GameConstants.UFO, Player.RED));
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    assertThat(game.moveUnit(new Position(0, 0), new Position(1, 0)), is(true));
    assertThat(game.getUnitAt(new Position(1, 0)).getTypeString(), is(GameConstants.UFO));
    assertThat(game.getUnitAt(new Position(1, 0)).getMoveCount(), is(1));
  }

  @Test
  public void UFOShouldBeAbleToMoveTwicePerTurn() {
    assertThat(game, is(notNullValue()));
    game.getUnits().put(new Position(0, 5), new UnitImpl(GameConstants.UFO, Player.RED));
    assertThat(game.getUnitAt(new Position(0, 5)).getMoveCount(), is(2));
    assertThat(game.moveUnit(new Position(0, 5), new Position(0, 6)), is(true));
    assertThat(game.getUnitAt(new Position(0, 6)).getMoveCount(), is(1));
    assertThat(game.moveUnit(new Position(0, 6), new Position(0, 7)), is(true));
    assertThat(game.getUnitAt(new Position(0, 7)).getTypeString(), is(GameConstants.UFO));
    assertThat(game.getUnitAt(new Position(0, 7)).getMoveCount(), is(0));
    assertThat(game.moveUnit(new Position(0, 7), new Position(0, 8)), is(false));
  }

  @Test
  public void UFOShouldNotBeAbleToMoveTwoTilesInOneMove() {
    assertThat(game, is(notNullValue()));
    game.getUnits().put(new Position(0, 5), new UnitImpl(GameConstants.UFO, Player.RED));
    assertThat(game.getUnitAt(new Position(0, 5)).getMoveCount(), is(2));
    assertThat(game.moveUnit(new Position(0, 5), new Position(0, 7)), is(false));
    assertThat(game.getUnitAt(new Position(0, 5)).getMoveCount(), is(2));
  }
}
