package pacman.model;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.Group;
import pacman.constant.Orientation;
import pacman.model.BarObstacle;

public class Maze {

  /** The obstacles collection. */
  public Set<BarObstacle> obstacles;

  /** Initializes the Maze object. */
  Maze() {
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
    for (BarObstacle barObstacle : obstacles) {
      boolean isBetweenWidth =
          x >= barObstacle.getX() - padding
              && x <= barObstacle.getX() + barObstacle.getWidth() + padding;
      boolean isBetweenHeight =
          y >= barObstacle.getY() - padding
              && y <= barObstacle.getY() + barObstacle.getHeight() + padding;

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
    this.obstacles.add(new BarObstacle(0, 0, Orientation.HORIZONTAL, 48));
    // bottom
    this.obstacles.add(new BarObstacle(0, 600, Orientation.HORIZONTAL, 48));
    // left
    this.obstacles.add(new BarObstacle(0, 0, Orientation.VERTICAL, 25));
    // right
    this.obstacles.add(new BarObstacle(1225 - BarObstacle.THICKNESS, 0, Orientation.VERTICAL, 25));

    // ~~~~~~~~~~~~~~~~~~~~~~~~~ Islands ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // obsTopLeft
    this.obstacles.add(
        new BarObstacle(
            12 * BarObstacle.THICKNESS, BarObstacle.THICKNESS, Orientation.VERTICAL, 4));
    // obsTopRight
    this.obstacles.add(
        new BarObstacle(
            36 * BarObstacle.THICKNESS, BarObstacle.THICKNESS, Orientation.VERTICAL, 4));
    // obsBottomLeft
    this.obstacles.add(
        new BarObstacle(
            12 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, Orientation.VERTICAL, 4));
    // obsBottomRight
    this.obstacles.add(
        new BarObstacle(
            36 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, Orientation.VERTICAL, 4));
    // obsTopMiddle
    this.obstacles.add(
        new BarObstacle(
            16 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, Orientation.HORIZONTAL, 17));
    // obsBottomMiddle
    this.obstacles.add(
        new BarObstacle(
            16 * BarObstacle.THICKNESS,
            600 - 4 * BarObstacle.THICKNESS,
            Orientation.HORIZONTAL,
            17));
    // obsLMTop
    this.obstacles.add(
        new BarObstacle(
            8 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // obsLMTop4
    this.obstacles.add(
        new BarObstacle(
            8 * BarObstacle.THICKNESS, 12 * BarObstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // obsLMBottom
    this.obstacles.add(
        new BarObstacle(
            8 * BarObstacle.THICKNESS, 16 * BarObstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // obsRMTop
    this.obstacles.add(
        new BarObstacle(
            36 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // obsRMTop2
    this.obstacles.add(
        new BarObstacle(
            36 * BarObstacle.THICKNESS, 12 * BarObstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // obsRMBottom
    this.obstacles.add(
        new BarObstacle(
            36 * BarObstacle.THICKNESS, 16 * BarObstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // LobsLeftTop1
    this.obstacles.add(
        new BarObstacle(
            4 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // LobsLeftTop2
    this.obstacles.add(
        new BarObstacle(
            4 * BarObstacle.THICKNESS, 5 * BarObstacle.THICKNESS, Orientation.VERTICAL, 6));
    // LobsLeftBottom1
    this.obstacles.add(
        new BarObstacle(
            4 * BarObstacle.THICKNESS, 600 - 4 * BarObstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // LobsLeftBottom2
    this.obstacles.add(
        new BarObstacle(
            4 * BarObstacle.THICKNESS, 600 - 10 * BarObstacle.THICKNESS, Orientation.VERTICAL, 6));
    // LobsRightTop1
    this.obstacles.add(
        new BarObstacle(
            40 * BarObstacle.THICKNESS, 4 * BarObstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // LobsRightTop2
    this.obstacles.add(
        new BarObstacle(
            44 * BarObstacle.THICKNESS, 5 * BarObstacle.THICKNESS, Orientation.VERTICAL, 6));
    // LobsRightBottom1
    this.obstacles.add(
        new BarObstacle(
            40 * BarObstacle.THICKNESS,
            600 - 4 * BarObstacle.THICKNESS,
            Orientation.HORIZONTAL,
            5));
    // LobsRightBottom2
    this.obstacles.add(
        new BarObstacle(
            44 * BarObstacle.THICKNESS, 600 - 10 * BarObstacle.THICKNESS, Orientation.VERTICAL, 6));
    // cageBottom
    this.obstacles.add(
        new BarObstacle(
            16 * BarObstacle.THICKNESS,
            600 - 8 * BarObstacle.THICKNESS,
            Orientation.HORIZONTAL,
            17));
    // cageRightV
    this.obstacles.add(
        new BarObstacle(
            32 * BarObstacle.THICKNESS, 600 - 16 * BarObstacle.THICKNESS, Orientation.VERTICAL, 8));
    // cageLeftV
    this.obstacles.add(
        new BarObstacle(
            16 * BarObstacle.THICKNESS, 600 - 16 * BarObstacle.THICKNESS, Orientation.VERTICAL, 8));
    // cateRightH
    this.obstacles.add(
        new BarObstacle(
            17 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, Orientation.HORIZONTAL, 5));
    // cateLeftH
    this.obstacles.add(
        new BarObstacle(
            27 * BarObstacle.THICKNESS, 8 * BarObstacle.THICKNESS, Orientation.HORIZONTAL, 5));

    root.getChildren().addAll(obstacles);
  }
}
