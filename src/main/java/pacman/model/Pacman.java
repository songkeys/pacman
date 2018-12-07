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
            Pacman.this.setX(Pacman.this.getX() + MapConfig.STEP);
            break;
          case LEFT:
            Pacman.this.setX(Pacman.this.getX() - MapConfig.STEP);
            break;
          case UP:
            Pacman.this.setY(Pacman.this.getY() - MapConfig.STEP);
            break;
          case DOWN:
            Pacman.this.setY(Pacman.this.getY() + MapConfig.STEP);
            break;
        }
      }
    };
  }
}
