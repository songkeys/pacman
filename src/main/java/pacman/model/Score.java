package pacman.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * <h1>Score</h1>
 *
 * <p>A {@link Score} is an object to keep the record of the play's score in a single run.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see ScoreBoard
 */
public class Score implements Serializable {

  /** The value of the score. */
  private int value;
  /** The player's name. */
  private String nickname;
  /** The time when the score settles. */
  private Date time;

  /**
   * Allocates a new {@link Score} object.
   *
   * <p>This constructor initializes the {@link #value} to {@code 0}, records the {@link #nickname}
   * and sets the {@link #time} to the current time.
   *
   * @param nickname the player's nickname.
   */
  public Score(String nickname) {
    this.value = 0;
    this.nickname = nickname;
    this.time = new Date();
  }

  /**
   * Allocates a new {@link Score} object.
   *
   * <p>This constructor does the same thing as {@link Score} does, but with a default {@link
   * #nickname} {@code "Unknown Player"} initialized.
   */
  public Score() {
    this("Unknown Player");
  }

  /**
   * Increases the {@link #value} by the given {@code value}.
   *
   * @param value an integer to be added to the {@link #value}.
   */
  public void gain(int value) {
    this.value += value;
  }

  /**
   * Decreases the {@link #value} by the given {@code value}.
   *
   * @param value an integer to be added to the {@link #value}.
   */
  public void lose(int value) {
    this.value -= value;
  }

  /** Settles the score. That is: changes the {@link #time} to the current time. */
  public void settle() {
    this.time = new Date();
  }

  /**
   * Returns the {@link #value}.
   *
   * @return an integer indicating the value of score.
   */
  public int getValue() {
    return value;
  }

  /**
   * Returns the player's {@link #nickname}.
   *
   * @return the player's {@link #nickname}.
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * Returns the time when the {@link Score} is settled.
   *
   * @return the time when the {@link Score} is settled.
   */
  public Date getTime() {
    return time;
  }
}
