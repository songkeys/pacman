package pacman.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import pacman.constant.MapResolution;
import pacman.constant.PortalType;
import pacman.model.Cookie;
import pacman.model.Ghost;
import pacman.model.Map;
import pacman.model.MapConfig;
import pacman.model.Obstacle;
import pacman.model.Pacman;
import pacman.model.Portal;
import pacman.model.Spawn;

public class MapReader {

  private String fileName;
  private int mazeLineCount;
  private int mazeGridCount;
  private Map map;
  private MapConfig mapConfig;
  private Set<Obstacle> obstacles;
  private Set<Cookie> cookies;
  private Set<Ghost> ghosts;
  private Pacman pacman;
  private Spawn spawn;
  private Portal portalA;
  private Portal portalB;

  public MapReader(String fileName, Map map) {
    this.fileName = fileName;
    this.map = map;
    this.mazeLineCount = 0;
    this.mazeGridCount = 0;
    this.obstacles = new HashSet<>();
    this.cookies = new HashSet<>();
    this.ghosts = new HashSet<>();
    this.mapConfig = new MapConfig(50);

    // set title
    String title = fileName.substring(fileName.lastIndexOf("/") + 1); // remove path
    title = title.substring(0, title.lastIndexOf(".")); // remove type suffix
    mapConfig.setTitle(title);
  }

  public Set<Obstacle> getObstacles() {
    return obstacles;
  }

  public Pacman getPacman() {
    return pacman;
  }

  public Set<Cookie> getCookies() {
    return cookies;
  }

  public Set<Ghost> getGhosts() {
    return ghosts;
  }

  public Spawn getSpawn() {
    return spawn;
  }

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

  public MapConfig getMapConfig() {
    return mapConfig;
  }

  private int getCookieScore(String cookieGrid) {
    if (cookieGrid.equals(".")) {
      return 1;
    } else if (cookieGrid.equals("o")) {
      return 5;
    } else if (cookieGrid.equals("O")) {
      return 10;
    } else {
      return 0;
    }
  }

  private boolean isPacmanGrid(String grid) {
    return grid.equals("@");
  }

  private boolean isGhostGrid(String grid) {
    return grid.equals("X");
  }

  private boolean isCookieGrid(String grid) {
    return grid.equals(".") || grid.equals("o") || grid.equals("O");
  }

  private boolean isObstacleGrid(String grid) {
    return grid.equals("#");
  }

  private boolean isPortalAGrid(String grid) {
    return grid.equals("<");
  }

  private boolean isPortalBGrid(String grid) {
    return grid.equals(">");
  }

  private boolean isCommentLine(String line) {
    return line.startsWith("/*") && line.endsWith("*/");
  }

  private boolean isEmptyLine(String line) {
    return line.replaceAll("\\s+", "").isEmpty();
  }

  private boolean isPacmanStepRate(String line) {
    return line.startsWith("@PACMAN_STEP_RATE ");
  }

  private boolean isGhostStepRate(String line) {
    return line.startsWith("@GHOST_STEP_RATE ");
  }

  private boolean isCookiePaddingRate(String line) {
    return line.startsWith("@COOKIE_PADDING_RATE ");
  }

  private boolean isGhostPaddingRate(String line) {
    return line.startsWith("@GHOST_PADDING_RATE ");
  }

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
        Obstacle obstacle = new Obstacle(map, mazeGridCount, mazeLineCount);
        obstacles.add(obstacle);
      }

      // pacman
      if (isPacmanGrid(grid)) {
        pacman = new Pacman(map, mazeGridCount, mazeLineCount);
        spawn = new Spawn(map, mazeGridCount, mazeLineCount);
      }

      // cookie
      if (isCookieGrid(grid)) {
        Cookie cookie = new Cookie(map, mazeGridCount, mazeLineCount, getCookieScore(grid));
        cookies.add(cookie);
      }

      // ghost
      if (isGhostGrid(grid)) {
        Ghost ghost = new Ghost(map, mazeGridCount, mazeLineCount);
        ghosts.add(ghost);
      }

      // portalA
      if (isPortalAGrid(grid)) {
        portalA = new Portal(map, mazeGridCount, mazeLineCount, PortalType.A);
      }

      // portalB
      if (isPortalBGrid(grid)) {
        portalB = new Portal(map, mazeGridCount, mazeLineCount, PortalType.B);
      }

      mazeGridCount++;
    }
    mazeLineCount++;
    mazeGridCount = 0;
  }

  private void readFile(boolean isForConfig) {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(fileName);
    String path = null;
    try {
      path = resource.toURI().getPath();
    } catch (URISyntaxException e) {
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

  public void readFileForConfig() {
    readFile(true);
    mapConfig.calculate();
  }

  public void readFileForMap() {
    readFile(false);

    // link two portals
    if (portalA != null && portalB != null) {
      portalA.setTwinPortal(portalB);
      portalB.setTwinPortal(portalA);
    }
  }
}
