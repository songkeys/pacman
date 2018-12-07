package pacman.model;

import javafx.scene.paint.Color;

public class Cookie extends Grid {

  private int value;

  public Cookie(double x, double y) {
    super(x, y);

    this.value = 5;

    // set image
    this.setFill(Color.SADDLEBROWN);
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
