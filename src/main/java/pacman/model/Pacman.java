package pacman.model;

import java.util.Set;
import pacman.constant.Direction;
import pacman.constant.FileName;
import pacman.constant.MovableGridType;
import pacman.util.GameManager;

public class Pacman extends MovableGrid {

  public Pacman(Map map, double row, double column) {
    super(map, row, column, MovableGridType.PACMAN);

    this.setImage(FileName.IMAGE_PACMAN);
  }

  @Override
  public void handleMove(Direction direction) {
    switch (direction) {
      case UP:
        Pacman.this.setRotate(270);
        break;
      case DOWN:
        Pacman.this.setRotate(90);
        break;
      case LEFT:
        Pacman.this.setRotate(180);
        break;
      case RIGHT:
        Pacman.this.setRotate(0);
        break;
    }
    Pacman.this.checkTouchingCookies();
  }

  private void checkTouchingCookies() {
    Set<Cookie> cookies = getParentMap().getCookies();
    for (Cookie cookie : cookies) {
      if (!cookie.isEaten()
          && isTouching(cookie, getParentMap().getMapConfig().getCookiePadding())) {
        GameManager.INSTANCE.handleCookieTouched(cookie);
        return;
      }
    }
  }
}
