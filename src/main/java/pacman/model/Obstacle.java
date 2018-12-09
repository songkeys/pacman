package pacman.model;

import pacman.constant.FileName;

public class Obstacle extends Grid {

  /**
   * Initializes the Obstacle object.
   *
   * @param x the x-coordinate of the start point of the obstacle
   * @param y the y-coordinate of the start point of the obstacle
   */
  public Obstacle(Map map, double row, double column) {
    super(map, row, column);

    this.setImage(FileName.IMAGE_OBSTACLE);
  }
}
