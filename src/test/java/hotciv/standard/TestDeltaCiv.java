package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

import hotciv.factory.DeltaCivFactory;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.utility.Utility;
import hotciv.variants.FullWorldLayoutStrategy;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class TestDeltaCiv {

  private Game game;

  @Before
  public void setUp() {
    game = new GameImpl(new DeltaCivFactory());
  }

  @Test
  public void tilesCorrectlyPlaced() {
    assertThat(game, is(notNullValue()));
    HashMap<Position, TileImpl> tiles = new FullWorldLayoutStrategy().placeTiles();
    for (Position p : Utility.getWorldLayoutIterable()) {
      assertThat(game.getTileAt(p), samePropertyValuesAs(tiles.get(p)));
    }
  }

  @Test
  public void citiesCorrectlyPlaced() {
    assertThat(game, is(notNullValue()));
    HashMap<Position, CityImpl> cities = new FullWorldLayoutStrategy().placeCities();
    for (Position p : Utility.getWorldLayoutIterable()) {
      if (cities.containsKey(p)) {
        assertThat(game.getCityAt(p), is(notNullValue()));
        assertThat(game.getCityAt(p), samePropertyValuesAs(cities.get(p)));
      } else {
        assertThat(game.getCityAt(p), is(nullValue()));
      }
    }
  }

  @Test
  public void unitsCorrectlyPlaced() {
    assertThat(game, is(notNullValue()));
    HashMap<Position, UnitImpl> units = new FullWorldLayoutStrategy().placeUnits();
    for (Position p : Utility.getWorldLayoutIterable()) {
      if (units.containsKey(p)) {
        assertThat(game.getUnitAt(p), is(notNullValue()));
        assertThat(game.getUnitAt(p), samePropertyValuesAs(units.get(p)));
      } else {
        assertThat(game.getUnitAt(p), is(nullValue()));
      }
    }
  }
}
