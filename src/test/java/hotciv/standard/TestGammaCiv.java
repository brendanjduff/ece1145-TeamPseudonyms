package hotciv.standard;

import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestGammaCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new RedWinsAt3000BCVictoryStrategy(),
                new LinearAgingStrategy(),
                new FortifyArcherActionStrategy(),
                new BuildCitySettlerActionStrategy(),
                new SparseWorldLayoutStrategy());
    }

    @Test
    public void performBuildCitySettlerActionOnR4C3() {
        assertThat(game, is(notNullValue()));
        game.performUnitActionAt(new Position(4,3));

        assertThat(game.getCityAt(new Position(4,3)).getOwner(), is(Player.RED));  //no change
    }

    @Test
    public void performFortifyArcherActionOnR2C0() {
        assertThat(game, is(notNullValue()));
        game.performUnitActionAt(new Position(2,0));

        assertThat(game.getUnitAt(new Position(2,0)).fortified(), is(true));  //no change
    }
}