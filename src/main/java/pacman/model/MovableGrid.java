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

  public MovableGrid(double x, double y) {
    super(x, y);

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
              MovableGrid.this.handleMoveRight(MovableGrid.this.getX(), MovableGrid.this.getY());
            }
            break;
          case LEFT:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.LEFT, obstacles)) {
              MovableGrid.this.setX(MovableGrid.this.getX() - MapConfig.STEP);
              MovableGrid.this.handleMoveLeft(MovableGrid.this.getX(), MovableGrid.this.getY());
            }
            break;
          case UP:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.UP, obstacles)) {
              MovableGrid.this.setY(MovableGrid.this.getY() - MapConfig.STEP);
              MovableGrid.this.handleMoveUp(MovableGrid.this.getX(), MovableGrid.this.getY());
            }
            break;
          case DOWN:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.DOWN, obstacles)) {
              MovableGrid.this.setY(MovableGrid.this.getY() + MapConfig.STEP);
              MovableGrid.this.handleMoveDown(MovableGrid.this.getX(), MovableGrid.this.getY());
            }
            break;
        }

        MovableGrid.this.handleMove(MovableGrid.this.getX(), MovableGrid.this.getY());
      }
    };
  }

  private boolean isGoingToTouchGrids(Direction direction, Set<Grid> grids, double padding) {
    // generate a mock pacman at the next step
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
    Grid nextPositionGrid = new Grid(nextX / MapConfig.GRID_LENGTH, nextY / MapConfig.GRID_LENGTH);

    // check if the mock pacman overlaps any obstacle
    for (Grid grid : grids) {
      if (grid.isTouching(nextPositionGrid, padding)) {
        return true;
      }
    }

    return false;
  }

  private boolean isGoingToTouchGrids(Direction direction, Set<Grid> grids) {
    return this.isGoingToTouchGrids(direction, grids, 0);
  }

  public void handleMove(double x, double y) {};

  public void handleMoveRight(double x, double y) {};

  public void handleMoveLeft(double x, double y) {};

  public void handleMoveUp(double x, double y) {};

  public void handleMoveDown(double x, double y) {};
}
