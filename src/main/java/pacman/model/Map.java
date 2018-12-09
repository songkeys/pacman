package pacman.model;

import java.util.Set;
import javafx.scene.layout.Pane;
import pacman.util.GameManager;
import pacman.util.MapPainter;
import pacman.util.MapReader;

public class Map {

  private String mapFileName;
  private GameManager parentGameManager;
  private Set<Obstacle> obstacles;
  private Set<Cookie> cookies;
  private Set<Ghost> ghosts;
  private Pacman pacman;
  private Spawn spawn;
  private String title;

  public Map(String mapFileName) {
    this.mapFileName = mapFileName;
  }

  public GameManager getParentGameManager() {
    return parentGameManager;
  }

  public void setParentGameManager(GameManager parentGameManager) {
    this.parentGameManager = parentGameManager;
  }

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

  public String getTitle() {
    return title;
  }

  private void read(String fileName) throws Exception {

    // read map
    MapReader mapReader = new MapReader(fileName, this);
    mapReader.readFile();

    // get config
    title = mapReader.getTitle();

    // initialize grids
    obstacles = mapReader.getObstacles();
    cookies = mapReader.getCookies();
    pacman = mapReader.getPacman();
    ghosts = mapReader.getGhosts();
    spawn = mapReader.getSpawn();
  }

  public void draw(Pane root) throws Exception {

    // read map
    this.read(this.mapFileName);

    // build map
    MapPainter mapPainter = new MapPainter(root);
    mapPainter.drawObstacles(obstacles);
    mapPainter.drawCookies(cookies);
    mapPainter.drawPacman(pacman);
    mapPainter.drawGhost(ghosts);
  }
}
