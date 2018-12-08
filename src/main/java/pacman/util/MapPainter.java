package pacman.util;

import java.util.Set;
import javafx.scene.Group;
import pacman.model.Cookie;
import pacman.model.Ghost;
import pacman.model.Obstacle;
import pacman.model.Pacman;

public class MapPainter {

  private Group root;

  public MapPainter(Group root) {
    this.root = root;
  }

  public void drawObstacles(Set<Obstacle> obstacles) {
    root.getChildren().addAll(obstacles);
  }

  public void drawPacman(Pacman pacman) {
    root.getChildren().add(pacman);
  }

  public void drawCookies(Set<Cookie> cookies) {
    root.getChildren().addAll(cookies);
  }

  public void drawGhost(Set<Ghost> ghosts) {
    root.getChildren().addAll(ghosts);
  }
}
