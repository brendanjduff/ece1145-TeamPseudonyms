package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

public class TestCity {
    private City city;

    @Test
    public void cityPopulationIsOne() {
        city = new CityImpl(Player.RED);
        assertThat(city.getSize(),is(1));
    }

    @Test
    public void cityIsOwnedByRed() {
        city = new CityImpl(Player.RED);
        assertThat(city.getOwner(),is(Player.RED));
    }
}
