package pacman.model;

/**
 *
 *
 * <h1>Spawn</h1>
 *
 * <p>A {@link Spawn} is a invisible {@link Grid} located in a {@link Map}. It indicates where the
 * {@link Pacman} were born, so that when the {@link Pacman} is dead, the {@link Pacman} will be
 * sent here.
 *
 * <p>This class extends {@link Grid}.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see Grid
 * @see Map
 */
public class Spawn extends Grid {

  /**
   * Allocates a new {@link Spawn} object.
   *
   * <p>This constructor sets the {@link Pacman} in the given position of the given {@link Map}.
   *
   * @param map the {@link Map} where this {@link Spawn} stays
   * @param row the row index in the {@link Map} where this {@link Spawn} stays, starting from 0
   * @param column the column index in the {@link Map} where this {@link Spawn} stays, starting from
   *     0
   */
  public Spawn(Map map, double row, double column) {
    super(map, row, column);
  }
}
