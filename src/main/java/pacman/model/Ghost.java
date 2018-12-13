package pacman.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import pacman.constant.Direction;
import pacman.constant.FileName;
import pacman.constant.MovableGridType;
import pacman.util.GameManager;

/**
 *
 *
 * <h1>Ghost</h1>
 *
 * <p>A {@link Ghost} is a {@link MovableGrid} in a {@link Map}. It can move randomly. When it
 * touches the {@link Pacman}, it will ask {@link GameManager} to handle the some consequences.
 *
 * <p>This class extends {@link MovableGrid} and implements {@link Runnable}.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see MovableGrid
 * @see Runnable
 * @see Map
 * @see Direction
 */
public class Ghost extends MovableGrid implements Runnable {
  /**
   * The current direction this ghost moving towards. This is used in {@link #findDirectionToMove()}
   * to prevent moving back unexpectedly.
   */
  private Direction direction;
  /**
   * The time counter recording how long the ghost walks. This is used for throttling in {@link
   * #handleMove(Direction)}.
   */
  private int timeWalked;

  /**
   * Allocates a new {@link Ghost} object.
   *
   * <p>This constructor sets the {@link Ghost} in the given position of the given {@link Map}, sets
   * a random image of {@link Ghost}, and gives a initial {@link Direction#UP} to move to.
   *
   * @param map the {@link Map} where this {@link Ghost} stays
   * @param row the row index in the {@link Map} where this {@link Ghost} stays, starting from 0
   * @param column the column index in the {@link Map} where this {@link Ghost} stays, starting from
   *     0
   */
  public Ghost(Map map, double row, double column) {
    super(map, row, column, MovableGridType.GHOST);

    // set a random ghost image
    int randomIndex = new Random().nextInt(FileName.IMAGE_GHOSTS.size());
    int index = 0;
    for (String image : FileName.IMAGE_GHOSTS) {
      if (index == randomIndex) {
        this.setImage(image);
      }
      index++;
    }

    // set initial direction
    this.direction = Direction.UP;
  }

  /**
   * Moves to a given {@link Direction}.
   *
   * @param direction a direction indicating where to go
   */
  private void moveTo(Direction direction) {
    switch (direction) {
      case UP:
        setScaleX(-1); // to reverse the image in x-axis
        moveUp.start();
        break;
      case DOWN:
        setScaleX(1);
        moveDown.start();
        break;
      case LEFT:
        setScaleX(-1);
        moveLeft.start();
        break;
      case RIGHT:
        setScaleX(1);
        moveRight.start();
        break;
      default:
    }
  }

  /**
   * Returns a {@link Direction} indicating a possible way to move to.
   *
   * <p>This method first gets all possible directions (without the current and the opposite one) to
   * move, and randomly pick a direction to return. If there is no possible direction to move,
   * returns the opposite direction. Otherwise, keeps moving.
   *
   * @return a {@link Direction} indicating a possible way to move to
   */
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
      default:
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

  /**
   * Moves to another direction randomly.
   *
   * <p>This method calls {@link #findDirectionToMove()} to find a direction first, then stops the
   * current moving by calling {@link #freeze()}, and calls {@link #moveTo(Direction)}.
   */
  private void moveToAnotherDirection() {
    direction = findDirectionToMove();
    freeze();
    moveTo(direction);
  }

  /**
   * Tests if this {@link Ghost} is touching the {@link Pacman}. If true, calls {@link
   * GameManager#handleGhostTouched(Ghost)}.
   */
  private void checkTouchingPacman() {
    if (getParentMap()
        .getPacman()
        .isTouching(this, getParentMap().getMapConfig().getGhostPadding())) {
      GameManager.INSTANCE.handleGhostTouched(this);
    }
  }

  /**
   * This method overrides {@link MovableGrid#handleMove(Direction)}. This is called when the moving
   * happens.
   *
   * <p>This method calls {@link #moveToAnotherDirection()} every 10 times it is called, and calls
   * {@link #checkTouchingPacman()} always.
   *
   * @param direction the current direction of moving
   */
  @Override
  public void handleMove(Direction direction) {
    timeWalked++;
    if (timeWalked >= 10) {
      moveToAnotherDirection();
      timeWalked = 0;
    }
    checkTouchingPacman();
  }

  /**
   * This method overrides {@link MovableGrid#handleMove(Direction)}. This is called when the moving
   * cannot happen.
   *
   * <p>This method calls {@link #moveToAnotherDirection()}.
   *
   * @param direction the current direction of moving
   */
  @Override
  public void handleCantMove(Direction direction) {
    moveToAnotherDirection();
  }

  /**
   * This method overrides {@link Runnable#run()}.
   *
   * <p>This method calls {@link #moveTo(Direction)} to start the moving.
   */
  @Override
  public void run() {
    moveTo(direction);
  }

  /** Freezes this {@link Ghost}. I.e. Stops the moving. */
  public void freeze() {
    moveUp.stop();
    moveDown.stop();
    moveLeft.stop();
    moveRight.stop();
  }

  /**
   * Returns the value of {@link Score} of this {@link Ghost}.
   *
   * <p><b>Note:</b> currently the value is constant as {@code 5}.
   *
   * @return the value of {@link Score} of this {@link Ghost}
   */
  public int getValue() {
    // TODO: 2018-12-12
    //  This value should be changeable according to map configuration in a map file, but need to
    //  figure out a better way to make the configuration.
    return 5;
  }
}
