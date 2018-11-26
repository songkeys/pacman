package pacman;

import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ghost extends Rectangle implements Runnable {

  String direction;
  GameManager gameManager;
  Maze maze;
  AnimationTimer animation;
  int timesWalked;

  public Ghost(double x, double y, Color color, Maze maze, GameManager gameManager) {
    this.setX(x);
    this.setY(y);
    this.maze = maze;
    this.gameManager = gameManager;
    this.setHeight(50);
    this.setWidth(50);
    this.setFill(color);
    this.timesWalked = 0;
    this.direction = "down";
    this.createAnimation();
  }

  private String getRandomDirection(String exclude1, String exclude2) {
    String[] directions = {"left", "right", "up", "down"};
    int rnd = new Random().nextInt(directions.length);
    while (directions[rnd].equals(exclude1) || directions[rnd].equals(exclude2)) {
      rnd = new Random().nextInt(directions.length);
    }
    return directions[rnd];
  }

  private boolean getRandomBoolean() {
    Random rand = new Random();
    return rand.nextBoolean();
  }

  /** Gets the animation for the ghost */
  public AnimationTimer getAnimation() {
    return animation;
  }

  /** @param direction */
  private void checkIftheresPathToGo(String direction) {
    double rightEdge, leftEdge, topEdge, bottomEdge;
    switch (direction) {
      case "down":
        leftEdge = getX() - 10;
        bottomEdge = getY() + getHeight() + 15;
        rightEdge = getX() + getWidth() + 10;
        if (!maze.hasObstacle(leftEdge, rightEdge, bottomEdge - 1, bottomEdge)) {
          this.direction = direction;
        }
        break;
      case "up":
        leftEdge = getX() - 10;
        rightEdge = getX() + getWidth() + 10;
        topEdge = getY() - 15;
        if (!maze.hasObstacle(leftEdge, rightEdge, topEdge - 1, topEdge)) {
          this.direction = direction;
        }
        break;
      case "left":
        leftEdge = getX() - 15;
        bottomEdge = getY() + getHeight() + 10;
        topEdge = getY() - 10;
        if (!maze.hasObstacle(leftEdge - 1, leftEdge, topEdge, bottomEdge)) {
          this.direction = direction;
        }
        break;
      case "right":
        bottomEdge = getY() + getHeight() + 10;
        rightEdge = getX() + getWidth() + 15;
        topEdge = getY() - 10;
        if (!maze.hasObstacle(rightEdge - 1, rightEdge, topEdge, bottomEdge)) {
          this.direction = direction;
        }
        break;
    }
  }

  /**
   * @param whereToGo
   * @param whereToChangeTo
   * @param leftEdge
   * @param topEdge
   * @param rightEdge
   * @param bottomEdge
   * @param padding
   */
  private void moveUntilYouCant(
      String whereToGo,
      String whereToChangeTo,
      double leftEdge,
      double topEdge,
      double rightEdge,
      double bottomEdge,
      double padding) {
    double step = 5;
    switch (whereToGo) {
      case "left":
        if (!maze.isTouching(leftEdge, topEdge, padding)) {
          setX(leftEdge - step);
        } else {
          while (maze.isTouching(getX(), getY(), padding)) {
            setX(getX() + 1);
          }
          direction = whereToChangeTo;
        }
        break;
      case "right":
        if (!maze.isTouching(rightEdge, topEdge, padding)) {
          setX(leftEdge + step);
        } else {
          while (maze.isTouching(getX() + getWidth(), getY(), padding)) {
            setX(getX() - 1);
          }
          direction = whereToChangeTo;
        }
        break;
      case "up":
        if (!maze.isTouching(leftEdge, topEdge, padding)) {
          setY(topEdge - step);
        } else {
          while (maze.isTouching(getX(), getY(), padding)) {
            setY(getY() + 1);
          }
          direction = "left";
        }
        break;
      case "down":
        if (!maze.isTouching(leftEdge, bottomEdge, padding)) {
          setY(topEdge + step);
        } else {
          while (maze.isTouching(getX(), getY() + getHeight(), padding)) {
            setY(getY() - 1);
          }
          direction = "right";
        }
        break;
    }
  }

  /** Creates an animation of the ghost */
  public void createAnimation() {

    this.animation =
        new AnimationTimer() {
          public void handle(long currentNanoTime) {
            gameManager.checkGhostCoalition();
            double leftEdge = getX();
            double topEdge = getY();
            double rightEdge = getX() + getWidth();
            double bottomEdge = getY() + getHeight();
            double padding = 12;
            timesWalked++;
            int walkAtLeast = 4;
            switch (direction) {
              case "left":
                moveUntilYouCant("left", "down", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                if (timesWalked > walkAtLeast) {
                  checkIftheresPathToGo(getRandomDirection("left", "right"));
                  timesWalked = 0;
                }
                break;
              case "right":
                moveUntilYouCant("right", "up", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                if (timesWalked > walkAtLeast) {
                  checkIftheresPathToGo(getRandomDirection("left", "right"));
                  timesWalked = 0;
                }
                break;
              case "up":
                moveUntilYouCant("up", "left", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                if (timesWalked > walkAtLeast) {
                  checkIftheresPathToGo(getRandomDirection("up", "down"));
                  timesWalked = 0;
                }
                break;
              case "down":
                moveUntilYouCant(
                    "down", "right", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                if (timesWalked > walkAtLeast) {
                  checkIftheresPathToGo(getRandomDirection("up", "down"));
                  timesWalked = 0;
                }
                break;
            }
          }
        };
  }

  @Override
  public void run() {
    this.animation.start();
  }
}
