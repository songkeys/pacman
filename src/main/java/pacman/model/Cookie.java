package pacman.model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import pacman.constant.FileName;

public class Cookie extends Grid {

  private int value;

  public Cookie(Map map, double x, double y) {
    super(map, x, y);

    // set image
    Image image = new Image(FileName.IMAGE_COOKIE);
    this.setFill(new ImagePattern(image));
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
