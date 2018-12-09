package pacman.model;

public class Score {
  private int value;

  public Score() {
    this.value = 0;
  }

  public void gain(int value) {
    this.value += value;
  }

  public void lose(int value) {
    this.value -= value;
  }

  public int getValue() {
    return value;
  }
}
