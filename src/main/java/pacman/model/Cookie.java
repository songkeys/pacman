package pacman.model;

import pacman.constant.FileName;

public class Cookie extends Grid {

  private int score;

  public Cookie(Map map, double row, double column) {
    super(map, row, column);

    this.setImage(FileName.IMAGE_COOKIE);
  }

  public int getScore() {
    return score;
  }

  public void eat() {
    setVisible(false);
  }

  public boolean isEaten() {
    return !isVisible();
  }
}
