package pacman.util;

import java.util.Set;
import javafx.scene.layout.Pane;
import pacman.model.Cookie;
import pacman.model.Ghost;
import pacman.model.Obstacle;
import pacman.model.Pacman;
import pacman.model.Portal;

public class MapPainter {

  private Pane root;

  public MapPainter(Pane root) {
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

  public void drawPortals(Set<Portal> portals) {
    root.getChildren().addAll(portals);
  }
}
