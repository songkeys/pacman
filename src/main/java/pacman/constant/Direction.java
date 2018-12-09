package pacman.constant;

public enum Direction {
  UP,
  DOWN,
  LEFT,
  RIGHT;

  public static Direction getOpposite(Direction direction) {
    Direction oppositeDirection;
    switch (direction) {
      case UP:
        oppositeDirection = DOWN;
        break;
      case DOWN:
        oppositeDirection = UP;
        break;
      case LEFT:
        oppositeDirection = RIGHT;
        break;
      case RIGHT:
        oppositeDirection = LEFT;
        break;
      default:
        oppositeDirection = RIGHT;
    }
    return oppositeDirection;
  }
}
