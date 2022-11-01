package hotciv.framework;

public interface MutableUnit extends Unit {

  void decrementMoveCount();

  void resetMoveCount();

  boolean isMovable();

  boolean isFlying();

  void setMovable(boolean value);

  void setDefensiveStrength(int value);
}
