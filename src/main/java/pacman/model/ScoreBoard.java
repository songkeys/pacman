package pacman.model;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 *
 * <h1>ScoreBoard</h1>
 *
 * <p>A {@link ScoreBoard} is an object to keep the record of the {@link Pacman}'s score in a all
 * runs in a level.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see Score
 */
public class ScoreBoard {

  /** The set of {@link Score}. */
  private Set<Score> scores;

  /**
   * Allocates a {@link ScoreBoard} with a set of {@link Score}.
   *
   * @param scores the set of {@link Score} to be added
   */
  public ScoreBoard(Set<Score> scores) {
    this();
    this.scores.addAll(scores);
  }

  /** Allocates a {@link ScoreBoard} with an empty set of {@link Score}. */
  public ScoreBoard() {
    this.scores = new TreeSet<>(Comparator.comparingInt(Score::getValue).reversed());
  }

  /**
   * Returns a set of {@link Score}.
   *
   * @return a {@link Score} set
   */
  public Set<Score> getScores() {
    return scores;
  }

  /**
   * Adds a {@link Score} to the score board.
   *
   * @param score a {@link Score} to be added
   */
  public void addScore(Score score) {
    scores.add(score);
  }

  /**
   * Returns the best {@link Score} in this score board.
   *
   * <p>If there is no {@link Score} yet, creates an new {@link Score} and returns it.
   *
   * @return the best {@link Score} in this score board
   */
  public Score getBestScore() {
    try {
      return scores.iterator().next();
    } catch (Exception e) {
      // no score yet
      return new Score();
    }
  }
}
