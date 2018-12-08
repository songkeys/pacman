package pacman.model;

import java.util.Set;
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
        Set<Grid> obstacles = (Set<Grid>) (Set<?>) Pacman.this.getParentMap().getObstacles();

        switch (direction) {
          case RIGHT:
            if (!Pacman.this.isTouchingGrids(Direction.RIGHT, obstacles)) {
              Pacman.this.setX(Pacman.this.getX() + MapConfig.STEP);
              Pacman.this.setRotate(0);
            }
            break;
          case LEFT:
            if (!Pacman.this.isTouchingGrids(Direction.LEFT, obstacles)) {
              Pacman.this.setX(Pacman.this.getX() - MapConfig.STEP);
              Pacman.this.setRotate(180);
            }
            break;
          case UP:
            if (!Pacman.this.isTouchingGrids(Direction.UP, obstacles)) {
              Pacman.this.setY(Pacman.this.getY() - MapConfig.STEP);
              Pacman.this.setRotate(270);
            }
            break;
          case DOWN:
            if (!Pacman.this.isTouchingGrids(Direction.DOWN, obstacles)) {
              Pacman.this.setY(Pacman.this.getY() + MapConfig.STEP);
              Pacman.this.setRotate(90);
            }
            break;
        }

        Pacman.this.checkTouchingCookies();
      }
    };
  }

  private boolean isTouchingGrids(Direction direction, Set<Grid> grids, double padding) {
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
    for (Grid grid : grids) {
      if (grid.isTouching(nextPacman, padding)) {
        return true;
      }
    }

    return false;
  }

  private boolean isTouchingGrids(Direction direction, Set<Grid> grids) {
    return this.isTouchingGrids(direction, grids, 0);
  }

  private void checkTouchingCookies() {
    Set<Cookie> cookies =  Pacman.this.getParentMap().getCookies();
    for (Cookie cookie : cookies) {
      if (this.isTouching(cookie, MapConfig.COOKIE_PADDING)) {
        cookie.hide();
        return;
      }
    }
  }
}
