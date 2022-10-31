package hotciv.framework;

public interface MutableUnit extends Unit {

  void setMoveCount(int count);

  boolean isMovable();

  void setMovable(boolean value);

  void setDefensiveStrength(int value);
}
