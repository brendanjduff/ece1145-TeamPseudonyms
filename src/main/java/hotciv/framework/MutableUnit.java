package hotciv.framework;

public interface MutableUnit extends Unit {

  void decrementMoveCount();

  void resetMoveCount();

  boolean isMovable();

  boolean isFlying();

  void setMovable(boolean movable);

  void setDefensiveStrength(int defensiveStrength);
}
