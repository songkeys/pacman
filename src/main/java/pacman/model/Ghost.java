package pacman.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import pacman.constant.Direction;
import pacman.constant.FileName;

public class Ghost extends MovableGrid implements Runnable {

  private Direction direction;
  private int timeWalked;

  public Ghost(Map map, double x, double y) {
    super(map, x, y);

    // set image
    Image image = new Image(FileName.IMAGE_PACMAN);
    this.setFill(new ImagePattern(image));

    // set initial direction
    this.direction = Direction.UP;

    run();
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

  private void moveToAnotherDirection() {
    this.direction = findDirectionToMove();
    moveUp.stop();
    moveDown.stop();
    moveLeft.stop();
    moveRight.stop();
    moveTo(this.direction);
  }

  @Override
  public void handleMove(Direction direction) {
    timeWalked++;
    if (timeWalked >= 10) {
      moveToAnotherDirection();
      timeWalked = 0;
    }
  }

  @Override
  public void handleCantMove(Direction direction) {
    moveToAnotherDirection();
  }

  @Override
  public void run() {
    moveTo(direction);
  }
}
