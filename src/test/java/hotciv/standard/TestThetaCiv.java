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

  @Test
  public void UFShouldNotConquerCityOutOfBattle() {
    assertThat(game, is(notNullValue()));
    game.getUnits().put(new Position(5, 1), new UnitImpl(GameConstants.UFO, Player.RED));
    game.moveUnit(new Position(5, 1), new Position(4, 1));
    assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.BLUE));
  }

  @Test
  public void UFShouldConquerCityInBattle() {
    assertThat(game, is(notNullValue()));
    game.getUnits().put(new Position(5, 1), new UnitImpl(GameConstants.UFO, Player.RED));
    game.getUnits().put(new Position(4, 1), new UnitImpl(GameConstants.LEGION, Player.BLUE));
    game.moveUnit(new Position(5, 1), new Position(4, 1));
    assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.RED));
  }

  @Test
  public void UFOShouldDestroyCityByAbduction() {
    assertThat(game, is(notNullValue()));
    game.getUnits().put(new Position(4, 1), new UnitImpl(GameConstants.UFO, Player.RED));
    assertThat(game.getCities().containsKey(new Position(4,1)), is(true));
    game.performUnitActionAt(new Position(4,1));
    assertThat(game.getCities().containsKey(new Position(4,1)), is(false));
  }

  @Test
  public void UFOShouldConvertForestToPlains() {
    assertThat(game, is(notNullValue()));
    game.getUnits().put(new Position(7, 7), new UnitImpl(GameConstants.UFO, Player.RED));
    game.getTiles().get(new Position(7,7)).setTypeString(GameConstants.FOREST);
    assertThat(game.getTileAt(new Position(7,7)).getTypeString(), is(GameConstants.FOREST));
    game.performUnitActionAt(new Position(7,7));
    assertThat(game.getTileAt(new Position(7,7)).getTypeString(), is(GameConstants.PLAINS));
  }

  @Test
  public void UFOShouldMoveOnHills() {
    assertThat(game, is(notNullValue()));
    game.getUnits().put(new Position(0, 0), new UnitImpl(GameConstants.UFO, Player.RED));
    game.getTiles().get(new Position(1,0)).setTypeString(GameConstants.HILLS);
    assertThat(game.getPlayerInTurn(), is(Player.RED));
    assertThat(game.moveUnit(new Position(0, 0), new Position(1, 0)), is(true));
    assertThat(game.getUnitAt(new Position(1, 0)).getTypeString(), is(GameConstants.UFO));
    assertThat(game.getUnitAt(new Position(1, 0)).getMoveCount(), is(1));
  }
}
