package pacman.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pacman.constant.MapConfig;

public class Cookie extends Circle {

  private int value;

  public Cookie(double x, double y) {
    this.value = 5;

    double centerX = x * MapConfig.GRID_LENGTH + MapConfig.GRID_LENGTH / 2;
    double centerY = y * MapConfig.GRID_LENGTH + MapConfig.GRID_LENGTH / 2;
    this.setCenterX(centerX);
    this.setCenterY(centerY);

    double radius = MapConfig.GRID_LENGTH / 4;
    this.setRadius(radius);
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
