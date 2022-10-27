package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.GameConstants;
import hotciv.framework.MutableCity;
import hotciv.framework.MutableUnit;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.variants.FullWorldLayoutStrategy;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class TestFullWorldLayoutStrategy {

  WorldLayoutStrategy worldLayoutStrategy;
  HashMap<Position, Tile> tiles;
  HashMap<Position, MutableCity> cities;
  HashMap<Position, MutableUnit> units;

  @Before
  public void setUp() {
    worldLayoutStrategy = new FullWorldLayoutStrategy();
    tiles = worldLayoutStrategy.placeTiles();
    cities = worldLayoutStrategy.placeCities();
    units = worldLayoutStrategy.placeUnits();
  }

  @Test
  public void shouldBe86OceansTiles() {
    assertThat(tiles, is(notNullValue()));
    int count = 0;
    for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
        if (tiles.get(new Position(r, c)).getTypeString().equals(GameConstants.OCEANS)) {
          count++;
        }
      }
    }
    assertThat(count, is(86));
  }

  @Test
  public void shouldBe9MountainsTiles() {
    assertThat(tiles, is(notNullValue()));
    int count = 0;
    for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
        if (tiles.get(new Position(r, c)).getTypeString().equals(GameConstants.MOUNTAINS)) {
          count++;
        }
      }
    }
    assertThat(count, is(9));
  }

  @Test
  public void shouldBe10HillsTiles() {
    assertThat(tiles, is(notNullValue()));
    int count = 0;
    for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
        if (tiles.get(new Position(r, c)).getTypeString().equals(GameConstants.HILLS)) {
          count++;
        }
      }
    }
    assertThat(count, is(10));
  }

  @Test
  public void shouldBe14ForestTiles() {
    assertThat(tiles, is(notNullValue()));
    int count = 0;
    for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
        if (tiles.get(new Position(r, c)).getTypeString().equals(GameConstants.FOREST)) {
          count++;
        }
      }
    }
    assertThat(count, is(14));
  }

  @Test
  public void shouldBe137PlainsTiles() {
    assertThat(tiles, is(notNullValue()));
    int count = 0;
    for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
        if (tiles.get(new Position(r, c)).getTypeString().equals(GameConstants.PLAINS)) {
          count++;
        }
      }
    }
    assertThat(count, is(137));
  }

  @Test
  public void shouldBeRedCityAtR8C12() {
    assertThat(cities, is(notNullValue()));
    assertThat(cities.get(new Position(8, 12)).getOwner(), is(Player.RED));
  }

  @Test
  public void shouldBeBlueCityAtR4C5() {
    assertThat(cities, is(notNullValue()));
    assertThat(cities.get(new Position(4, 5)).getOwner(), is(Player.BLUE));
  }

  @Test
  public void shouldBeRedArcherAtR3C8() {
    assertThat(units, is(notNullValue()));
    assertThat(units.get(new Position(3, 8)).getOwner(), is(Player.RED));
    assertThat(units.get(new Position(3, 8)).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void shouldBeBlueLegionAtR4C4() {
    assertThat(units, is(notNullValue()));
    assertThat(units.get(new Position(4, 4)).getOwner(), is(Player.BLUE));
    assertThat(units.get(new Position(4, 4)).getTypeString(), is(GameConstants.LEGION));
  }

  @Test
  public void shouldBeRedSettlerAtR5C5() {
    assertThat(units, is(notNullValue()));
    assertThat(units.get(new Position(5, 5)).getOwner(), is(Player.RED));
    assertThat(units.get(new Position(5, 5)).getTypeString(), is(GameConstants.SETTLER));
  }
}
