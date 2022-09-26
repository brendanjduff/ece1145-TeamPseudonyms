package hotciv.standard;

import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.*;
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
    public void shouldBe86OceansTiles() {
        assertThat(game, is(notNullValue()));
        int count = 0;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                if(game.getTileAt(new Position(r,c)).getTypeString() == GameConstants.OCEANS) {
                    count++;
                }
            }
        }
        assertThat(count, is(86));
    }

    @Test
    public void shouldBe9MountainsTiles() {
        assertThat(game, is(notNullValue()));
        int count = 0;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                if(game.getTileAt(new Position(r,c)).getTypeString() == GameConstants.MOUNTAINS) {
                    count++;
                }
            }
        }
        assertThat(count, is(9));
    }

    @Test
    public void shouldBe10HillsTiles() {
        assertThat(game, is(notNullValue()));
        int count = 0;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                if(game.getTileAt(new Position(r,c)).getTypeString() == GameConstants.HILLS) {
                    count++;
                }
            }
        }
        assertThat(count, is(10));
    }

    @Test
    public void shouldBe14ForestTiles() {
        assertThat(game, is(notNullValue()));
        int count = 0;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                if(game.getTileAt(new Position(r,c)).getTypeString() == GameConstants.FOREST) {
                    count++;
                }
            }
        }
        assertThat(count, is(14));
    }

    @Test
    public void shouldBe137PlainsTiles() {
        assertThat(game, is(notNullValue()));
        int count = 0;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                if(game.getTileAt(new Position(r,c)).getTypeString() == GameConstants.PLAINS) {
                    count++;
                }
            }
        }
        assertThat(count, is(137));
    }

    @Test
    public void shouldBeRedCityAtR8C12() {
        assertThat(game, is(notNullValue()));
        assertThat(game.getCityAt(new Position(8,12)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBeBlueCityAtR4C5() {
        assertThat(game, is(notNullValue()));
        assertThat(game.getCityAt(new Position(4,5)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldBeRedArcherAtR3C8() {
        assertThat(game, is(notNullValue()));
        assertThat(game.getUnitAt(new Position(3,8)).getOwner(),is(Player.RED));
        assertThat(game.getUnitAt(new Position(3,8)).getTypeString(),is(GameConstants.ARCHER));
    }

    @Test
    public void shouldBeBlueLegionAtR4C4() {
        assertThat(game, is(notNullValue()));
        assertThat(game.getUnitAt(new Position(4,4)).getOwner(),is(Player.BLUE));
        assertThat(game.getUnitAt(new Position(4,4)).getTypeString(),is(GameConstants.LEGION));
    }

    @Test
    public void shouldBeRedSettlerAtR5C5() {
        assertThat(game, is(notNullValue()));
        assertThat(game.getUnitAt(new Position(5,5)).getOwner(),is(Player.RED));
        assertThat(game.getUnitAt(new Position(5,5)).getTypeString(),is(GameConstants.SETTLER));
    }
}
