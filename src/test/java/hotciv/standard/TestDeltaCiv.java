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

}
