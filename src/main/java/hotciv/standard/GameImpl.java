package hotciv.standard;

import hotciv.common.AgingStrategy;
import hotciv.common.BattleStrategy;
import hotciv.common.UnitActionStrategy;
import hotciv.common.VictoryStrategy;
import hotciv.common.WorldLayoutStrategy;
import hotciv.factory.GameFactory;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.MutableCity;
import hotciv.framework.MutableGame;
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

    playerIndex = 0;
    players[0] = Player.RED;
    players[1] = Player.BLUE;
    age = -4000;
    round = 1;

    //instantiation of successful attack counter
    for (int i = 0; i < players.length; i++) {
      successfulAttacks.put(players[i], 0);
    }

    tiles = worldLayoutStrategy.placeTiles();
    cities = worldLayoutStrategy.placeCities();
    units = worldLayoutStrategy.placeUnits();
  }

  Player[] players = new Player[2];
  int playerIndex;
  int age;
  int round;
  HashMap<Position, Tile> tiles;
  Map<Position, MutableCity> cities;
  Map<Position, MutableUnit> units;

  Map<Player, Integer> successfulAttacks = new HashMap<>();

  GameFactory gameFactory;
  VictoryStrategy victoryStrategy;
  AgingStrategy agingStrategy;
  UnitActionStrategy unitActionStrategy;
  WorldLayoutStrategy worldLayoutStrategy;
  BattleStrategy battleStrategy;
  NumberGenerator rng = new RandomNumberGenerator();

  public Tile getTileAt(Position p) {
    return tiles.get(p);
  }

  public Unit getUnitAt(Position p) {
    return units.get(p);
  }

  public City getCityAt(Position p) {
    return cities.get(p);
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
    boolean notEnoughMoves = Math.abs(from.getRow() - to.getRow()) > unit.getMoveCount()
        || Math.abs(from.getColumn() - to.getColumn()) > unit.getMoveCount();
    if (notEnoughMoves) {
      return false;
    }
    boolean isTileMountainsOrOceans =
        tiles.get(to).getTypeString().equals(GameConstants.MOUNTAINS) || tiles.get(to)
            .getTypeString().equals(GameConstants.OCEANS);
    if (isTileMountainsOrOceans) {
      return false;
    }

    // Check for battles and unit collision
    if (units.containsKey(to)) {
      if (units.get(to).getOwner() != getPlayerInTurn()) {
        if (battleStrategy.battle(unit, from, units.get(to), to, this)) {
          units.remove(to);
          units.put(to, unit);
          units.remove(from);
          successfulAttacks.put(getPlayerInTurn(), successfulAttacks.get(getPlayerInTurn()) + 1);
        } else {
          units.remove(from);
          return false;
        }
      } else {
        return false;
      }
    } else {
      units.put(to, unit);
      units.remove(from);
    }
    unit.setMoveCount(0);

    // Check for city takeover
    if (cities.containsKey(to)) {
      if (cities.get(to).getOwner() != getPlayerInTurn()) {
        cities.get(to).setOwner(getPlayerInTurn());
      }
    }
    return true;
  }

  public void endOfTurn() {
    playerIndex++;
    if (playerIndex % 2 == 0) {
      playerIndex = 0;
      // Perform end of round functions
      // A) restore all units' move counts
      units.forEach((position, unit) -> {
        unit.setMoveCount(1);
      });
      /* B) produce food and production in all cities
         C) produce units in all cities (if enough production) */
      cities.forEach((position, city) -> {
        city.fillTreasury();
        if (city.unitCostMet()) {
          boolean noUnitOnCity = !units.containsKey(position);
          if (noUnitOnCity) {
            city.produceUnit();
            units.put(position, new UnitImpl(city.getProduction(), getPlayerInTurn()));
          } else {
            for (Position p : Utility.get8neighborhoodOf(position)) {
              boolean canPlaceUnitHere = !units.containsKey(p) &&
                  !tiles.get(p).getTypeString().equals(GameConstants.OCEANS) &&
                  !tiles.get(p).getTypeString().equals(GameConstants.MOUNTAINS);
              if (canPlaceUnitHere) {
                city.produceUnit();
                units.put(p, new UnitImpl(city.getProduction(), getPlayerInTurn()));
                break;
              }
            }
          }
        }
      });
      // E) increment the world age.
      age = agingStrategy.incrementAge(age);
      round++;
    }
  }

  public void changeWorkForceFocusInCityAt(Position p, String balance) {
  }

  public void changeProductionInCityAt(Position p, String unitType) {
    cities.get(p).setProduction((unitType));
  }

  public void performUnitActionAt(Position p) {
    unitActionStrategy.performAction(p, this);
  }

  @Override
  public Map<Position, Tile> getTiles() {
    return (Map<Position, Tile>) tiles;
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
}
