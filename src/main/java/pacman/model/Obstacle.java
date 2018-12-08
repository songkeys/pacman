package pacman.model;

import javafx.scene.paint.Color;

public class Obstacle extends Grid {

  /** The thickness of the obstacle. */
  public static double THICKNESS = 25;

  /**
   * Initializes the Obstacle object.
   *
   * @param x the x-coordinate of the start point of the obstacle
   * @param y the y-coordinate of the start point of the obstacle
   */
  public Obstacle(Map map, double row, double column) {
    super(map, row, column);

    // set color
    this.setFill(Color.RED);
  }
}
