package pacman.model;

import java.util.Set;
import javafx.animation.AnimationTimer;
import pacman.constant.Direction;
import pacman.constant.MovableGridType;

/**
 *
 *
 * <h1>MovableGrid</h1>
 *
 * <p>A {@link MovableGrid} is a {@link Grid} located in a {@link Map} with the ability to move.
 *
 * <p>This class extends {@link Grid}.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see Grid
 * @see Ghost
 * @see Pacman
 * @see Map
 * @see MovableGridType
 * @see Direction
 */
public abstract class MovableGrid extends Grid {

  /** The {@link AnimationTimer} instance for moving left */
  public AnimationTimer moveLeft;
  /** The {@link AnimationTimer} instance for moving right */
  public AnimationTimer moveRight;
  /** The {@link AnimationTimer} instance for moving up */
  public AnimationTimer moveUp;
  /** The {@link AnimationTimer} instance for moving down */
  public AnimationTimer moveDown;
  /** The step to go every time moving happens, in px */
  private double step;

  /**
   * Allocates a new {@link MovableGrid} object.
   *
   * <p>This constructor sets the {@link MovableGrid} in the given position of the given {@link
   * Map}, and sets a random image of {@link MovableGrid}, and gives a initial {@link Direction#UP}
   * to move to.
   *
   * @param map the {@link Map} where this {@link MovableGrid} stays
   * @param row the row index in the {@link Map} where this {@link MovableGrid} stays, starting from
   *     0
   * @param column the column index in the {@link Map} where this {@link MovableGrid} stays,
   *     starting from 0
   * @param movableGridType the {@link MovableGridType} of this {@link MovableGrid}; possible
   *     values: {@link MovableGridType#GHOST} {@link MovableGridType#PACMAN}
   */
  MovableGrid(Map map, double row, double column, MovableGridType movableGridType) {
    super(map, row, column);

    // set step rate according to type
    initConfig(movableGridType);

    // set animation
    this.moveLeft = this.move(Direction.LEFT);
    this.moveRight = this.move(Direction.RIGHT);
    this.moveUp = this.move(Direction.UP);
    this.moveDown = this.move(Direction.DOWN);
  }

  /**
   * Initialize the step configuration based on the given {@link MovableGridType}.
   *
   * @param movableGridType a {@link MovableGridType} indicates the role; possible values: {@link
   *     MovableGridType#GHOST}, {@link MovableGridType#PACMAN}
   */
  private void initConfig(MovableGridType movableGridType) {
    switch (movableGridType) {
      case GHOST:
        step = getParentMap().getMapConfig().getGhostStep();
        break;
      case PACMAN:
        step = getParentMap().getMapConfig().getPacmanStep();
        break;
      default:
    }
  }

  /**
   * Returns an {@link AnimationTimer} with {@link AnimationTimer#handle(long)} overridden to handle
   * the moving.
   *
   * @param direction the {@link Direction} indicating where to go
   * @return an {@link AnimationTimer} instance handling the moving of the given direction
   */
  private AnimationTimer move(Direction direction) {
    return new AnimationTimer() {
      public void handle(long currentNanoTime) {
        Set<Grid> obstacles = (Set<Grid>) (Set<?>) MovableGrid.this.getParentMap().getObstacles();

        switch (direction) {
          case RIGHT:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.RIGHT, obstacles)) {
              MovableGrid.this.setX(MovableGrid.this.getX() + step);
              MovableGrid.this.handleMove(Direction.RIGHT);
            } else {
              MovableGrid.this.handleCantMove(Direction.RIGHT);
            }
            break;
          case LEFT:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.LEFT, obstacles)) {
              MovableGrid.this.setX(MovableGrid.this.getX() - step);
              MovableGrid.this.handleMove(Direction.LEFT);
            } else {
              MovableGrid.this.handleCantMove(Direction.LEFT);
            }
            break;
          case UP:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.UP, obstacles)) {
              MovableGrid.this.setY(MovableGrid.this.getY() - step);
              MovableGrid.this.handleMove(Direction.UP);
            } else {
              MovableGrid.this.handleCantMove(Direction.UP);
            }
            break;
          case DOWN:
            if (!MovableGrid.this.isGoingToTouchGrids(Direction.DOWN, obstacles)) {
              MovableGrid.this.setY(MovableGrid.this.getY() + step);
              MovableGrid.this.handleMove(Direction.DOWN);
            } else {
              MovableGrid.this.handleCantMove(Direction.DOWN);
            }
            break;
        }

        checkIfTouchingPortal();
      }
    };
  }

  /**
   * Tests if this {@link MovableGrid} is touching a {@link Portal}. If true, sends this {@link
   * MovableGrid} to the position where the twin portal (using {@link Portal#getTwinPortal()} to
   * retrieve) stays.
   *
   * <p>This method gets all {@link Portal}s from the parent {@link Map} and calls {@link
   * Grid#isTouching(Grid, double)} to check it one by one.
   */
  private void checkIfTouchingPortal() {
    for (Portal portal : getParentMap().getPortals()) {
      if (isTouching(portal, getParentMap().getMapConfig().getGridLength() * 0.8)) {
        if (portal.isOpen()) {
          // send to another portal
          setX(portal.getTwinPortal().getX());
          setY(portal.getTwinPortal().getY());

          // close portal
          portal.getTwinPortal().close();
        }
        return;
      }
    }

    // open portals if leaving portals
    for (Portal portal : getParentMap().getPortals()) {
      if (isTouching(portal, 0)) {
        portal.open();
      }
    }
  }

  /**
   * Tests if this {@link MovableGrid} is going to touch a set of another given {@link Grid}.
   *
   * <p>The algorithm: this method generates a mock grid in a position where this {@link
   * MovableGrid} will be after the next move calculated based on the given direction, and checks if
   * the mock one overlaps any grids in the given set.
   *
   * @param direction the current {@link Direction} of moving
   * @param grids a set of {@link Grid} to test if touching
   * @param padding a permissible error range in px indicating a range to allow overlapping to some
   *     extend, in px
   * @return {@code true} if this {@link MovableGrid} is going to touch a set of another given
   *     {@link Grid}; {@code false} otherwise
   */
  public boolean isGoingToTouchGrids(Direction direction, Set<Grid> grids, double padding) {
    // calculate next step based on direction
    double nextX = getX();
    double nextY = getY();
    switch (direction) {
      case RIGHT:
        nextX += step;
        break;
      case LEFT:
        nextX -= step;
        break;
      case UP:
        nextY -= step;
        break;
      case DOWN:
        nextY += step;
        break;
      default:
    }

    // check if the next step is beyond screen
    //    if (nextX < 0
    //        || nextY < 0
    //        || nextX + getParentMap().getMapConfig().getGridLength() + step > MapResolution.WIDTH
    //        || nextY + getParentMap().getMapConfig().getGridLength() + step >
    // MapResolution.HEIGHT) {
    //      return true;
    //    }

    // generate a mock grid at the next step
    double gridLength = getParentMap().getMapConfig().getGridLength();
    Grid nextPositionGrid = new Grid(getParentMap(), nextX / gridLength, nextY / gridLength);
    nextPositionGrid.setX(nextX);
    nextPositionGrid.setY(nextY);

    // check if the mock grid overlaps any obstacle
    for (Grid grid : grids) {
      if (grid.isTouching(nextPositionGrid, padding)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Tests if this {@link MovableGrid} is going to touch a set of another given {@link Grid}.
   *
   * <p>This method does the same thing as what {@link #isGoingToTouchGrids(Direction, Set, double)}
   * does. But with the third parameter {@code padding} being {@code 1} (which is a recommended
   * minimal value).
   *
   * @param direction the current {@link Direction} of moving
   * @param grids a set of {@link Grid} to test if touching
   * @return {@code true} if this {@link MovableGrid} is going to touch a set of another given *
   *     {@link Grid}; {@code false} otherwise
   */
  public boolean isGoingToTouchGrids(Direction direction, Set<Grid> grids) {
    return isGoingToTouchGrids(direction, grids, 1); // padding = 1 for the error
  }

  /**
   * Handles what should be done whenever moving.
   *
   * <p>This method is called inside {@link #move(Direction)}. This doesn't do anything as default,
   * and is designed to be overridden.
   *
   * @param direction the current direction of moving
   * @see Ghost#handleMove(Direction)
   * @see Pacman#handleMove(Direction)
   */
  public void handleMove(Direction direction) {}

  /**
   * Handles what should be done whenever cannot move.
   *
   * <p>This method is called inside {@link #move(Direction)}. This doesn't do anything as default,
   * and is designed to be overridden.
   *
   * @param direction the current direction of moving
   * @see Ghost#handleCantMove(Direction)
   */
  public void handleCantMove(Direction direction) {}
}
