package hotciv.framework;

public interface MutableUnit extends Unit {
  void setMoveCount(int count);
  boolean isMoveable();
  void setMoveable(boolean moveable);
  void setDefensiveStrength(int value);
}
