package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.variants.SparseWorldLayoutStrategy;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class TestSparseWorldLayout {

  WorldLayoutStrategy worldLayoutStrategy;
  HashMap<Position, TileImpl> tiles;
  HashMap<Position, CityImpl> cities;
  HashMap<Position, UnitImpl> units;

  @Before
  public void setUp() {
    worldLayoutStrategy = new SparseWorldLayoutStrategy();
    tiles = worldLayoutStrategy.placeTiles();
    cities = worldLayoutStrategy.placeCities();
    units = worldLayoutStrategy.placeUnits();
  }

  @Test
  public void positionR1C0ShouldReturnOcean() {
    assertThat(tiles, is(notNullValue()));
    assertThat(tiles.get(new Position(1, 0)).getTypeString(), is(GameConstants.OCEANS));
  }

  @Test
  public void positionR0C1ShouldReturnHills() {
    assertThat(tiles, is(notNullValue()));
    assertThat(tiles.get(new Position(0, 1)).getTypeString(), is(GameConstants.HILLS));
  }

  @Test
  public void positionR2C2ShouldReturnMountains() {
    assertThat(tiles, is(notNullValue()));
    assertThat(tiles.get(new Position(2, 2)).getTypeString(), is(GameConstants.MOUNTAINS));
  }

  @Test
  public void allOtherPositionsShouldReturnPlains() {
    assertThat(tiles, is(notNullValue()));
    for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
        if (!(r == 1 && c == 0) && !(r == 0 && c == 1) && !(r == 2 && c == 2)) {
          assertThat(tiles.get(new Position(r, c)).getTypeString(), is(GameConstants.PLAINS));
        }
      }
    }
  }

  @Test
  public void cityAtR1C1BelongsToRed() {
    assertThat(cities, is(notNullValue()));
    assertThat(cities.get(new Position(1, 1)).getOwner(), is(Player.RED));
  }

  @Test
  public void cityAtR4C1BelongsToBlue() {
    assertThat(cities, is(notNullValue()));
    assertThat(cities.get(new Position(4, 1)).getOwner(), is(Player.BLUE));
  }

  @Test
  public void unitAtR2C0IsArcherBelongsToRed() {
    assertThat(units, is(notNullValue()));
    assertThat(units.get(new Position(2, 0)).getOwner(), is(Player.RED));
    assertThat(units.get(new Position(2, 0)).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void unitAtR3C2IsLegionBelongsToBlue() {
    assertThat(units, is(notNullValue()));
    assertThat(units.get(new Position(3, 2)).getOwner(), is(Player.BLUE));
    assertThat(units.get(new Position(3, 2)).getTypeString(), is(GameConstants.LEGION));
  }

  @Test
  public void unitAtR4C3IsSettlerBelongsToRed() {
    assertThat(units, is(notNullValue()));
    assertThat(units.get(new Position(4, 3)).getOwner(), is(Player.RED));
    assertThat(units.get(new Position(4, 3)).getTypeString(), is(GameConstants.SETTLER));
  }
}
