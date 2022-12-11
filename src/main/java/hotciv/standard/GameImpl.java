package hotciv.standard;

import hotciv.common.AgingStrategy;
import hotciv.common.BattleStrategy;
import hotciv.common.UnitActionStrategy;
import hotciv.common.VictoryStrategy;
import hotciv.common.WorkforceStrategy;
import hotciv.common.WorldLayoutStrategy;
import hotciv.factory.GameFactory;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.GameObserver;
import hotciv.framework.MutableCity;
import hotciv.framework.MutableGame;
import hotciv.framework.MutableTile;
import hotciv.framework.MutableUnit;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;
import hotciv.utility.NumberGenerator;
import hotciv.utility.RandomNumberGenerator;
import hotciv.utility.Utility;
import java.util.HashMap;
import java.util.Map;

/* Skeleton implementation of HotCiv.
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

public class GameImpl implements Game, MutableGame {

  public GameImpl(GameFactory factory) {
    gameFactory = factory;
    victoryStrategy = factory.createVictoryStrategy();
    agingStrategy = factory.createAgingStrategy();
    unitActionStrategy = factory.createUnitActionStrategy();
    worldLayoutStrategy = factory.createWorldLayoutStrategy();
    battleStrategy = factory.createBattleStrategy();
    workforceStrategy = factory.createWorkforceStrategy();
    gameObserver = new NullGameObserver();


    playerIndex = 0;
    players[0] = Player.RED;
    players[1] = Player.BLUE;
    age = -4000;
    round = 1;

    //instantiation of successful attack counter
    for (Player p : players) {
      successfulAttacks.put(p, 0);
    }

    tiles = worldLayoutStrategy.placeTiles();
    cities = worldLayoutStrategy.placeCities();
    units = worldLayoutStrategy.placeUnits();
  }

  final Player[] players = new Player[2];
  int playerIndex;
  int age;
  int round;
  final HashMap<Position, MutableTile> tiles;
  final Map<Position, MutableCity> cities;
  final Map<Position, MutableUnit> units;

  final Map<Player, Integer> successfulAttacks = new HashMap<>();

  final GameFactory gameFactory;
  final VictoryStrategy victoryStrategy;
  final AgingStrategy agingStrategy;
  final UnitActionStrategy unitActionStrategy;
  final WorldLayoutStrategy worldLayoutStrategy;
  final BattleStrategy battleStrategy;
  final WorkforceStrategy workforceStrategy;
  final NumberGenerator rng = new RandomNumberGenerator();
  GameObserver gameObserver;

  public Tile getTileAt(Position p) {
    if(tiles.containsKey(p)) { return tiles.get(p); }
    return null;
  }

  public Unit getUnitAt(Position p) {
    if(units.containsKey(p)) { return units.get(p); }
    return null;
  }

  public City getCityAt(Position p) {
    if(cities.containsKey(p)) { return cities.get(p); }
    return null;
  }

  public Player getPlayerInTurn() {
    return players[playerIndex];
  }

  public Player getWinner() {
    return victoryStrategy.getWinner(this);
  }

  public int getAge() {
    return age;
  }

  public boolean moveUnit(Position from, Position to) {
    MutableUnit unit = units.get(from);

    // Check unit ownership, terrain type, and move distance
    boolean unitBelongsToOtherPlayer = unit.getOwner() != getPlayerInTurn();
    if (unitBelongsToOtherPlayer) {
      return false;
    }
    boolean noMoves = unit.getMoveCount() <= 0;
    if (noMoves) {
      return false;
    }
    boolean moreThanOneTile = Math.abs(from.getRow() - to.getRow()) > 1
        || Math.abs(from.getColumn() - to.getColumn()) > 1;
    if (moreThanOneTile) {
      return false;
    }
    boolean impassableTerrain =
        (tiles.get(to).getTypeString().equals(GameConstants.MOUNTAINS) || tiles.get(to)
            .getTypeString().equals(GameConstants.OCEANS)) && !unit.isFlying();
    if (impassableTerrain) {
      return false;
    }

    // Check for battles and unit collision
    if (units.containsKey(to)) {
      if (units.get(to).getOwner() != getPlayerInTurn()) {
        if (battleStrategy.battle(unit, from, units.get(to), to, this)) {
          units.remove(to);
          units.put(to, unit);
          units.remove(from);
          gameObserver.worldChangedAt(from);
          gameObserver.worldChangedAt(to);
          successfulAttacks.put(getPlayerInTurn(), successfulAttacks.get(getPlayerInTurn()) + 1);
          // Actively conquer city
          if (cities.containsKey(to)) {
            cities.get(to).setOwner(getPlayerInTurn());
            gameObserver.worldChangedAt(to);
          }
        } else {
          units.remove(from);
          gameObserver.worldChangedAt(from);
          return false;
        }
      } else {
        return false;
      }
    } else {
      units.put(to, unit);
      units.remove(from);
      gameObserver.worldChangedAt(from);
      gameObserver.worldChangedAt(to);
    }
    unit.decrementMoveCount();

    // Passively conquer city
    if (cities.containsKey(to) && !unit.isFlying()) {
      cities.get(to).setOwner(getPlayerInTurn());
      gameObserver.worldChangedAt(to);
    }
    return true;
  }

  public void endOfTurn() {
    playerIndex++;
    if (playerIndex % 2 == 0) {
      playerIndex = 0;
      // Perform end of round functions
      // A) restore all units' move counts
      units.forEach((position, unit) -> unit.resetMoveCount());
      /* B) produce food and production in all cities
         C) produce units in all cities (if enough production) */
      cities.forEach((position, city) -> {
        workforceStrategy.FillTreasury(position, city, this);
        if (city.unitCostMet()) {
          boolean noUnitOnCity = !units.containsKey(position);
          if (noUnitOnCity) {
            createUnit(position, city);
          } else {
            for (Position p : Utility.get8neighborhoodOf(position)) {
              boolean canPlaceUnitHere = !units.containsKey(p) && !tiles.get(p).getTypeString()
                  .equals(GameConstants.OCEANS) && !tiles.get(p).getTypeString()
                  .equals(GameConstants.MOUNTAINS);
              if (canPlaceUnitHere) {
                createUnit(p, city);
                break;
              }
            }
          }
        }
        // D) Update population in city
        workforceStrategy.UpdatePopulation(position, city, this);
      });
      // E) increment the world age.
      age = agingStrategy.incrementAge(age);
      round++;
    }
    gameObserver.turnEnds(getPlayerInTurn(), getAge());
  }

  public void changeWorkForceFocusInCityAt(Position p, String balance) {
    cities.get(p).setWorkforceFocus(balance);
  }

  public void changeProductionInCityAt(Position p, String unitType) {
    cities.get(p).setProduction((unitType));
  }

  public void performUnitActionAt(Position p) {
    unitActionStrategy.performAction(p, this);
  }

  @Override
  public void addObserver(GameObserver gameObserver) {
    this.gameObserver = gameObserver;
  }

  @Override
  public void setTileFocus(Position position) {

    gameObserver.tileFocusChangedAt(position);
  }

  @Override
  public Map<Position, MutableTile> getTiles() {
    return tiles;
  }

  @Override
  public Map<Position, MutableCity> getCities() {
    return cities;
  }

  @Override
  public Map<Position, MutableUnit> getUnits() {
    return units;
  }

  @Override
  public Map<Player, Integer> getBattleWins() {
    return successfulAttacks;
  }

  @Override
  public NumberGenerator getRNG() {
    return rng;
  }

  @Override
  public int getRound() {
    return round;
  }

  @Override
  public GameObserver getObserver() {
    return gameObserver;
  }

  void createUnit(Position p, MutableCity city) {
    city.produceUnit();
    units.put(p, new UnitImpl(city.getProduction(), getPlayerInTurn()));
    gameObserver.worldChangedAt(p);
  }
}
