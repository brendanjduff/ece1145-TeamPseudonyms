package hotciv.variants;

import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class SparseWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public HashMap<Position, Tile> placeTiles() {
        HashMap<Position, Tile> tiles = new HashMap<Position, Tile>();
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
        return tiles;
    }

    @Override
    public HashMap<Position, City> placeCities() {
        HashMap<Position, City> cities = new HashMap<>();
        cities.put(new Position(1,1),new CityImpl(Player.RED));
        cities.put(new Position(4,1),new CityImpl(Player.BLUE));
        return cities;
    }

    @Override
    public HashMap<Position, Unit> placeUnits() {
        HashMap<Position, Unit> units = new java.util.HashMap<>();
        units.put(new Position(2, 0), new UnitImpl(GameConstants.ARCHER, Player.RED));
        units.put(new Position(3, 2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        units.put(new Position(4, 3), new UnitImpl(GameConstants.SETTLER, Player.RED));
        return units;
    }
}
