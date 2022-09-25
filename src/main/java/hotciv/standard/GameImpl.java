package hotciv.standard;

import hotciv.framework.*;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Map;
import java.util.Set;

import static hotciv.framework.GameConstants.WORLDSIZE;

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

  public GameImpl() {
    playerIndex = 0;
    players[0] = Player.RED;
    players[1] = Player.BLUE;
    age = GameConstants.STARTING_YEAR;
    createWorld();

    cities = new java.util.HashMap<Position, City>();
    cities.put(new Position(1,1),new CityImpl(Player.RED));
    cities.put(new Position(4,1),new CityImpl(Player.BLUE));

    units = new java.util.HashMap<Position, Unit>();
    units.put(new Position(2, 0), new UnitImpl(GameConstants.ARCHER, Player.RED));
    units.put(new Position(3, 2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
    units.put(new Position(4, 3), new UnitImpl(GameConstants.SETTLER, Player.RED));
  }

  Player[] players = new Player[GameConstants.NUM_PLAYERS];
  int playerIndex;
  int age;
  Tile[][] tiles;
  java.util.Map<Position, City> cities;

  java.util.Map<Position, Unit> units;

  public Tile getTileAt( Position p ) { return tiles[p.getRow()][p.getColumn()]; }
  public Unit getUnitAt( Position p ) { return units.get(p); }
  public City getCityAt( Position p ) { return cities.get(p); }
  public Player getPlayerInTurn() { return players[playerIndex]; }
  public Player getWinner() {
    if(age == GameConstants.LAST_YEAR){
      return Player.RED;
    }else{
      return null;
    }
  }
  public int getAge() { return age; }

  public boolean moveUnit( Position from, Position to) {
    Unit unit = getUnitAt(from);
    if (unit.getOwner() == players[playerIndex]) {
      return false;
    } else if (to.getColumn() < 0 || to.getColumn() > GameConstants.WORLDSIZE
            || to.getRow() < 0 || to.getRow() > GameConstants.WORLDSIZE) {
      return false;
    } else if (tiles[to.getRow()][to.getColumn()].getTypeString() == GameConstants.MOUNTAINS ||
            tiles[to.getRow()][to.getColumn()].getTypeString() == GameConstants.OCEANS) {
      return false;
    } else if (Math.abs(from.getRow() - to.getRow()) > unit.getMoveCount() ||
            Math.abs(from.getColumn() - to.getColumn()) > unit.getMoveCount()) {
      return false;
    }

    if (units.containsKey(to)) {
      if(units.get(to).getOwner() != players[playerIndex]) {
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
    }

    units.put(to, unit);
    units.remove(from);

    if (cities.containsKey(to)) {
      if(cities.get(to).getOwner() != players[playerIndex]) {
        cities.get(to).setOwner(players[playerIndex]);
      }
    }
    return true;
  }
  public void endOfTurn() {
    playerIndex++;
    if(playerIndex % GameConstants.NUM_PLAYERS == 0) {
      age += 100;
      playerIndex = 0;
    }
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {
    cities.get(p).setProduction((unitType));
  }
  public void performUnitActionAt( Position p ) {}
  public boolean battle(Unit attacker, Unit defender) { return true;}

  void createWorld() {
    tiles = new Tile[WORLDSIZE][WORLDSIZE];
    for (int r = 0; r < WORLDSIZE; r++) {
      for (int c = 0; c < WORLDSIZE; c++) {
        if (r == 1 && c == 0) {
          tiles[r][c] = new TileImpl(GameConstants.OCEANS);
        } else if (r == 0 && c == 1) {
          tiles[r][c] = new TileImpl(GameConstants.HILLS);
        } else if (r == 2 && c == 2) {
          tiles[r][c] = new TileImpl(GameConstants.MOUNTAINS);
        } else {
          tiles[r][c] = new TileImpl(GameConstants.PLAINS);
        }
      }
    }
  }
}
