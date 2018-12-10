package pacman.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class FileName {
  public static final String MAP_LEVEL_1 = "level1.txt";
  public static final Set<String> MAPS =
      new TreeSet<>(Arrays.asList("#1 So Easy.txt", "#2 Sooo Esay.txt"));

  public static final Set<String> IMAGE_BACKGROUNDS =
      new TreeSet<>(
          Arrays.asList(
              "/pacman/image/floor/bedrock.png",
              "/pacman/image/floor/dirt.png",
              "/pacman/image/floor/gravel.png",
              "/pacman/image/floor/packed_ice.png",
              "/pacman/image/floor/polished_andesite.png",
              "/pacman/image/floor/polished_diorite.png",
              "/pacman/image/floor/polished_granite.png",
              "/pacman/image/floor/red_concrete.png",
              "/pacman/image/floor/red_sand.png",
              "/pacman/image/floor/red_terracotta.png",
              "/pacman/image/floor/sandstone.png",
              "/pacman/image/floor/stone.png"));
  public static final Set<String> IMAGE_OBSTACLES =
      new TreeSet<>(
          Arrays.asList(
              "/pacman/image/obstacle/prismarine_bricks.png",
              "/pacman/image/obstacle/red_nether_bricks.png",
              "/pacman/image/obstacle/stone_bricks.png"));
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

  public static final String VIEW_HOME = "/pacman/view/home.fxml";
  public static final String VIEW_GAME = "/pacman/view/game.fxml";
  public static final String VIEW_SELECT = "/pacman/view/select.fxml";
}
