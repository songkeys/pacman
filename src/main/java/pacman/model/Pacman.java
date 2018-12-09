package pacman.model;

import java.util.Set;
import pacman.constant.Direction;
import pacman.constant.FileName;
import pacman.constant.MapConfig;

public class Pacman extends MovableGrid {

  public Pacman(Map map, double row, double column) {
    super(map, row, column);

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
    boolean isAllEaten = true;
    Set<Cookie> cookies = getParentMap().getCookies();
    for (Cookie cookie : cookies) {
      if (!cookie.isEaten() && isTouching(cookie, MapConfig.COOKIE_PADDING)) {
        getParentMap().getParentGameManager().handleCookieTouched(cookie);
        return;
      }
      if (!cookie.isEaten()) {
        isAllEaten = false;
      }
    }

    // wining condition: all cookies is eaten
    if (isAllEaten) {
      getParentMap().getParentGameManager().winGame();
    }
  }
}
