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
        game = new GameImpl(new ConquerAllCitiesVictoryStrategy(),
                new ProgressiveAgingStrategy(),
                new NoArcherActionStrategy(),
                new NoSettlerActionStrategy(),
                new SparseWorldLayoutStrategy());
    }

    @Test
    public void progAgingWorksCorrectlyInProgAgingStrategy() {
        ProgressiveAgingStrategy prog;
        assertThat(game, is(notNullValue()));
        for(int i = 0;i < 2;i++){
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(-3900));
        for(int i = 0;i < 2*38;i++){
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(-100));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(-1));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(1));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(50));
        for(int i = 0;i < 2*34;i++){
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1750));
        for(int i = 0;i < 2*6;i++){
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1900));
        for(int i = 0;i < 2*14;i++){
            game.endOfTurn();
        }
        assertThat(game.getAge(), is(1970));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(1971));
    }
}
