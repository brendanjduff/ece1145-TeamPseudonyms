package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import hotciv.factory.EtaCivFactory;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.MutableGame;
import hotciv.framework.Position;
import org.junit.Before;
import org.junit.Test;

public class TestEtaCiv {

  private MutableGame game;

  @Before
  public void setUp() {
    game = new GameImpl(new EtaCivFactory());
  }

  @Test
  public void workforceFocusOfCityAtR1C1IsProduction() {
    assertThat(game, is(notNullValue()));
    game.changeWorkForceFocusInCityAt(new Position(1, 1), GameConstants.productionFocus);
    assertThat(game.getCityAt(new Position(1, 1)).getWorkforceFocus(), is(GameConstants.productionFocus));
  }

  @Test
  public void workforceFocusOfCityAtR1C1IsFood() {
    assertThat(game, is(notNullValue()));
    game.changeWorkForceFocusInCityAt(new Position(1, 1), GameConstants.foodFocus);
    assertThat(game.getCityAt(new Position(1, 1)).getWorkforceFocus(), is(GameConstants.foodFocus));
  }

  @Test
  public void cityGrowthUnderProductionFocus() {
    assertThat(game, is(notNullValue()));
    game.getTiles().get(new Position(0,0)).setTypeString(GameConstants.FOREST);
    game.getCities().get(new Position(1,1)).setWorkforceFocus(GameConstants.productionFocus);

    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(1));
    for(int i = 0; i < 7*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(7));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(7));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(1));
    for(int i = 0; i < 1*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(8));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(2));
    for(int i = 0; i < 10*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(48));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(10));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(2));
    for(int i = 0; i < 1*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(52));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(3));
    for(int i = 0; i < 14*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(136));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(4));
    for(int i = 0; i < 17*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(255));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(5));
    for(int i = 0; i < 5*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(290));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(6));
    for(int i = 0; i < 4*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(318));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(7));
    for(int i = 0; i < 3*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(339));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(8));
    for(int i = 0; i < 3*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(360));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(9));
    for(int i = 0; i < 1*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(367));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(14));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(9));
  }

  @Test
  public void cityGrowthUnderFoodFocus() {
    assertThat(game, is(notNullValue()));
    game.getTiles().get(new Position(0,0)).setTypeString(GameConstants.FOREST);
    game.getCities().get(new Position(1,1)).setWorkforceFocus(GameConstants.foodFocus);

    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(1));
    for(int i = 0; i < 7*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(7));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(7));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(1));
    for(int i = 0; i < 1*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(8));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(2));
    for(int i = 0; i < 2*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(10));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(8));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(2));
    for(int i = 0; i < 1*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(11));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(3));
    for(int i = 0; i < 2*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(13));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(4));
    for(int i = 0; i < 2*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(15));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(5));
    for(int i = 0; i < 2*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(17));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(6));
    for(int i = 0; i < 2*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(19));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(7));
    for(int i = 0; i < 2*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(27));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(8));
    for(int i = 0; i < 3*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(45));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(0));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(9));
    for(int i = 0; i < 1*2; i++) { game.endOfTurn(); }
    assertThat(game.getCities().get(new Position(1,1)).getTreasury(), is(52));
    assertThat(game.getCities().get(new Position(1,1)).getFood(), is(14));
    assertThat(game.getCities().get(new Position(1,1)).getSize(), is(9));
  }

}
