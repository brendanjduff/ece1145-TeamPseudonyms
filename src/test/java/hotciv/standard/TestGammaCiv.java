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
        game = new GameImpl();
        game.setVictoryStrategy(new RedWinsAt3000BCVictoryStrategy());
        game.setAgingStrategy(new LinearAgingStrategy());
        game.setArcherActionStrategy(new FortifyArcherActionStrategy());
        game.setSettlerActionStrategy(new BuildCitySettlerActionStrategy());
        game.setWorldLayoutStrategy(new SparseWorldLayoutStrategy());
    }

    // TODO: Step 4, Implement tests for GammaCiv behavior for Archer Action Strategy and Settler Action Strategy
}
