package pacman.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import pacman.model.Cookie;
import pacman.model.Ghost;
import pacman.model.Map;
import pacman.model.Obstacle;
import pacman.model.Pacman;
import pacman.model.Spawn;

public class MapReader {

  private String fileName;
  private int mazeLineCount;
  private Map map;
  private String title;
  private Set<Obstacle> obstacles;
  private Set<Cookie> cookies;
  private Set<Ghost> ghosts;
  private Pacman pacman;
  private Spawn spawn;

  public MapReader(String fileName, Map map) {
    this.fileName = fileName;
    this.map = map;
    this.mazeLineCount = 0;
    this.obstacles = new HashSet<>();
    this.cookies = new HashSet<>();
    this.ghosts = new HashSet<>();
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

  public String getTitle() {
    return title;
  }

  private boolean isPacmanGrid(String grid) {
    return grid.equals("@");
  }

  private boolean isGhostGrid(String grid) {
    return grid.equals("X");
  }

  private boolean isCookieGrid(String grid) {
    return grid.equals(".");
  }

  private boolean isObstacleGrid(String grid) {
    return grid.equals("#");
  }

  private boolean isCommentLine(String line) {
    return line.startsWith("/*") && line.endsWith("*/");
  }

  private boolean isEmptyLine(String line) {
    return line.replaceAll("\\s+", "").isEmpty();
  }

  private boolean isTitleLine(String line) {
    return line.startsWith("@TITLE ");
  }

  private void processLine(String line) {
    // check if the line is a comment or is empty
    if (isCommentLine(line) || isEmptyLine(line)) {
      return;
    }

    // check if the line is a title
    if (isTitleLine(line)) {
      title = line.replace("@TITLE ", "").trim();
      return;
    }

    // read every character in the line
    String[] grids = line.split("");
    int mazeGridCount = 0;
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
        Cookie cookie = new Cookie(map, mazeGridCount, mazeLineCount);
        cookies.add(cookie);
      }

      // ghost
      if (isGhostGrid(grid)) {
        Ghost ghost = new Ghost(map, mazeGridCount, mazeLineCount);
        ghosts.add(ghost);
      }

      mazeGridCount++;
    }
    mazeLineCount++;
  }

  public void readFile() throws Exception {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource("pacman/map/" + fileName);
    String path = resource.getPath();
    System.out.println(path);
    // FileReader fileReader = new FileReader("src/main/resources/pacman/map/level1.txt");
    FileReader fileReader = new FileReader(path);
    try (BufferedReader br = new BufferedReader(fileReader)) {
      String line;

      while ((line = br.readLine()) != null) {
        System.out.println(line);
        processLine(line);
      }
    } catch (Error e) {
      e.printStackTrace();
    }
  }
}
