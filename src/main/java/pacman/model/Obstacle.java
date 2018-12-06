package pacman.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pacman.constant.Map;

public class Obstacle extends Rectangle {

  /** The thickness of the obstacle. */
  public static double THICKNESS = 25;

  /**
   * Initializes the Obstacle object.
   *
   * @param x the x-coordinate of the start point of the obstacle
   * @param y the y-coordinate of the start point of the obstacle
   */
  public Obstacle(double x, double y) {
    // set coordinates
    this.setX(x * Map.GRID_LENGTH);
    this.setY(y * Map.GRID_LENGTH);

    // set height and width
    this.setHeight(Map.GRID_LENGTH);
    this.setWidth(Map.GRID_LENGTH);
    // set color
    this.setFill(Color.RED);
  }
}
