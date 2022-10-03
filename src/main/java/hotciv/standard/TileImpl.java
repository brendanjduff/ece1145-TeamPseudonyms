package hotciv.standard;

import hotciv.framework.Tile;

public class TileImpl implements Tile {

  String landType;

  public TileImpl(String landType) {
    this.landType = landType;
  }

  @Override
  public String getTypeString() {
    return landType;
  }
}
