package pacman.model;

import java.util.Set;
import javafx.animation.AnimationTimer;
import pacman.constant.Direction;
import pacman.constant.MapConfig;

public abstract class MovableGrid extends Grid {

  public AnimationTimer moveLeft;
  public AnimationTimer moveRight;
  public AnimationTimer moveUp;
  public AnimationTimer moveDown;

  public MovableGrid(Map map, double row, double column) {
    super(map, row, column);

    // set animation
    this.moveLeft = this.move(Direction.LEFT);
    this.moveRight = this.move(Direction.RIGHT);
    this.moveUp = this.move(Direction.UP);
    this.moveDown = this.move(Direction.DOWN);
  }

  private AnimationTimer move(Direction direction) {
    return new AnimationTimer() {
      public void handle(long currentNanoTime) {
        Set<Grid> obstacles = (Set<Grid>) (Set<?>) MovableGrid.this.getParentMap().getObstacles();

        switch (direction) {
          case RIGHT:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.RIGHT, obstacles)) {
              MovableGrid.this.setX(MovableGrid.this.getX() + MapConfig.STEP);
              MovableGrid.this.handleMove(Direction.RIGHT);
            } else {
              MovableGrid.this.handleCantMove(Direction.RIGHT);
            }
            break;
          case LEFT:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.LEFT, obstacles)) {
              MovableGrid.this.setX(MovableGrid.this.getX() - MapConfig.STEP);
              MovableGrid.this.handleMove(Direction.LEFT);
            } else {
              MovableGrid.this.handleCantMove(Direction.LEFT);
            }
            break;
          case UP:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.UP, obstacles)) {
              MovableGrid.this.setY(MovableGrid.this.getY() - MapConfig.STEP);
              MovableGrid.this.handleMove(Direction.UP);
            } else {
              MovableGrid.this.handleCantMove(Direction.UP);
            }
            break;
          case DOWN:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.DOWN, obstacles)) {
              MovableGrid.this.setY(MovableGrid.this.getY() + MapConfig.STEP);
              MovableGrid.this.handleMove(Direction.DOWN);
            } else {
              MovableGrid.this.handleCantMove(Direction.DOWN);
            }
            break;
        }
      }
    };
  }

  public boolean isGoingToTouchGrids(Direction direction, Set<Grid> grids, double padding) {
    // calculate next step based on direction
    double nextX = this.getX();
    double nextY = this.getY();
    switch (direction) {
      case RIGHT:
        nextX += MapConfig.STEP;
        break;
      case LEFT:
        nextX -= MapConfig.STEP;
        break;
      case UP:
        nextY -= MapConfig.STEP;
        break;
      case DOWN:
        nextY += MapConfig.STEP;
        break;
    }

    // check if the next step is beyond screen
    if (nextX < 0
        || nextY < 0
        || nextX + MapConfig.GRID_LENGTH > MapConfig.WIDTH
        || nextY + MapConfig.GRID_LENGTH > MapConfig.HEIGHT) {
      return true;
    }

    // generate a mock grid at the next step
    Grid nextPositionGrid =
        new Grid(getParentMap(), nextX / MapConfig.GRID_LENGTH, nextY / MapConfig.GRID_LENGTH);

    // check if the mock grid overlaps any obstacle
    for (Grid grid : grids) {
      if (grid.isTouching(nextPositionGrid, padding)) {
        return true;
      }
    }

    return false;
  }

  public boolean isGoingToTouchGrids(Direction direction, Set<Grid> grids) {
    return this.isGoingToTouchGrids(direction, grids, 0);
  }

  public void handleMove(Direction direction) {}

  public void handleCantMove(Direction direction) {}
}
