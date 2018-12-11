package pacman.model;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class ScoreBoard {

  private Set<Score> scores;

  public ScoreBoard(Set<Score> scores) {
    this();
    this.scores.addAll(scores);
  }

  public ScoreBoard() {
    this.scores = new TreeSet<>(Comparator.comparingInt(Score::getValue).reversed());
  }

  public Set<Score> getScores() {
    return scores;
  }

  public void setScores(Set<Score> scores) {
    this.scores = scores;
  }

  public void addScore(Score score) {
    scores.add(score);
  }

  public Score getBestScore() {
    try {
      for (Score score : scores) {
        System.out.println(score.getValue());
      }
      return scores.iterator().next();
    } catch (Exception e) {
      // no score yet
      return new Score();
    }
  }
}
