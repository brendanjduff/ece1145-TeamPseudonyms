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
        game = new GameImpl();
        game.setVictoryStrategy(new RedWinsAt3000BCVictoryStrategy());
        game.setAgingStrategy(new LinearAgingStrategy());
        game.setArcherActionStrategy(new NoArcherActionStrategy());
        game.setSettlerActionStrategy(new NoSettlerActionStrategy());
        game.setWorldLayoutStrategy(new FullWorldLayoutStrategy());
    }

    // TODO: Step 4, Implement tests for DeltaCiv behavior for World Layout
}
