package pacman.model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 *
 * <h1>Grid</h1>
 *
 * <p>A {@link Grid} is an <i>conceptually abstract</i> object located in a {@link Map}.
 *
 * <p><b>Note</b>: A single {@link Grid} makes no sense in a {@link Map} in some way. So It's
 * normally used to be extended.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see Cookie
 * @see Ghost
 * @see MovableGrid
 * @see Obstacle
 * @see Pacman
 * @see Portal
 * @see Spawn
 * @see Map
 */
public class Grid extends Rectangle {

  /** The map where this grid stays. */
  private Map parentMap;

  /**
   * Allocates a new {@link Grid} object.
   *
   * <p>This constructor sets the {@link Grid} in the given position of the given {@link Map}.
   *
   * @param parentMap the {@link Map} where this {@link Grid} stays
   * @param row the row index in the {@link Map} where this {@link Grid} stays, starting from 0
   * @param column the column index in the {@link Map} where this {@link Grid} stays, starting from
   *     0
   */
  public Grid(Map parentMap, double row, double column) {

    // set map
    this.parentMap = parentMap;

    // set position
    double gridLength = parentMap.getMapConfig().getGridLength();

    this.setX(row * gridLength);
    this.setY(column * gridLength);

    // set height and width
    this.setHeight(gridLength);
    this.setWidth(gridLength);
  }

  /**
   * Returns the parent {@link Map} where this {@link Grid} stays.
   *
   * @return the parent {@link Map} where this {@link Grid} stays
   */
  public Map getParentMap() {
    return parentMap;
  }

  /**
   * Changes the image of this {@code Grid} to be the image file from the given {@code fileName}.
   *
   * @param fileName the image file's name
   */
  public void setImage(String fileName) {
    Image image = new Image(fileName);
    setFill(new ImagePattern(image));
  }

  /**
   * Tests if this {@link Grid} is touching a given grid.
   *
   * @param grid a {@link Grid} object
   * @param padding a permissible error range in px indicating a range to allow overlapping to some
   *     extend, in px
   * @return {@code true} if this {@link Grid} is touching the given {@link Grid}; {@code false}
   *     otherwise
   */
  public boolean isTouching(Grid grid, double padding) {
    double myX = this.getX();
    double myY = this.getY();
    double itsX = grid.getX();
    double itsY = grid.getY();
    double gridLength = getParentMap().getMapConfig().getGridLength();

    return myX < itsX + gridLength - padding
        && myX > itsX - gridLength + padding
        && myY < itsY + gridLength - padding
        && myY > itsY - gridLength + padding;
  }
}
