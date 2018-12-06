package pacman.model;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.Group;
import pacman.constant.Orientation;

public class Maze {

  /** The obstacles collection. */
  public Set<Obstacle> obstacles;

  /** Initializes the Maze object. */
  public Maze() {
    obstacles = new HashSet<>();
  }

  /**
   * Checks if the point is touching obstacles.
   *
   * @param x the x-coordinate of the point
   * @param y the y-coordinate of the point
   * @param padding the padding of the point
   * @return <code>true</code> if the point is touching obstacles; <code>false</code> otherwise.
   */
  public Boolean isTouching(double x, double y, double padding) {
    for (Obstacle obstacle : obstacles) {
      boolean isBetweenWidth =
          x >= obstacle.getX() - padding
              && x <= obstacle.getX() + obstacle.getWidth() + padding;
      boolean isBetweenHeight =
          y >= obstacle.getY() - padding
              && y <= obstacle.getY() + obstacle.getHeight() + padding;

      if (isBetweenWidth && isBetweenHeight) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if there's an obstacle in a rectangular area.
   *
   * @param fromX the x-coordinate of the left-top endpoint of the rectangle
   * @param toX the x-coordinate of the right-top endpoint of the rectangle
   * @param fromY the y-coordinate of the left-top endpoint of the rectangle
   * @param toY the y-coordinate of an right-top endpoint of the rectangle
   * @return <code>true</code> if there's an obstacle in a rectangular area; <code>false</code>
   *     otherwise.
   */
  public Boolean hasObstacle(double fromX, double toX, double fromY, double toY) {
    for (double i = fromX; i < toX; i++) {
      for (double j = fromY; j < toY; j++) {
        if (this.isTouching(i, j, 0)) {
          return true;
        }
      }
    }
    return false;
  }

  /** Draws the maze */
  public void draw(Group root) {
    // ~~~~~~~~~~~~~~~~~~~~~~~~~ frame ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // top
    this.obstacles.add(new Obstacle(0, 0, Orientation.HORIZONTAL, 48));
    // bottom
    this.obstacles.add(new Obstacle(0, 600, Orientation.HORIZONTAL, 48));
    // left
    this.obstacles.add(new Obstacle(0, 0, Orientation.VERTICAL, 25));
    // right
    this.obstacles.add(new Obstacle(1225 - Obstacle.THICKNESS, 0, Orientation.VERTICAL, 25));

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Islands ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // obsTopLeft
    this.obstacles.add(
        new Obstacle(
            12 * Obstacle.THICKNESS, Obstacle.THICKNESS, Orientation.VERTICAL, 4));
    // obsTopRight
    this.obstacles.add(
        new Obstacle(
            36 * Obstacle.THICKNESS, Obstacle.THICKNESS, Orientation.VERTICAL, 4));
    // obsBottomLeft
    this.obstacles.add(
        new Obstacle(
            12 * Obstacle.THICKNESS, 600 - 4 * Obstacle.THICKNESS, Orientation.VERTICAL, 4));
    // obsBottomRight
    this.obstacles.add(
        new Obstacle(
            36 * Obstacle.THICKNESS, 600 - 4 * Obstacle.THICKNESS, Orientation.VERTICAL, 4));
    // obsTopMiddle
    this.obstacles.add(
        new Obstacle(
            16 * Obstacle.THICKNESS, 4 * Obstacle.THICKNESS, Orientation.HORIZONTAL, 17));
    // obsBottomMiddle
    this.obstacles.add(
        new Obstacle(
            16 * Obstacle.THICKNESS,
            600 - 4 * Obstacle.THICKNESS,
            Orientation.HORIZONTAL,
            17));
    // obsLMTop
    this.obstacles.add(
        new Obstacle(
            8 * Obstacle.THICKNESS, 8 * Obstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // obsLMTop4
    this.obstacles.add(
        new Obstacle(
            8 * Obstacle.THICKNESS, 12 * Obstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // obsLMBottom
    this.obstacles.add(
        new Obstacle(
            8 * Obstacle.THICKNESS, 16 * Obstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // obsRMTop
    this.obstacles.add(
        new Obstacle(
            36 * Obstacle.THICKNESS, 8 * Obstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // obsRMTop2
    this.obstacles.add(
        new Obstacle(
            36 * Obstacle.THICKNESS, 12 * Obstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // obsRMBottom
    this.obstacles.add(
        new Obstacle(
            36 * Obstacle.THICKNESS, 16 * Obstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // LobsLeftTop1
    this.obstacles.add(
        new Obstacle(
            4 * Obstacle.THICKNESS, 4 * Obstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // LobsLeftTop2
    this.obstacles.add(
        new Obstacle(
            4 * Obstacle.THICKNESS, 5 * Obstacle.THICKNESS, Orientation.VERTICAL, 6));
    // LobsLeftBottom1
    this.obstacles.add(
        new Obstacle(
            4 * Obstacle.THICKNESS, 600 - 4 * Obstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // LobsLeftBottom2
    this.obstacles.add(
        new Obstacle(
            4 * Obstacle.THICKNESS, 600 - 10 * Obstacle.THICKNESS, Orientation.VERTICAL, 6));
    // LobsRightTop1
    this.obstacles.add(
        new Obstacle(
            40 * Obstacle.THICKNESS, 4 * Obstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // LobsRightTop2
    this.obstacles.add(
        new Obstacle(
            44 * Obstacle.THICKNESS, 5 * Obstacle.THICKNESS, Orientation.VERTICAL, 6));
    // LobsRightBottom1
    this.obstacles.add(
        new Obstacle(
            40 * Obstacle.THICKNESS,
            600 - 4 * Obstacle.THICKNESS,
            Orientation.HORIZONTAL,
            5));
    // LobsRightBottom2
    this.obstacles.add(
        new Obstacle(
            44 * Obstacle.THICKNESS, 600 - 10 * Obstacle.THICKNESS, Orientation.VERTICAL, 6));
    // cageBottom
    this.obstacles.add(
        new Obstacle(
            16 * Obstacle.THICKNESS,
            600 - 8 * Obstacle.THICKNESS,
            Orientation.HORIZONTAL,
            17));
    // cageRightV
    this.obstacles.add(
        new Obstacle(
            32 * Obstacle.THICKNESS, 600 - 16 * Obstacle.THICKNESS, Orientation.VERTICAL, 8));
    // cageLeftV
    this.obstacles.add(
        new Obstacle(
            16 * Obstacle.THICKNESS, 600 - 16 * Obstacle.THICKNESS, Orientation.VERTICAL, 8));
    // cateRightH
    this.obstacles.add(
        new Obstacle(
            17 * Obstacle.THICKNESS, 8 * Obstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // cateLeftH
    this.obstacles.add(
        new Obstacle(
            27 * Obstacle.THICKNESS, 8 * Obstacle.THICKNESS, Orientation.HORIZONTAL, 5));

    root.getChildren().addAll(obstacles);
  }
}
