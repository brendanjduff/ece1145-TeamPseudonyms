package hotciv.standard;

import hotciv.framework.MutableTile;

public class TileImpl implements MutableTile {

  String landType;

  public TileImpl(String landType) {
    this.landType = landType;
  }

  @Override
  public String getTypeString() {
    return landType;
  }

  @Override
  public void setTypeString(String type) {
    landType = type;
  }
}
