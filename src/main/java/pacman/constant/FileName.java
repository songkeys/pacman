package pacman.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FileName {
  public static final String MAP_LEVEL_1 = "level1.txt";

  public static final String IMAGE_PACMAN = "/pacman/image/pacman.png";
  public static final String IMAGE_COOKIE_SMALL = "/pacman/image/cookie1.png";
  public static final String IMAGE_COOKIE_MEDIUM = "/pacman/image/cookie5.png";
  public static final String IMAGE_COOKIE_BIG = "/pacman/image/cookie10.png";
  public static final String IMAGE_OBSTACLE = "/pacman/image/obstacle.png";
  public static final Set<String> IMAGE_GHOSTS =
      new HashSet<>(
          Arrays.asList(
              "/pacman/image/ghost1.png",
              "/pacman/image/ghost2.png",
              "/pacman/image/ghost3.png",
              "/pacman/image/ghost4.png",
              "/pacman/image/ghost5.png",
              "/pacman/image/ghost6.png",
              "/pacman/image/ghost7.png",
              "/pacman/image/ghost8.png",
              "/pacman/image/ghost9.png",
              "/pacman/image/ghost10.png"));

  public static final String VIEW_START = "/pacman/view/home.fxml";
  public static final String VIEW_GAME = "/pacman/view/game.fxml";
}
