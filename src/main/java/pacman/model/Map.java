package pacman.model;

import java.util.Set;
import javafx.scene.layout.Pane;
import pacman.util.MapPainter;
import pacman.util.MapReader;

public class Map {

  private String mapFileName;
  private Set<Obstacle> obstacles;
  private Set<Cookie> cookies;
  private Set<Ghost> ghosts;
  private Pacman pacman;

  public Map(String mapFileName) {
    this.mapFileName = mapFileName;
  }

  public Pacman getPacman() {
    return pacman;
  }

  public void setPacman(Pacman pacman) {
    this.pacman = pacman;
  }

  public Set<Cookie> getCookies() {
    return cookies;
  }

  public void setCookies(Set<Cookie> cookies) {
    this.cookies = cookies;
  }

  public Set<Obstacle> getObstacles() {
    return obstacles;
  }

  public void setObstacles(Set<Obstacle> obstacles) {
    this.obstacles = obstacles;
  }

  public Set<Ghost> getGhosts() {
    return ghosts;
  }

  public void setGhosts(Set<Ghost> ghosts) {
    this.ghosts = ghosts;
  }

  private void read(String fileName) throws Exception {

    // read map
    MapReader mapReader = new MapReader(fileName, this);
    mapReader.readFile();

    // initialize grids
    obstacles = mapReader.getObstacles();
    cookies = mapReader.getCookies();
    pacman = mapReader.getPacman();
    ghosts = mapReader.getGhosts();
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
