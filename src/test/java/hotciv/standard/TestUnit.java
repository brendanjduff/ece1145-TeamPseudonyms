package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import org.junit.Test;

public class TestUnit {

  private UnitImpl unit;

  @Test
  public void unitTypeShouldBeArcher() {
    unit = new UnitImpl(GameConstants.ARCHER, Player.RED);
    assertThat(unit.getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void unitTypeShouldBeLegion() {
    unit = new UnitImpl(GameConstants.LEGION, Player.BLUE);
    assertThat(unit.getTypeString(), is(GameConstants.LEGION));
  }

  @Test
  public void unitTypeShouldBeSettler() {
    unit = new UnitImpl(GameConstants.SETTLER, Player.RED);
    assertThat(unit.getTypeString(), is(GameConstants.SETTLER));
  }

  @Test
  public void unitShouldBelongToRed() {
    unit = new UnitImpl(GameConstants.SETTLER, Player.RED);
    assertThat(unit.getOwner(), is(Player.RED));
  }

  @Test
  public void unitShouldBelongToBlue() {
    unit = new UnitImpl(GameConstants.SETTLER, Player.BLUE);
    assertThat(unit.getOwner(), is(Player.BLUE));
  }

  @Test
  public void moveCountShouldDefaultTo1() {
    unit = new UnitImpl(GameConstants.SETTLER, Player.BLUE);
    assertThat(unit.getMoveCount(), is(1));
  }

  @Test
  public void canChangeMoveCountTo0() {
    unit = new UnitImpl(GameConstants.SETTLER, Player.BLUE);
    unit.decrementMoveCount();
    assertThat(unit.getMoveCount(), is(0));
  }

  @Test
  public void testArcherConfig() {
    unit = new UnitImpl(GameConstants.ARCHER, Player.RED);
    assertThat(unit.getMoveCount(), is(1));
    assertThat(unit.getAttackingStrength(), is(2));
    assertThat(unit.getDefensiveStrength(), is(3));
    assertThat(unit.isMovable(), is(true));
    assertThat(unit.isFlying(), is(false));
  }

  @Test
  public void testLegionConfig() {
    unit = new UnitImpl(GameConstants.LEGION, Player.RED);
    assertThat(unit.getMoveCount(), is(1));
    assertThat(unit.getAttackingStrength(), is(4));
    assertThat(unit.getDefensiveStrength(), is(2));
    assertThat(unit.isMovable(), is(true));
    assertThat(unit.isFlying(), is(false));
  }

  @Test
  public void testSettlerConfig() {
    unit = new UnitImpl(GameConstants.SETTLER, Player.RED);
    assertThat(unit.getMoveCount(), is(1));
    assertThat(unit.getAttackingStrength(), is(0));
    assertThat(unit.getDefensiveStrength(), is(3));
    assertThat(unit.isMovable(), is(true));
    assertThat(unit.isFlying(), is(false));
  }

  @Test
  public void testUFOConfigs() {
    unit = new UnitImpl(GameConstants.UFO, Player.RED);
    assertThat(unit.getMoveCount(), is(2));
    assertThat(unit.getAttackingStrength(), is(1));
    assertThat(unit.getDefensiveStrength(), is(8));
    assertThat(unit.isMovable(), is(true));
    assertThat(unit.isFlying(), is(true));
  }


  @Test
  public void shouldHaveMoveCount0WhenNotMovable() {
    unit = new UnitImpl(GameConstants.ARCHER, Player.RED);
    assertThat(unit.isMovable(), is(true));
    assertThat(unit.getMoveCount(), is(1));
    unit.setMovable(false);
    assertThat(unit.isMovable(), is(false));
    assertThat(unit.getMoveCount(), is(0));
  }
}
