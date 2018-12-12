package pacman.model;

import java.util.Set;
import javafx.scene.layout.Pane;
import pacman.util.MapPainter;
import pacman.util.MapReader;

public class Map {

  private String fileName;
  private String nickname;
  private String backgroundFileName;
  private String wallFileName;

  private MapConfig mapConfig;

  private Set<Obstacle> obstacles;
  private Set<Cookie> cookies;
  private Set<Ghost> ghosts;
  private Pacman pacman;
  private Spawn spawn;
  private Set<Portal> portals;

  public Map() {}

  public Pacman getPacman() {
    return pacman;
  }

  public Set<Cookie> getCookies() {
    return cookies;
  }

  public Set<Obstacle> getObstacles() {
    return obstacles;
  }

  public Set<Ghost> getGhosts() {
    return ghosts;
  }

  public Spawn getSpawn() {
    return spawn;
  }

  public Set<Portal> getPortals() {
    return portals;
  }

  public MapConfig getMapConfig() {
    return mapConfig;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getBackgroundFileName() {
    return backgroundFileName;
  }

  public void setBackgroundFileName(String backgroundFileName) {
    this.backgroundFileName = backgroundFileName;
  }

  public String getWallFileName() {
    return wallFileName;
  }

  public void setWallFileName(String wallFileName) {
    this.wallFileName = wallFileName;
  }

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
