package hotciv.standard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.utility.Utility;
import hotciv.variants.LinearAgingStrategy;
import hotciv.variants.NoArcherActionStrategy;
import hotciv.variants.NoSettlerActionStrategy;
import hotciv.variants.RedWinsAt3000BCVictoryStrategy;
import hotciv.variants.SparseWorldLayoutStrategy;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

/* Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

public class TestAlphaCiv {

  private Game game;

  /**
   * Fixture for AlphaCiv testing.
   */
  @Before
  public void setUp() {
    game = new GameImpl(new RedWinsAt3000BCVictoryStrategy(),
        new LinearAgingStrategy(),
        new NoArcherActionStrategy(),
        new NoSettlerActionStrategy(),
        new SparseWorldLayoutStrategy());
  }

  @Test
  public void shouldBeRedAsStartingPlayer() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldBeBlueAsSecondPlayer() {
    assertThat(game, is(notNullValue()));
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.BLUE));
  }

  @Test
  public void shouldBeRedOnTurnThree() {
    assertThat(game, is(notNullValue()));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getPlayerInTurn(), is(Player.RED));
  }

  @Test
  public void shouldStartAt4000BC() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getAge(), is(-4000));
  }

  @Test
  public void shouldAge100YearsEachRound() {
    assertThat(game, is(notNullValue()));
    for (int i = -4000; i <= -3100; i += 100) {
      assertThat(game.getAge(), is(i));
      game.endOfTurn();
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(-3000));
  }

  @Test
  public void redShouldWinIn3000BC() {
    assertThat(game, is(notNullValue()));
    for (int i = 0; i < (2 * 10); i++) {
      assertThat(game.getWinner(), is(nullValue()));
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(-3000));
    assertThat(game.getWinner(), is(Player.RED));
  }

  @Test
  public void tilesCorrectlyPlaced() {
    assertThat(game, is(notNullValue()));
    HashMap<Position, TileImpl> tiles = new SparseWorldLayoutStrategy().placeTiles();
    for (Position p : Utility.getWorldLayoutIterable()) {
      assertThat(game.getTileAt(p), samePropertyValuesAs(tiles.get(p)));
    }
  }

  @Test
  public void citiesCorrectlyPlaced() {
    assertThat(game, is(notNullValue()));
    HashMap<Position, CityImpl> cities = new SparseWorldLayoutStrategy().placeCities();
    for (Position p : Utility.getWorldLayoutIterable()) {
      if (cities.containsKey(p)) {
        assertThat(game.getCityAt(p), is(notNullValue()));
        assertThat(game.getCityAt(p), samePropertyValuesAs(cities.get(p)));
      } else {
        assertThat(game.getCityAt(p), is(nullValue()));
      }
    }
  }

  @Test
  public void unitsCorrectlyPlaced() {
    assertThat(game, is(notNullValue()));
    HashMap<Position, UnitImpl> units = new SparseWorldLayoutStrategy().placeUnits();
    for (Position p : Utility.getWorldLayoutIterable()) {
      if (units.containsKey(p)) {
        assertThat(game.getUnitAt(p), is(notNullValue()));
        assertThat(game.getUnitAt(p), samePropertyValuesAs(units.get(p)));
      } else {
        assertThat(game.getUnitAt(p), is(nullValue()));
      }
    }
  }

  @Test
  public void productionAtCityR1C1IsArcher() {
    assertThat(game, is(notNullValue()));
    game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
    assertThat(game.getCityAt(new Position(1, 1)).getProduction(), is(GameConstants.ARCHER));
  }

  @Test
  public void redCityShouldProduceUnitsOnUnoccupiedTilesInClockwiseDirection() {
    assertThat(game, is(notNullValue()));
    game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(1, 1)).getTypeString(), is(GameConstants.ARCHER));
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(0, 1)).getTypeString(), is(GameConstants.ARCHER));
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(0, 2)).getTypeString(), is(GameConstants.ARCHER));
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(1, 2)).getTypeString(), is(GameConstants.ARCHER));
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(2, 1)).getTypeString(), is(GameConstants.ARCHER));
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(0, 0)).getTypeString(), is(GameConstants.ARCHER));
  }

  @Test
  public void blueCityShouldProduceUnitsOnUnoccupiedTilesInClockwiseDirection() {
    assertThat(game, is(notNullValue()));
    game.changeProductionInCityAt(new Position(4, 1), GameConstants.LEGION);
    for (int i = 0; i < 6; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(4, 1)).getTypeString(), is(GameConstants.LEGION));
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(3, 1)).getTypeString(), is(GameConstants.LEGION));
    for (int i = 0; i < 6; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(4, 2)).getTypeString(), is(GameConstants.LEGION));
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(5, 2)).getTypeString(), is(GameConstants.LEGION));
    for (int i = 0; i < 6; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(5, 1)).getTypeString(), is(GameConstants.LEGION));
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(5, 0)).getTypeString(), is(GameConstants.LEGION));
    for (int i = 0; i < 6; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(4, 0)).getTypeString(), is(GameConstants.LEGION));
    for (int i = 0; i < 4; i++) {
      game.endOfTurn();
    }
    assertThat(game.getUnitAt(new Position(3, 0)).getTypeString(), is(GameConstants.LEGION));
  }

  @Test
  public void attackingUnitAlwaysWins() {
    assertThat(game, is(notNullValue()));
    Unit attacker = new UnitImpl(GameConstants.ARCHER, Player.RED);
    Unit defender = new UnitImpl(GameConstants.SETTLER, Player.BLUE);
    assertThat(((GameImpl) game).battle(attacker, defender), is(true));
  }

  @Test
  public void unitMustHaveMovementToMove() {
    assertThat(game, is(notNullValue()));
    ((UnitImpl) game.getUnitAt(new Position(2, 0))).setMoveCount(0);
    assertThat(game.moveUnit(new Position(2, 0), new Position(2, 1)), is(false));
  }


  @Test
  public void unitCannotMoveTwiceInOneTurn() {
    assertThat(game, is(notNullValue()));
    game.moveUnit(new Position(2, 0), new Position(2, 1));
    assertThat(game.getUnitAt(new Position(2, 1)).getTypeString(), is(GameConstants.ARCHER));
    assertThat(game.getUnitAt(new Position(2, 1)).getMoveCount(), is(0));
    assertThat(game.moveUnit(new Position(2, 1), new Position(3, 1)), is(false));
  }

  @Test
  public void unitCannotMoveFurtherThanMoveCount() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2, 0), new Position(4, 0)), is(false));
  }

  @Test
  public void cannotMoveIntoAllyUnit() {
    assertThat(game, is(notNullValue()));
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
    }
    assertThat(game.moveUnit(new Position(2, 0), new Position(2, 1)), is(true));
    assertThat(game.moveUnit(new Position(4, 3), new Position(3, 3)), is(true));
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
    }
    assertThat(game.moveUnit(new Position(2, 1), new Position(1, 1)), is(true));
    assertThat(game.moveUnit(new Position(3, 3), new Position(2, 3)), is(true));
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
    }
    assertThat(game.moveUnit(new Position(1, 1), new Position(1, 2)), is(true));
    assertThat(game.moveUnit(new Position(2, 3), new Position(1, 3)), is(true));
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
    }
    assertThat(game.moveUnit(new Position(1, 2), new Position(1, 3)), is(false));
    assertThat(game.moveUnit(new Position(1, 3), new Position(1, 2)), is(false));
  }

  @Test
  public void attackerMovesOnBattleWin() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2, 0), new Position(2, 1)), is(true));
    game.endOfTurn();
    assertThat(game.moveUnit(new Position(3, 2), new Position(2, 1)), is(true));
    assertThat(game.getUnitAt(new Position(2, 1)).getOwner(), is(Player.BLUE));
  }

  @Test
  public void takeOverOpponentsCityByMovingIntoIt() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2, 0), new Position(3, 1)), is(true));
    for (int i = 0; i < 2; i++) {
      game.endOfTurn();
    }
    assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.BLUE));
    assertThat(game.moveUnit(new Position(3, 1), new Position(4, 1)), is(true));
    assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.RED));
  }

  @Test
  public void moveIntoOwnCity() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2, 0), new Position(1, 1)), is(true));
  }

  @Test
  public void cannotMoveOpponentsUnit() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(3, 2), new Position(3, 1)), is(false));
  }

  @Test
  public void unitCannotMoveOntoOceanOrMountain() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2, 0), new Position(1, 0)), is(false));
    game.moveUnit(new Position(2, 0), new Position(2, 1));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getUnitAt(new Position(2, 1)).getMoveCount(), is(1));
    assertThat(game.moveUnit(new Position(2, 1), new Position(2, 2)), is(false));
  }

  @Test
  public void performNoSettlerActionOnR4C3() {
    assertThat(game, is(notNullValue()));
    game.performUnitActionAt(new Position(4, 3));
    assertThat(game.getUnitAt(new Position(4, 3)).getOwner(), is(Player.RED));  //no change
  }

  @Test
  public void performNoArcherActionOnR2C0() {
    assertThat(game, is(notNullValue()));
    game.performUnitActionAt(new Position(2, 0));
    assertThat(((UnitImpl) game.getUnitAt(new Position(2, 0))).isFortified(),
        is(false));  //no change
  }
}
