package pacman.constant;

public class MapResolution {
  public static final double WIDTH = 1250; // recommend: 25 grids in x
  public static final double HEIGHT = 650; // recommend: 13 grids in y

  public static final double GRID_LENGTH = 50; // recommend: 50

  public static final double STEP = GRID_LENGTH / 10;

  public static final double COOKIE_PADDING = GRID_LENGTH * 0.3;
  public static final double GHOST_PADDING = GRID_LENGTH * 0.2;
}
