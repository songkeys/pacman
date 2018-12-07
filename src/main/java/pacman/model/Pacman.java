package pacman.model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import pacman.constant.MapConfig;

public class Pacman extends Circle {

  public Pacman(double x, double y) {

    double centerX = x * MapConfig.GRID_LENGTH + MapConfig.GRID_LENGTH / 2;
    double centerY = y * MapConfig.GRID_LENGTH + MapConfig.GRID_LENGTH / 2;

    // set position
    this.setCenterX(centerX);
    this.setCenterY(centerY);

    // set radius
    double radius = (double) MapConfig.GRID_LENGTH / 2;
    this.setRadius(radius);

    // set image
    Image image = new Image("/pacman/image/pacman.png");
    this.setFill(new ImagePattern(image));
  }
}
