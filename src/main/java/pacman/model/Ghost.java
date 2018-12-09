package pacman.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import pacman.constant.Direction;
import pacman.constant.FileName;
import pacman.constant.MapConfig;

public class Ghost extends MovableGrid implements Runnable {

  private Direction direction;
  private int timeWalked;

  public Ghost(Map map, double row, double column) {
    super(map, row, column);

    this.setImage(FileName.IMAGE_PACMAN);

    // set initial direction
    this.direction = Direction.UP;
  }

  private void moveTo(Direction direction) {
    switch (direction) {
      case UP:
        moveUp.start();
        break;
      case DOWN:
        moveDown.start();
        break;
      case LEFT:
        moveLeft.start();
        break;
      case RIGHT:
        moveRight.start();
        break;
    }
  }

  private Direction findDirectionToMove() {
    // get all possible directions to go on flank
    Set<Direction> directionsToGo = new HashSet<>();
    Set<Grid> obstacles = (Set<Grid>) (Set<?>) getParentMap().getObstacles();
    switch (direction) {
      case UP:
      case DOWN:
        if (!isGoingToTouchGrids(Direction.LEFT, obstacles)) {
          directionsToGo.add(Direction.LEFT);
        }
        if (!isGoingToTouchGrids(Direction.RIGHT, obstacles)) {
          directionsToGo.add(Direction.RIGHT);
        }
        break;
      case LEFT:
      case RIGHT:
        if (!isGoingToTouchGrids(Direction.UP, obstacles)) {
          directionsToGo.add(Direction.UP);
        }
        if (!isGoingToTouchGrids(Direction.DOWN, obstacles)) {
          directionsToGo.add(Direction.DOWN);
        }
        break;
    }

    // check if there is no directions to go but the opposite one
    if (directionsToGo.size() == 0 && isGoingToTouchGrids(direction, obstacles)) {
      return Direction.getOpposite(direction);
    } else {
      //  pick a direction randomly
      Direction directionToGo = direction;
      int i = 0;
      Boolean randBool = new Random().nextBoolean();
      int j = randBool ? 1 : 0;
      for (Direction direction : directionsToGo) {
        if (i == j) {
          directionToGo = direction;
        }
        i++;
      }
      return directionToGo;
    }
  }

  private void moveToAnotherDirection() {
    direction = findDirectionToMove();
    freeze();
    moveTo(direction);
  }

  private void checkTouchingPacman() {
    if (getParentMap().getPacman().isTouching(this, MapConfig.GHOST_PADDING)) {
      getParentMap().getParentGameManager().handleGhostTouched();
    }
  }

  @Override
  public void handleMove(Direction direction) {
    timeWalked++;
    if (timeWalked >= 10) {
      moveToAnotherDirection();
      timeWalked = 0;
    }
    checkTouchingPacman();
  }

  @Override
  public void handleCantMove(Direction direction) {
    moveToAnotherDirection();
  }

  @Override
  public void run() {
    moveTo(direction);
  }

  public void freeze() {
    moveUp.stop();
    moveDown.stop();
    moveLeft.stop();
    moveRight.stop();
  }
}
