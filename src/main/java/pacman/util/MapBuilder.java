package pacman.util;

import java.util.Set;
import javafx.scene.Group;
import pacman.model.Cookie;
import pacman.model.Ghost;
import pacman.model.Obstacle;
import pacman.model.Pacman;

public class MapBuilder {

  private Group root;

  public MapBuilder(Group root) {
    this.root = root;
  }

  public void buildObstacles(Set<Obstacle> obstacles) {
    root.getChildren().addAll(obstacles);
  }

  public void buildPacman(Pacman pacman) {
    root.getChildren().add(pacman);
  }

  public void buildCookies(Set<Cookie> cookies) {
    root.getChildren().addAll(cookies);
  }

  public void buildGhost(Set<Ghost> ghosts) {
    root.getChildren().addAll(ghosts);
  }
}
