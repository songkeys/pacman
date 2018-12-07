package pacman.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import pacman.model.Cookie;
import pacman.model.Obstacle;
import pacman.model.Pacman;

public class MapReader {

  private String fileName;
  private int mazeLineCount;
  private Set<Obstacle> obstacles;
  private Pacman pacman;
  private Set<Cookie> cookies;

  public MapReader(String fileName) {
    this.fileName = fileName;
    this.obstacles = new HashSet<>();
    this.cookies = new HashSet<>();
    this.mazeLineCount = 0;
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

  private void processLine(String line) {
    // check if the line is a comment or is empty
    if (isCommentLine(line) || isEmptyLine(line)) {
      return;
    }

    // read every character in the line
    String[] grids = line.split("");
    int mazeGridCount = 0;
    for (String grid : grids) {

      // obstacle
      if (isObstacleGrid(grid)) {
        Obstacle obstacle = new Obstacle(mazeGridCount, mazeLineCount);
        this.obstacles.add(obstacle);
      }

      // pacman
      if (isPacmanGrid(grid)) {
        this.pacman = new Pacman(mazeGridCount, mazeLineCount);
      }

      // cookie
      if (isCookieGrid(grid)) {
        Cookie cookie = new Cookie(mazeGridCount, mazeLineCount);
        this.cookies.add(cookie);
      }

      // ghost
      if (isGhostGrid(grid)) {}

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
