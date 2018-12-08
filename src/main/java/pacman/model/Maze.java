package pacman.model;

import java.util.HashSet;
import java.util.Set;
import javafx.scene.Group;
import pacman.util.MapReader;

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
          x >= obstacle.getX() - padding && x <= obstacle.getX() + obstacle.getWidth() + padding;
      boolean isBetweenHeight =
          y >= obstacle.getY() - padding && y <= obstacle.getY() + obstacle.getHeight() + padding;

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
  public void draw(Group root) throws Exception {
  }
}
