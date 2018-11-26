package pacman;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pacman extends Circle {

  public Pacman(double x, double y) {
    this.setCenterX(x);
    this.setCenterY(y);
    this.setRadius(25);
    this.setFill(Color.YELLOW);
  }
}
