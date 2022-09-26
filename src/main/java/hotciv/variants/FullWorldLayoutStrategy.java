package hotciv.variants;

import hotciv.common.WorldLayoutStrategy;
import hotciv.framework.City;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

import java.util.HashMap;

public class FullWorldLayoutStrategy implements WorldLayoutStrategy {
    // TODO: Step 5, Implement Behavior for DeltaCiv
    @Override
    public HashMap<Position, Tile> placeTiles() {
        return null;
    }

    @Override
    public HashMap<Position, City> placeCities() {
        return null;
    }

    @Override
    public HashMap<Position, Unit> placeUnits() {
        return null;
    }
}
