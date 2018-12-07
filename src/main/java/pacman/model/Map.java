package pacman.model;

import java.util.Set;
import javafx.scene.Group;
import pacman.util.MapBuilder;
import pacman.util.MapReader;

public class Map {

  private String mapFileName;
  private Set<Obstacle> obstacles;
  private Pacman pacman;
  private Set<Cookie> cookies;

  public Map(String mapFileName) {
    this.mapFileName = mapFileName;
  }

  private void read(String fileName) throws Exception {

    // read map
    MapReader mapReader = new MapReader(fileName);
    mapReader.readFile();

    // initialize variables
    setObstacles(mapReader.getObstacles());
    setPacman(mapReader.getPacman());
    setCookies(mapReader.getCookies());
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

  public void draw(Group root) throws Exception {

    // read map
    this.read(this.mapFileName);

    // build map
    MapBuilder mapBuilder = new MapBuilder(root);
    mapBuilder.buildObstacles(this.obstacles);
    mapBuilder.buildPacman(this.pacman);
    mapBuilder.buildCookies(this.cookies);
  }
}
