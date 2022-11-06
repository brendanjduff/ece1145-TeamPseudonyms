package hotciv.standard;

import hotciv.factory.BetaCivFactory;
import hotciv.factory.SemiCivFactory;
import hotciv.framework.*;
import hotciv.utility.Utility;
import hotciv.variants.FullWorldLayoutStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

public class TestSemiCiv {

    private MutableGame game;

    @Before
    public void setUp() {
        game = new GameImpl(new SemiCivFactory());
    }

    //test aging strategy
    @Test
    public void progAgingWorksCorrectlyInProgAgingStrategy() {
        assertThat(game, is(notNullValue()));
        for (int i = 0; i < 2; i++) {
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(-3900));
        for (int i = 0; i < 2 * 38; i++) {
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(-100));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(-1));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(1));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(50));
        for (int i = 0; i < 2 * 34; i++) {
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1750));
        for (int i = 0; i < 2 * 6; i++) {
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1900));
        for (int i = 0; i < 2 * 14; i++) {
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1970));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(1971));
    }

    //test unit action - Gamma
    @Test
    public void performFortifyArcherActionOnR2C0() {
        assertThat(game, is(notNullValue()));
        game.performUnitActionAt(new Position(3, 8));
        assertThat(((MutableUnit) game.getUnitAt(new Position(3, 8))).isMovable(),
                is(false));
        assertThat((game.getUnitAt(new Position(3, 8))).getDefensiveStrength(),
                is(6));
    }


    //test world layout - Delta
    @Test
    public void tilesCorrectlyPlaced() {
        assertThat(game, is(notNullValue()));
        HashMap<Position, MutableTile> tiles = new FullWorldLayoutStrategy().placeTiles();
        for (Position p : Utility.getWorldLayoutIterable()) {
            assertThat(game.getTileAt(p), samePropertyValuesAs(tiles.get(p)));
        }
    }

    @Test
    public void citiesCorrectlyPlaced() {
        assertThat(game, is(notNullValue()));
        HashMap<Position, MutableCity> cities = new FullWorldLayoutStrategy().placeCities();
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
        HashMap<Position, MutableUnit> units = new FullWorldLayoutStrategy().placeUnits();
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
