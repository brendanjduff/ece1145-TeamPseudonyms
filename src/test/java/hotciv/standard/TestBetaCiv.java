package hotciv.standard;

import hotciv.framework.*;

import hotciv.variants.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TestBetaCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl();
        game.setVictoryStrategy(new ConquerAllCitiesVictoryStrategy());
        game.setAgingStrategy(new ProgressiveAgingStrategy());
        game.setArcherActionStrategy(new NoArcherActionStrategy());
        game.setSettlerActionStrategy(new NoSettlerActionStrategy());
        game.setWorldLayoutStrategy(new SparseWorldLayoutStrategy());
    }

    // TODO: Step 4, Implement tests for BetaCiv behavior for Victory Strategy and Aging Strategy
}
