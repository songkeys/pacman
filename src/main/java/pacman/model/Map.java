package pacman.model;

import java.util.Set;
import javafx.scene.layout.Pane;
import pacman.util.MapConfig;
import pacman.util.MapPainter;
import pacman.util.MapReader;

/**
 *
 *
 * <h1>Map</h1>
 *
 * <p>A {@link Map} is an object consisting of different types of {@link Grid}, a configuration
 * object {@link MapConfig} and some setup configurations.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see Grid
 * @see MovableGrid
 * @see Cookie
 * @see Ghost
 * @see Obstacle
 * @see Pacman
 * @see Portal
 * @see Spawn
 * @see MapConfig
 * @see MapReader
 * @see MapPainter
 */
public class Map {

  /** The filename of the map file. */
  private String fileName;
  /** The nickname of the player. */
  private String nickname;
  /** The filename of the background. */
  private String backgroundFileName;
  /** The filename of the walls. */
  private String wallFileName;

  /** The configuration object of this {@link Map}. */
  private MapConfig mapConfig;

  /** The {@link Obstacle} set in this {@link Map}. */
  private Set<Obstacle> obstacles;
  /** The {@link Cookie} set in this {@link Map}. */
  private Set<Cookie> cookies;
  /** The {@link Ghost} set in this {@link Map}. */
  private Set<Ghost> ghosts;
  /** The {@link Pacman} in this {@link Map}. */
  private Pacman pacman;
  /** The {@link Spawn} in this {@link Map}. */
  private Spawn spawn;
  /** The {@link Portal} set in this {@link Map}. */
  private Set<Portal> portals;

  /** Allocates a new {@link Map} object. */
  public Map() {}

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
   * Returns the {@link Obstacle} set in this {@link Map}.
   *
   * @return the {@link Obstacle} set in this {@link Map}
   */
  public Set<Obstacle> getObstacles() {
    return obstacles;
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
   * Returns the nickname of the player.
   *
   * @return the nickname of the player
   */
  public String getNickname() {
    return nickname;
  }

  /**
   * Changes the nickname of the player.
   *
   * @param nickname the nickname of the player
   */
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  /**
   * Changes the filename of the map file.
   *
   * @param fileName the filename of the map file
   */
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  /**
   * Returns the filename of the background.
   *
   * @return the filename of the background
   */
  public String getBackgroundFileName() {
    return backgroundFileName;
  }

  /**
   * Changes the filename of the background.
   *
   * @param backgroundFileName the filename of the background
   */
  public void setBackgroundFileName(String backgroundFileName) {
    this.backgroundFileName = backgroundFileName;
  }

  /**
   * Returns the filename of the walls.
   *
   * @return the filename of the walls
   */
  public String getWallFileName() {
    return wallFileName;
  }

  /**
   * Changes the filename of the walls.
   *
   * @param wallFileName the filename of the walls
   */
  public void setWallFileName(String wallFileName) {
    this.wallFileName = wallFileName;
  }

  /**
   * Reads the map file of {@link #fileName}.
   *
   * <p>This method uses {@link MapReader} to get results.
   */
  private void read() {

    // read map
    MapReader mapReader = new MapReader(fileName, this);

    // get config
    mapReader.readFileForConfig();
    mapConfig = mapReader.getMapConfig();

    // get grids
    mapReader.readFileForMap();
    obstacles = mapReader.getObstacles();
    cookies = mapReader.getCookies();
    pacman = mapReader.getPacman();
    ghosts = mapReader.getGhosts();
    spawn = mapReader.getSpawn();
    portals = mapReader.getPortals();
  }

  /**
   * Draws the map to the given {@link Pane} node.
   *
   * <p>This method calls {@link #read()} to get result first, then uses {@link MapPainter} to paint
   * the map.
   *
   * @param root a {@link Pane} that is expected to be drawn with the map on it
   */
  public void draw(Pane root) {

    // read map
    read();

    // paint map
    MapPainter mapPainter = new MapPainter(root);
    mapPainter.drawObstacles(obstacles);
    mapPainter.drawPortals(portals);
    mapPainter.drawCookies(cookies);
    mapPainter.drawPacman(pacman);
    mapPainter.drawGhost(ghosts);
  }
}
