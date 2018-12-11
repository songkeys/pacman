package pacman.model;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class ScoreBoard {

  private String levelTitle;
  private Set<Score> scores = new TreeSet<>(Comparator.comparingInt(Score::getValue));

  public ScoreBoard(String levelTitle, Set<Score> scores) {
    this.levelTitle = levelTitle;
    this.scores.addAll(scores);
  }

  public ScoreBoard(String levelTitle) {
    this.levelTitle = levelTitle;
  }

  public Set<Score> getScores() {
    return scores;
  }

  public void addScore(Score score) {
    scores.add(score);
  }

  public String getLevelTitle() {
    return levelTitle;
  }

  public void setLevelTitle(String levelTitle) {
    this.levelTitle = levelTitle;
  }

  public void setScores(Set<Score> scores) {
    this.scores = scores;
  }

  public Score getBestScore() {
    try {
      return scores.iterator().next();
    } catch (Exception e) {
      // no score yet
      return new Score();
    }
  }
}
