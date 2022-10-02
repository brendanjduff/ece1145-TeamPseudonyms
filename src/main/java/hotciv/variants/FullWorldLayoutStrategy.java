package hotciv.variants;

import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class FullWorldLayoutStrategy implements WorldLayoutStrategy {
    @Override
    public HashMap<Position, Tile> placeTiles() {
        String[] layout = new String[] {
                "...ooMooooo.....",
                "..ohhoooofffoo..",
                ".oooooMooo...oo.",
                ".ooMMMoooo..oooo",
                "...ofooohhoooo..",
                ".ofoofooooohhoo.",
                "...ooo..........",
                ".ooooo.ooohooM..",
                ".ooooo.oohooof..",
                "offfoooo.offoooo",
                "oooooooo...ooooo",
                ".ooMMMoooo......",
                "..ooooooffoooo..",
                "....ooooooooo...",
                "..ooohhoo.......",
                ".....ooooooooo..", };
        HashMap<Position,Tile> tiles = new HashMap<Position,Tile>();
        String line;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                tiles.put( p, new TileImpl(type));
            }
        }
        return tiles;
    }

    @Override
    public HashMap<Position, City> placeCities() {
        HashMap<Position, City> cities = new HashMap<>();
        cities.put(new Position(8,12),new CityImpl(Player.RED));
        cities.put(new Position(4,5),new CityImpl(Player.BLUE));
        return cities;
    }

    @Override
    public HashMap<Position, Unit> placeUnits() {
        HashMap<Position, Unit> units = new java.util.HashMap<>();
        units.put(new Position(3, 8), new UnitImpl(GameConstants.ARCHER, Player.RED));
        units.put(new Position(4, 4), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        units.put(new Position(5, 5), new UnitImpl(GameConstants.SETTLER, Player.RED));
        return units;
    }
}
