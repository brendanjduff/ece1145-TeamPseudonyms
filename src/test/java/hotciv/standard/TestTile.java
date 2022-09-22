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

    @Test
    public void shouldReturnTerrainAsOceans() {
        tile = new TileImpl(GameConstants.OCEANS);
        assertThat(tile.getTypeString(), is(GameConstants.OCEANS));
    }

    @Test
    public void shouldReturnTerrainAsForest() {
        tile = new TileImpl(GameConstants.FOREST);
        assertThat(tile.getTypeString(), is(GameConstants.FOREST));
    }

    @Test
    public void shouldReturnTerrainAsMountains() {
        tile = new TileImpl(GameConstants.MOUNTAINS);
        assertThat(tile.getTypeString(), is(GameConstants.MOUNTAINS));
    }

    @Test
    public void shouldReturnTerrainAsHills() {
        tile = new TileImpl(GameConstants.HILLS);
        assertThat(tile.getTypeString(), is(GameConstants.HILLS));
    }
}
