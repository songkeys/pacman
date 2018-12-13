package pacman.model;

/**
 *
 *
 * <h1>Life</h1>
 *
 * <p>A {@link Life} is an object to keep the record of the {@link Pacman}'s life in a single run.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 */
public class Life {

  /** The upper limit of life */
  private int total;
  /** The remaining life. */
  private int remaining;

  /**
   * Allocates a new {@link Life} object.
   *
   * <p>This constructor initialize the {@link #total} and {@link #remaining} to be equal to the
   * parameter {@code total}.
   *
   * @param total the upper limit of life
   */
  public Life(int total) {
    this.total = total;
    this.remaining = total;
  }

  /**
   * Allocates a new {@link Life} object.
   *
   * <p>This method does the same thing as {@link #Life(int)} but with the {@code total} to be
   * {@code 3} as default.
   */
  public Life() {
    this(3);
  }

  /** Reduces the {@link #remaining} life by {@code 1} (minimum to {@code 0}). */
  public void lose() {
    remaining--;
    if (remaining < 0) {
      remaining = 0;
    }
  }

  /** Increases the {@link #remaining} life by {@code 1} (maximum to {@link #total}). */
  public void gain() {
    remaining++;
    if (remaining > total) {
      remaining = total;
    }
  }

  /**
   * Returns the {@link #remaining} life
   *
   * @return the {@link #remaining} life
   */
  public int getRemaining() {
    return remaining;
  }

  /**
   * Returns the {@link #total} life
   *
   * @return the {@link #total} life
   */
  public int getTotal() {
    return total;
  }
}
