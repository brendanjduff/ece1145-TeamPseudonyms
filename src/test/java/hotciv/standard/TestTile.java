package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

public class TestTile {
    private Tile tile;

    @Test
    public void shouldReturnTerrainAsPlains() {
        tile = new TileImpl(GameConstants.PLAINS);
        assertThat(tile.getTypeString(), is(GameConstants.PLAINS));
    }
}
