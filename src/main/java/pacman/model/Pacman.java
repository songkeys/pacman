package pacman.model;

import java.util.Set;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import pacman.constant.FileName;
import pacman.constant.MapConfig;

public class Pacman extends MovableGrid {

  public Pacman(double x, double y) {
    super(x, y);

    // set image
    Image image = new Image(FileName.IMAGE_PACMAN);
    this.setFill(new ImagePattern(image));
  }

  @Override
  public void handleMove(double x, double y) {
    Pacman.this.checkTouchingCookies();
  }

  @Override
  public void handleMoveRight(double x, double y) {
    Pacman.this.setRotate(0);
  }

  @Override
  public void handleMoveLeft(double x, double y) {
    Pacman.this.setRotate(180);
  }

  @Override
  public void handleMoveUp(double x, double y) {
    Pacman.this.setRotate(270);
  }

  @Override
  public void handleMoveDown(double x, double y) {
    Pacman.this.setRotate(90);
  }

  private void checkTouchingCookies() {
    Set<Cookie> cookies = Pacman.this.getParentMap().getCookies();
    for (Cookie cookie : cookies) {
      if (this.isTouching(cookie, MapConfig.COOKIE_PADDING)) {
        cookie.hide();
        return;
      }
    }
  }
}
