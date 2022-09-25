package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

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

  /** Fixture for alphaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl();
  }

  // FRS p. 455 states that 'Red is the first player to take a turn'.
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
    assertThat(game.getAge(), is(GameConstants.STARTING_YEAR));
  }

  @Test
  public void shouldAge100YearsEachRound() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getAge(), is(GameConstants.STARTING_YEAR));
    game.endOfTurn();
    game.endOfTurn();
    assertThat(game.getAge(), is(GameConstants.STARTING_YEAR+100));
  }

  @Test
  public void redShouldWinIn3000BC() {
    assertThat(game, is(notNullValue()));
    for(int i = 0; i < GameConstants.NUM_PLAYERS * 10; i++){
      game.endOfTurn();
    }
    assertThat(game.getAge(), is(GameConstants.LAST_YEAR));
    assertThat(game.getWinner(),is(Player.RED));
  }

  @Test
  public void positionR1C0ShouldReturnOcean() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getTileAt(new Position(1,0)).getTypeString(), is(GameConstants.OCEANS));
  }

  @Test
  public void positionR0C1ShouldReturnHills() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getTileAt(new Position(0,1)).getTypeString(), is(GameConstants.HILLS));
  }

  @Test
  public void positionR2C2ShouldReturnMountains() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getTileAt(new Position(2,2)).getTypeString(), is(GameConstants.MOUNTAINS));
  }

  @Test
  public void allOtherPositionsShouldReturnPlains() {
    assertThat(game, is(notNullValue()));
    for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
        if (!(r == 1 && c == 0) && !(r == 0 && c == 1) && !(r == 2 && c == 2)) {
          assertThat(game.getTileAt(new Position(r,c)).getTypeString(), is(GameConstants.PLAINS));
        }
      }
    }
  }

  @Test
  public void cityAtR1C1BelongsToRed() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getCityAt(new Position(1,1)).getOwner(),is(Player.RED));
  }

  @Test
  public void cityAtR4C1BelongsToBlue() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getCityAt(new Position(4,1)).getOwner(),is(Player.BLUE));
  }

  @Test
  public void unitAtR2C0IsArcherBelongsToRed() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getUnitAt(new Position(2,0)).getOwner(),is(Player.RED));
    assertThat(game.getUnitAt(new Position(2,0)).getTypeString(),is(GameConstants.ARCHER));
  }

  @Test
  public void unitAtR3C2IsLegionBelongsToBlue() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getUnitAt(new Position(3,2)).getOwner(),is(Player.BLUE));
    assertThat(game.getUnitAt(new Position(3,2)).getTypeString(),is(GameConstants.LEGION));
  }

  @Test
  public void unitAtR4C3IsSettlerBelongsToRed() {
    assertThat(game, is(notNullValue()));
    assertThat(game.getUnitAt(new Position(4,3)).getOwner(),is(Player.RED));
    assertThat(game.getUnitAt(new Position(4,3)).getTypeString(),is(GameConstants.SETTLER));
  }

  @Test
  public void productionAtCityR1C1IsArcher() {
    assertThat(game, is(notNullValue()));
    game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER);
    assertThat(game.getCityAt(new Position(1,1)).getProduction(), is(GameConstants.ARCHER));
  }

  @Test
  public void attackingUnitAlwaysWins() {
    assertThat(game, is(notNullValue()));
    Unit attacker = new UnitImpl(GameConstants.ARCHER, Player.RED);
    Unit defender = new UnitImpl(GameConstants.SETTLER, Player.BLUE);
    assertThat(game.battle(attacker,defender),is(true));
  }

  @Test
  public void unitMustHaveMovementToMove() {
    assertThat(game, is(notNullValue()));
    assertThat(game.moveUnit(new Position(2,0), new Position(2,1)), is(false));
  }
}
