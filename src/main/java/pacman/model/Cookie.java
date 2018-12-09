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

  public void hide() {
    this.setVisible(false);
  }

  public void show() {
    this.setVisible(true);
  }
}
