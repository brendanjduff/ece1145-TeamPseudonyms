package hotciv.standard;

import hotciv.framework.*;
import hotciv.common.*;
import hotciv.utility.Utility;

import java.util.HashMap;

/** Skeleton implementation of HotCiv.

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

public class GameImpl implements Game {

  public GameImpl(VictoryStrategy victory,
                  AgingStrategy aging,
                  ArcherActionStrategy archerAction,
                  SettlerActionStrategy settlerAction,
                  WorldLayoutStrategy worldLayout) {
    victoryStrategy = victory;
    agingStrategy = aging;
    archerActionStrategy = archerAction;
    settlerActionStrategy = settlerAction;
    worldLayoutStrategy = worldLayout;

    playerIndex = 0;
    players[0] = Player.RED;
    players[1] = Player.BLUE;
    age = -4000;

    defineWorld();

    cities = new java.util.HashMap<>();
    cities.put(new Position(1,1),new CityImpl(Player.RED));
    cities.put(new Position(4,1),new CityImpl(Player.BLUE));

    units = new java.util.HashMap<>();
    units.put(new Position(2, 0), new UnitImpl(GameConstants.ARCHER, Player.RED));
    units.put(new Position(3, 2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
    units.put(new Position(4, 3), new UnitImpl(GameConstants.SETTLER, Player.RED));
  }

  Player[] players = new Player[2];
  int playerIndex;
  int age;
  java.util.Map<Position, Tile> tiles;
  java.util.Map<Position, City> cities;
  java.util.Map<Position, Unit> units;

  // Strategies
  VictoryStrategy victoryStrategy;
  AgingStrategy agingStrategy;
  ArcherActionStrategy archerActionStrategy;
  SettlerActionStrategy settlerActionStrategy;
  WorldLayoutStrategy worldLayoutStrategy;

  public Tile getTileAt( Position p ) { return tiles.get(p); }
  public Unit getUnitAt( Position p ) { return units.get(p); }
  public City getCityAt( Position p ) { return cities.get(p); }
  public Player getPlayerInTurn() { return players[playerIndex]; }
  public Player getWinner() {
    if(age == -3000){
      return Player.RED;
    }else{
      return null;
    }
  }
  public int getAge() { return age; }

  public boolean moveUnit( Position from, Position to) {
    Unit unit = getUnitAt(from);
    // Check unit ownership, terrain type, and move distance
    if (unit.getOwner() != getPlayerInTurn()) {
      return false;
    } else if (tiles.get(to).getTypeString() == GameConstants.MOUNTAINS ||
              tiles.get(to).getTypeString() == GameConstants.OCEANS) {
        return false;
    } else if (Math.abs(from.getRow() - to.getRow()) > unit.getMoveCount() ||
            Math.abs(from.getColumn() - to.getColumn()) > unit.getMoveCount()) {
      return false;
    }

    // Check for battles and unit collision
    if (units.containsKey(to)) {
      if(units.get(to).getOwner() != getPlayerInTurn()) {
        if(battle(unit, units.get(to))) {
          units.remove(to);
          units.put(to, unit);
          units.remove(from);
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
      if(cities.get(to).getOwner() != getPlayerInTurn()) {
        cities.get(to).setOwner(getPlayerInTurn());
      }
    }
    return true;
  }
  public void endOfTurn() {
    playerIndex++;
    if(playerIndex % 2 == 0) {
      playerIndex = 0;
      // Perform end of round functions
      // A) restore all units' move counts
      units.forEach((position, unit) -> { unit.setMoveCount(1); });
      // B) produce food and production in all cities
      // C) produce units in all cities (if enough production)
      cities.forEach((position, city) -> {
        if(city.endOfTurnProduction()) {
          if (!units.containsKey(position)) {
            units.put(position, new UnitImpl(city.getProduction(), getPlayerInTurn()));
          } else {
            for (Position p : Utility.get8neighborhoodOf(position)) {
              if (!units.containsKey(p) &&
                      tiles.get(p).getTypeString() != GameConstants.OCEANS &&
                      tiles.get(p).getTypeString() != GameConstants.MOUNTAINS) {
                units.put(p, new UnitImpl(city.getProduction(), getPlayerInTurn()));
                break;
              }
            }
          }
        }
      });
      // E) increment the world age.
      age += 100;
    }
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {
    cities.get(p).setProduction((unitType));
  }
  // TODO: Step 3, Add behavior from archerActionStrategy and SettlerActionStrategy
  public void performUnitActionAt( Position p ) {}
  public boolean battle(Unit attacker, Unit defender) { return true;}

  void defineWorld() {
    tiles = new HashMap<Position, Tile>();
    for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
        if (r == 1 && c == 0) {
          tiles.put(new Position(r,c), new TileImpl(GameConstants.OCEANS));
        } else if (r == 0 && c == 1) {
          tiles.put(new Position(r,c), new TileImpl(GameConstants.HILLS));
        } else if (r == 2 && c == 2) {
          tiles.put(new Position(r,c), new TileImpl(GameConstants.MOUNTAINS));
        } else {
          tiles.put(new Position(r,c), new TileImpl(GameConstants.PLAINS));
        }
      }
    }
  }
}
