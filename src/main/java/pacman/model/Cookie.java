package pacman.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import pacman.constant.MapConfig;

public class Cookie extends Rectangle {

  private int value;

  public Cookie(double x, double y) {
    this.value = 5;

    // set coordinates
    this.setX(x * MapConfig.GRID_LENGTH);
    this.setY(y * MapConfig.GRID_LENGTH);

    // set height and width
    this.setHeight(MapConfig.GRID_LENGTH);
    this.setWidth(MapConfig.GRID_LENGTH);

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
