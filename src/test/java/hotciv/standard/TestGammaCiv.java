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

    // TODO: Step 4, Implement tests for GammaCiv behavior for Archer Action Strategy and Settler Action Strategy
}
