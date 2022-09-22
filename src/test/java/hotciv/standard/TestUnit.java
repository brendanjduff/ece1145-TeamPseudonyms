package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

public class TestUnit {
    private Unit unit;

    @Test
    public void unitTypeShouldBeArcher() {
        unit = new UnitImpl(GameConstants.ARCHER);
        assertThat(unit.getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void unitTypeShouldBeLegion() {
        unit = new UnitImpl(GameConstants.LEGION);
        assertThat(unit.getTypeString(), is(GameConstants.LEGION));
    }

    @Test
    public void unitTypeShouldBeSettler() {
        unit = new UnitImpl(GameConstants.SETTLER);
        assertThat(unit.getTypeString(), is(GameConstants.SETTLER));
    }
}
