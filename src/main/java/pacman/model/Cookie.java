package pacman.model;

import pacman.constant.FileName;

public class Cookie extends Grid {

  private int value;

  public Cookie(Map map, double row, double column, int value) {
    super(map, row, column);

    switch (value) {
      case 1:
        this.setImage(FileName.IMAGE_COOKIE_SMALL);
      case 5:
        this.setImage(FileName.IMAGE_COOKIE_MEDIUM);
      case 10:
        this.setImage(FileName.IMAGE_COOKIE_BIG);
      default:
        this.setImage(FileName.IMAGE_COOKIE_SMALL);
    }

    this.value = value;
  }

  public Cookie(Map map, double row, double column) {
    this(map, row, column, 1);
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
