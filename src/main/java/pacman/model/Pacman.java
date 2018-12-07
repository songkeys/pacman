package pacman.model;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import pacman.constant.FileName;
import pacman.constant.MapConfig;

public class Pacman extends Rectangle {

  public Pacman(double x, double y) {

    // set coordinates
    this.setX(x * MapConfig.GRID_LENGTH);
    this.setY(y * MapConfig.GRID_LENGTH);

    // set height and width
    this.setHeight(MapConfig.GRID_LENGTH);
    this.setWidth(MapConfig.GRID_LENGTH);

    // set image
    Image image = new Image(FileName.IMAGE_PACMAN);
    this.setFill(new ImagePattern(image));
  }
}
