package pacman.model;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import pacman.constant.Direction;
import pacman.constant.FileName;
import pacman.constant.MapConfig;

public class Pacman extends Grid {

  public AnimationTimer moveLeft;
  public AnimationTimer moveRight;
  public AnimationTimer moveUp;
  public AnimationTimer moveDown;

  public Pacman(double x, double y) {
    super(x, y);

    // set image
    Image image = new Image(FileName.IMAGE_PACMAN);
    this.setFill(new ImagePattern(image));

    // set animation
    this.moveLeft = this.move(Direction.LEFT);
    this.moveRight = this.move(Direction.RIGHT);
    this.moveUp = this.move(Direction.UP);
    this.moveDown = this.move(Direction.DOWN);
  }

  private AnimationTimer move(Direction direction) {
    return new AnimationTimer() {
      public void handle(long currentNanoTime) {
        switch (direction) {
          case RIGHT:
            if (!Pacman.this.isTouchingObstacle(Direction.RIGHT)) {
              Pacman.this.setX(Pacman.this.getX() + MapConfig.STEP);
              Pacman.this.setRotate(0);
            }
            break;
          case LEFT:
            if (!Pacman.this.isTouchingObstacle(Direction.LEFT)) {
              Pacman.this.setX(Pacman.this.getX() - MapConfig.STEP);
              Pacman.this.setRotate(180);
            }
            break;
          case UP:
            if (!Pacman.this.isTouchingObstacle(Direction.UP)) {
              Pacman.this.setY(Pacman.this.getY() - MapConfig.STEP);
              Pacman.this.setRotate(270);
            }
            break;
          case DOWN:
            if (!Pacman.this.isTouchingObstacle(Direction.DOWN)) {
              Pacman.this.setY(Pacman.this.getY() + MapConfig.STEP);
              Pacman.this.setRotate(90);
            }
            break;
        }
      }
    };
  }

  public boolean isTouchingObstacle(Direction direction) {
    // generate a mock pacman at the next step
    double nextX = this.getX();
    double nextY = this.getY();
    switch (direction) {
      case RIGHT:
        nextX += MapConfig.STEP;
        break;
      case LEFT:
        nextX -= MapConfig.STEP;
        break;
      case UP:
        nextY -= MapConfig.STEP;
        break;
      case DOWN:
        nextY += MapConfig.STEP;
        break;
    }
    Grid nextPacman = new Grid(nextX / MapConfig.GRID_LENGTH, nextY / MapConfig.GRID_LENGTH);

    // check if the mock pacman overlaps any obstacle
    for (Obstacle obstacle : this.getParentMap().getObstacles()) {
      if (obstacle.isTouching(nextPacman, 5)) {
        return true;
      }
    }

    return false;
  }
}
