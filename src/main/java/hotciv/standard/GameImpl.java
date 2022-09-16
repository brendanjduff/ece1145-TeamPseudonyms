package hotciv.standard;

import hotciv.framework.*;

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
    age = STARTING_YEAR;
    createWorld();
  }

  public static final int STARTING_YEAR = -4000;
  public static final int NUM_PLAYERS = 2;
  Player[] players = new Player[NUM_PLAYERS];
  int playerIndex;
  int age;
  Tile[][] tiles;

  public Tile getTileAt( Position p ) { return tiles[p.getColumn()][p.getRow()]; }
  public Unit getUnitAt( Position p ) { return null; }
  public City getCityAt( Position p ) { return null; }
  public Player getPlayerInTurn() { return players[playerIndex]; }
  public Player getWinner() {
    if(age == -3000){
      return Player.RED;
    }else{
      return null;
    }
  }
  public int getAge() { return age; }
  public boolean moveUnit( Position from, Position to ) {
    return false;
  }
  public void endOfTurn() {
    playerIndex++;
    if(playerIndex % NUM_PLAYERS == 0) {
      age += 100;
      playerIndex = 0;
    }
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}

  void createWorld() {
    tiles = new Tile[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
    for(int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for(int c = 0; c < GameConstants.WORLDSIZE; c++) {
        if(r == 0 && c == 1) {
          tiles[r][c] = new TileImpl(GameConstants.OCEANS);
        }
        else if (r == 1 && c == 0) {
          tiles[r][c] = new TileImpl(GameConstants.HILLS);
        }
        else if (r == 2 && c == 2) {
          tiles[r][c] = new TileImpl(GameConstants.MOUNTAINS);
        }
        else {
          tiles[r][c] = new TileImpl(GameConstants.PLAINS);
        }
      }
    }
  }
}
