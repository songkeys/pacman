package pacman.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import pacman.constant.MapResolution;
import pacman.constant.PortalType;
import pacman.model.Cookie;
import pacman.model.Ghost;
import pacman.model.Map;
import pacman.model.Obstacle;
import pacman.model.Pacman;
import pacman.model.Portal;
import pacman.model.Spawn;

/**
 *
 *
 * <h1>MapReader</h1>
 *
 * <p>A {@link MapReader} is an object of utility to read a {@link Map} from a file.
 *
 * <p>An example map file:
 *
 * <blockquote>
 *
 * <pre>
 *{@literal /}* This is a comment line. *{@literal /}
 *{@literal @}PACMAN_PADDING_RATE 0.1
 *{@literal @}PACMAN_STEP_RATE 0.15
 *{@literal @}GHOST_PADDING_RATE 0.15
 *{@literal @}GHOST_STEP_RATE 0.18
 *{@literal @}COOKIE_PADDING_RATE 0.5
 *
 *#########################
 *#{@literal @}....#...........#.....#
 *#.###.#.#########.#.###.#
 *#.#...................#.#
 *#.#.###.###   ###.###.#.#
 *#.#.....#     X #.....#.#
 *{@literal <}...###.#       #.###...{@literal >}
 *#.#.....# X X X #.....#.#
 *#.#.###.#########.###.#.#
 *#.#...................#.#
 *#.###.#.#########.#.###.#
 *#.....#...........#.....#
 *#########################
 *
 * </pre>
 *
 * </blockquote>
 *
 * <p>Usage of this class:
 *
 * <blockquote>
 *
 * <pre>
 *    MapReader mapReader = new MapReader(fileName, map);
 *
 *    // get configurations
 *    mapReader.readFileForConfig();
 *    mapConfig = mapReader.getMapConfig();
 *
 *    // get grids
 *    mapReader.readFileForMap();
 *    obstacles = mapReader.getObstacles();
 *    cookies = mapReader.getCookies();
 *    pacman = mapReader.getPacman();
 *    ghosts = mapReader.getGhosts();
 *    spawn = mapReader.getSpawn();
 *    portals = mapReader.getPortals();
 * </pre>
 *
 * </blockquote>
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see Map
 * @see MapPainter
 * @see MapConfig
 */
public class MapReader {

  /** The filename of the map file. */
  private String fileName;

  /** The line count of the map. */
  private int lineCount;

  /** The grid count of the map. */
  private int gridCount;

  /** The map read from the map file. */
  private Map map;

  /** The map configuration. */
  private MapConfig mapConfig;

  /** The {@link Obstacle} set in the map. */
  private Set<Obstacle> obstacles;

  /** The {@link Cookie} set in the map. */
  private Set<Cookie> cookies;

  /** The {@link Ghost} set in the map. */
  private Set<Ghost> ghosts;

  /** The {@link Pacman} in the map. */
  private Pacman pacman;

  /** The {@link Spawn} in the map. */
  private Spawn spawn;

  /** The {@link Portal} A in the map. */
  private Portal portalA;

  /** The {@link Portal} B in the map. */
  private Portal portalB;

  /**
   * Allocates a new {@link MapReader} object.
   *
   * @param fileName the filename of the map file to be read
   * @param map the map object itself to be injected
   */
  public MapReader(String fileName, Map map) {
    this.fileName = fileName;
    this.map = map;
    this.lineCount = 0;
    this.gridCount = 0;
    this.obstacles = new HashSet<>();
    this.cookies = new HashSet<>();
    this.ghosts = new HashSet<>();
    this.mapConfig = new MapConfig(50);

    // set title
    String title = fileName.substring(fileName.lastIndexOf("/") + 1); // remove path
    title = title.substring(0, title.lastIndexOf(".")); // remove type suffix
    mapConfig.setTitle(title);
  }

  /**
   * Returns the {@link Obstacle} set in this {@link Map}.
   *
   * @return the {@link Obstacle} set in this {@link Map}
   */
  public Set<Obstacle> getObstacles() {
    return obstacles;
  }

  /**
   * Returns the {@link Pacman} in this {@link Map}.
   *
   * @return the {@link Pacman} in this {@link Map}
   */
  public Pacman getPacman() {
    return pacman;
  }

  /**
   * Returns the {@link Cookie} set in this {@link Map}.
   *
   * @return the {@link Cookie} set in this {@link Map}
   */
  public Set<Cookie> getCookies() {
    return cookies;
  }

  /**
   * Returns the {@link Ghost} set in this {@link Map}.
   *
   * @return the {@link Ghost} set in this {@link Map}
   */
  public Set<Ghost> getGhosts() {
    return ghosts;
  }

  /**
   * Returns the {@link Spawn} in this {@link Map}.
   *
   * @return the {@link Spawn} in this {@link Map}
   */
  public Spawn getSpawn() {
    return spawn;
  }

  /**
   * Returns the {@link Portal} set in this {@link Map}.
   *
   * @return the {@link Portal} set in this {@link Map}
   */
  public Set<Portal> getPortals() {
    Set<Portal> portals = new HashSet<>();
    if (portalA != null) {
      portals.add(portalA);
    }
    if (portalB != null) {
      portals.add(portalB);
    }

    return portals;
  }

  /**
   * Returns the configuration object {@link MapConfig} of this {@link Map}.
   *
   * @return the configuration object {@link MapConfig} of this {@link Map}
   */
  public MapConfig getMapConfig() {
    return mapConfig;
  }

  /**
   * Returns the score value based on a grid string letter.
   *
   * <p>{@code .} - a cookie with score value {@code 1}; {@code o} - a cookie with score value
   * {@code 5}; {@code O} - a cookie with score value {@code 10}. (Default case: {@code 0}.)
   *
   * @param cookieGrid a string letter representing a grid
   * @return the score value of a {@link Cookie}
   */
  private int getCookieScore(String cookieGrid) {
    switch (cookieGrid) {
      case ".":
        return 1;
      case "o":
        return 5;
      case "O":
        return 10;
      default:
        return 0;
    }
  }

  /**
   * Tests if a given string letter represents a {@link Pacman}.
   *
   * <p>{@code @} - a {@link Pacman}.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link Pacman}; {@code false}
   *     otherwise
   */
  private boolean isPacmanGrid(String grid) {
    return grid.equals("@");
  }

  /**
   * Tests if a given string letter represents a {@link Ghost}.
   *
   * <p>{@code @} - a {@link Ghost}.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link Ghost}; {@code false}
   *     otherwise
   */
  private boolean isGhostGrid(String grid) {
    return grid.equals("X");
  }

  /**
   * Tests if a given string letter represents a {@link Cookie}.
   *
   * <p>{@code .}, {@code o} or {@code O} - a {@link Cookie}.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link Cookie}; {@code false}
   *     otherwise
   */
  private boolean isCookieGrid(String grid) {
    return grid.equals(".") || grid.equals("o") || grid.equals("O");
  }

  /**
   * Tests if a given string letter represents a {@link Obstacle}.
   *
   * <p>{@code #} - a {@link Obstacle}.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link Obstacle}; {@code false}
   *     otherwise
   */
  private boolean isObstacleGrid(String grid) {
    return grid.equals("#");
  }

  /**
   * Tests if a given string letter represents a {@link Portal} A.
   *
   * <p>{@code <} - a {@link Portal} A.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link Portal} A; {@code false}
   *     otherwise
   */
  private boolean isPortalAGrid(String grid) {
    return grid.equals("<");
  }

  /**
   * Tests if a given string letter represents a {@link Portal} B.
   *
   * <p>{@code >} - a {@link Portal} B.
   *
   * @param grid a string letter representing a grid
   * @return {@code true} if the given string letter represents a {@link Portal} B; {@code false}
   *     otherwise
   */
  private boolean isPortalBGrid(String grid) {
    return grid.equals(">");
  }

  /**
   * Tests if a given string line represents a comment line.
   *
   * <p>A line starting with {@code \/*} and ending with {@code *\/} is recognised to be a comment
   * line.
   *
   * @param line a line string
   * @return {@code true} if the given string represents a comment line; {@code false} otherwise
   */
  private boolean isCommentLine(String line) {
    return line.startsWith("/*") && line.endsWith("*/");
  }

  /**
   * Tests if a given string line represents an empty line.
   *
   * <p>A line consisting of only spaces or nothing is recognised to be an empty line.
   *
   * @param line a line string
   * @return {@code true} if the given string represents an empty line; {@code false} otherwise
   */
  private boolean isEmptyLine(String line) {
    return line.replaceAll("\\s+", "").isEmpty();
  }

  /**
   * Tests if a given string line represents a configuration line for pacman step rate.
   *
   * <p>A line starting with {@code @PACMAN_STEP_RATE } is recognised to be a configuration line for
   * pacman step rate.
   *
   * @param line a line string
   * @return {@code true} if the given string represents a a configuration line for pacman step
   *     rate; {@code false} otherwise
   */
  private boolean isPacmanStepRate(String line) {
    return line.startsWith("@PACMAN_STEP_RATE ");
  }

  /**
   * Tests if a given string line represents a configuration line for ghost step rate.
   *
   * <p>A line starting with {@code @GHOST_STEP_RATE } is recognised to be a configuration line for
   * ghost step rate.
   *
   * @param line a line string
   * @return {@code true} if the given string represents a configuration line for ghost step rate;
   *     {@code false} otherwise
   */
  private boolean isGhostStepRate(String line) {
    return line.startsWith("@GHOST_STEP_RATE ");
  }

  /**
   * Tests if a given string line represents a configuration line for cookie padding rate.
   *
   * <p>A line starting with {@code @COOKIE_PADDING_RATE } is recognised to be a configuration line
   * for cookie padding rate.
   *
   * @param line a line string
   * @return {@code true} if the given string represents a configuration line for cookie padding
   *     rate; {@code false} otherwise
   */
  private boolean isCookiePaddingRate(String line) {
    return line.startsWith("@COOKIE_PADDING_RATE ");
  }

  /**
   * Tests if a given string line represents a configuration line for ghost padding rate.
   *
   * <p>A line starting with {@code @GHOST_PADDING_RATE } is recognised to be a configuration line
   * for ghost padding rate.
   *
   * @param line a line string
   * @return {@code true} if the given string represents a configuration line for ghost padding
   *     rate; {@code false} otherwise
   */
  private boolean isGhostPaddingRate(String line) {
    return line.startsWith("@GHOST_PADDING_RATE ");
  }

  /**
   * Analyses a given line for configurations.
   *
   * @param line a line string
   */
  private void processConfigLine(String line) {

    // check if the line is a comment or is empty
    if (isCommentLine(line) || isEmptyLine(line)) {
      return;
    }

    // pacman step rate
    if (isPacmanStepRate(line)) {
      double pacmanStepRate = Double.parseDouble(line.replace("@PACMAN_STEP_RATE ", "").trim());
      mapConfig.setPacmanStepRate(pacmanStepRate);
      return;
    }

    // ghost step rate
    if (isGhostStepRate(line)) {
      double ghostStepRate = Double.parseDouble(line.replace("@GHOST_STEP_RATE ", "").trim());
      mapConfig.setGhostStepRate(ghostStepRate);
      return;
    }

    // cookie padding rate
    if (isCookiePaddingRate(line)) {
      double cookiePaddingRate =
          Double.parseDouble(line.replace("@COOKIE_PADDING_RATE ", "").trim());
      mapConfig.setCookiePaddingRate(cookiePaddingRate);
      return;
    }

    // ghost padding rate
    if (isGhostPaddingRate((line))) {
      double ghostPaddingRate = Double.parseDouble(line.replace("@GHOST_PADDING_RATE ", "").trim());
      mapConfig.setGhostPaddingRate(ghostPaddingRate);
      return;
    }

    // grid length (in map lines)
    mapConfig.setGridLength(MapResolution.WIDTH / line.length());
  }

  /**
   * Analyses a given line for map.
   *
   * @param line a line string
   */
  private void processMapLine(String line) {
    // skip all not map lines
    if (isCommentLine(line)
        || isEmptyLine(line)
        || isPacmanStepRate(line)
        || isGhostStepRate(line)
        || isCookiePaddingRate(line)
        || isGhostPaddingRate(line)) {
      return;
    }

    // read every character in the line
    String[] grids = line.split("");
    for (String grid : grids) {

      // obstacle
      if (isObstacleGrid(grid)) {
        Obstacle obstacle = new Obstacle(map, gridCount, lineCount);
        obstacles.add(obstacle);
      }

      // pacman
      if (isPacmanGrid(grid)) {
        pacman = new Pacman(map, gridCount, lineCount);
        spawn = new Spawn(map, gridCount, lineCount);
      }

      // cookie
      if (isCookieGrid(grid)) {
        Cookie cookie = new Cookie(map, gridCount, lineCount, getCookieScore(grid));
        cookies.add(cookie);
      }

      // ghost
      if (isGhostGrid(grid)) {
        Ghost ghost = new Ghost(map, gridCount, lineCount);
        ghosts.add(ghost);
      }

      // portalA
      if (isPortalAGrid(grid)) {
        portalA = new Portal(map, gridCount, lineCount, PortalType.A);
      }

      // portalB
      if (isPortalBGrid(grid)) {
        portalB = new Portal(map, gridCount, lineCount, PortalType.B);
      }

      gridCount++;
    }
    lineCount++;
    gridCount = 0;
  }

  /**
   * Reads a map file and processes for the configurations or the map.
   *
   * @param isForConfig {@code true} this calling for reading is for configurations; {@code false}
   *     if for the map
   */
  private void readFile(boolean isForConfig) {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(fileName);
    String path = null;
    try {
      path = Objects.requireNonNull(resource).toURI().getPath();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;

      while ((line = br.readLine()) != null) {
        if (isForConfig) {
          processConfigLine(line);
        } else {
          processMapLine(line);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Reads the file for the configurations. */
  public void readFileForConfig() {
    readFile(true);
    mapConfig.calculate();
  }

  /** Reads the file for the map. */
  public void readFileForMap() {
    readFile(false);

    // link two portals
    if (portalA != null && portalB != null) {
      portalA.setTwinPortal(portalB);
      portalB.setTwinPortal(portalA);
    }
  }
}
