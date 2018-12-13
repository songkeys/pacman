package pacman.model;

/**
 *
 *
 * <h1>Obstacle</h1>
 *
 * <p>A {@link Obstacle} is a {@link Grid} located in a {@link Map}. No {@link MovableGrid} is
 * allowed to move across it.
 *
 * <p>This class extends {@link Grid}.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see Grid
 * @see Map
 */
public class Obstacle extends Grid {

  /**
   * Allocates a new {@link Obstacle} object.
   *
   * <p>This constructor sets the {@link Obstacle} in the given position of the given {@link Map}, AND sets an image of wall.
   *
   * @param map the {@link Map} where this {@link Cookie} stays
   * @param row the row index in the {@link Map} where this {@link Obstacle} stays, starting from 0
   * @param column the column index in the {@link Map} where this {@link Obstacle} stays, starting
   *     from 0
   */
  public Obstacle(Map map, double row, double column) {
    super(map, row, column);

    this.setImage(map.getWallFileName());
  }
}
