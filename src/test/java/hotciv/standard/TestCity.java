package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

public class TestCity {
    private City city;

    @Test
    public void cityIsOwnedByRed() {
        city = new CityImpl(Player.RED);
        assertThat(city.getOwner(),is(Player.RED));
    }

    @Test
    public void cityIsOwnedByBlue() {
        city = new CityImpl(Player.BLUE);
        assertThat(city.getOwner(),is(Player.BLUE));
    }

    @Test
    public void cityPopulationIsOne() {
        city = new CityImpl(Player.RED);
        assertThat(city.getSize(),is(1));
    }

    @Test
    public void cityProductionIsSixEachTurn() {
        city = new CityImpl(Player.RED);
        assertThat(city.getTreasury(), is(0));
        city.startOfTurn(Player.RED);
        assertThat(city.getTreasury(), is(6));
    }

    @Test
    public void cityProductionDoesNotIncreaseOnOpponentsTurn() {
        city = new CityImpl(Player.RED);
        assertThat(city.getTreasury(), is(0));
        city.startOfTurn(Player.BLUE);
        assertThat(city.getTreasury(), is(0));
    }
}
