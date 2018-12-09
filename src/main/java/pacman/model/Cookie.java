package pacman.model;

import pacman.constant.FileName;

public class Cookie extends Grid {

  private int value;

  public Cookie(Map map, double row, double column) {
    super(map, row, column);

    this.setImage(FileName.IMAGE_COOKIE);
  }

  public int getValue() {
    return value;
  }

  public void eat() {
    setVisible(false);
  }

  public boolean isEaten() {
    return !isVisible();
  }
}
