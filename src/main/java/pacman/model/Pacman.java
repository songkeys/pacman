package pacman.model;

import pacman.constant.Direction;
import pacman.constant.FileName;
import pacman.constant.MovableGridType;
import pacman.util.GameManager;

/**
 *
 *
 * <h1>Pacman</h1>
 *
 * <p>A {@link Pacman} is a {@link MovableGrid} in a {@link Map}. It is the main character of the
 * game. It can move under the control of the player.
 *
 * <p>This class extends {@link MovableGrid}.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see Grid
 * @see MovableGrid
 * @see Map
 * @see Direction
 */
public class Pacman extends MovableGrid {

  /**
   * Allocates a new {@link Pacman} object.
   *
   * <p>This constructor sets the {@link Pacman} in the given position of the given {@link Map}, and
   * sets a image of {@link Pacman}.
   *
   * @param map the {@link Map} where this {@link Pacman} stays
   * @param row the row index in the {@link Map} where this {@link Pacman} stays, starting from 0
   * @param column the column index in the {@link Map} where this {@link Pacman} stays, starting
   *     from 0
   */
  public Pacman(Map map, double row, double column) {
    super(map, row, column, MovableGridType.PACMAN);

    this.setImage(FileName.IMAGE_PACMAN);
  }

  /**
   * This method overrides {@link MovableGrid#handleMove(Direction)}. This is called when the moving
   * happens.
   *
   * <p>This method rotates the image to meet the direction where this {@link Pacman} towards, and
   * calls {@link #checkTouchingCookies()}.
   *
   * @param direction the current direction of moving
   */
  @Override
  public void handleMove(Direction direction) {
    switch (direction) {
      case UP:
        setRotate(270);
        break;
      case DOWN:
        setRotate(90);
        break;
      case LEFT:
        setRotate(180);
        break;
      case RIGHT:
        setRotate(0);
        break;
      default:
    }
    checkTouchingCookies();
  }

  /**
   * Tests if this {@link Pacman} is touching an existing {@link Cookie}. If true, calls {@link
   * GameManager#handleCookieTouched(Cookie)}.
   *
   * <p>This method gets all {@link Cookie}s from the parent {@link Map} and calls {@link
   * Grid#isTouching(Grid, double)} to check it one by one.
   */
  private void checkTouchingCookies() {
    for (Cookie cookie : getParentMap().getCookies()) {
      if (cookie.isExisting()
          && isTouching(cookie, getParentMap().getMapConfig().getCookiePadding())) {
        GameManager.INSTANCE.handleCookieTouched(cookie);
        return;
      }
    }
  }
}
