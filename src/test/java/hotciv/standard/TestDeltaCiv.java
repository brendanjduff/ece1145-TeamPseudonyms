package hotciv.standard;

import hotciv.framework.*;

import hotciv.utility.Utility;
import hotciv.variants.*;
import org.junit.*;

import java.util.HashMap;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestDeltaCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new RedWinsAt3000BCVictoryStrategy(),
                new LinearAgingStrategy(),
                new NoArcherActionStrategy(),
                new NoSettlerActionStrategy(),
                new FullWorldLayoutStrategy());
    }

    @Test
    public void tilesCorrectlyPlaced() {
        assertThat(game, is(notNullValue()));
        HashMap<Position,Tile> tiles = new FullWorldLayoutStrategy().placeTiles();
        for(Position p : Utility.getWorldLayoutIterable()) {
            assertThat(game.getTileAt(p), samePropertyValuesAs(tiles.get(p)));
        }
    }

    @Test
    public void citiesCorrectlyPlaced() {
        assertThat(game, is(notNullValue()));
        HashMap<Position,City> cities = new FullWorldLayoutStrategy().placeCities();
        for(Position p : Utility.getWorldLayoutIterable()) {
            if(cities.containsKey(p)) {
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
        HashMap<Position,Unit> units = new FullWorldLayoutStrategy().placeUnits();
        for(Position p : Utility.getWorldLayoutIterable()) {
            if(units.containsKey(p)) {
                assertThat(game.getUnitAt(p), is(notNullValue()));
                assertThat(game.getUnitAt(p), samePropertyValuesAs(units.get(p)));
            } else {
                assertThat(game.getUnitAt(p), is(nullValue()));
            }
        }
    }
}
