package pacman.model;

import java.util.Set;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import pacman.constant.Direction;
import pacman.constant.MapConfig;
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

  private void read(String fileName) throws Exception {

    // read map
    MapReader mapReader = new MapReader(fileName);
    mapReader.readFile();

    // initialize variables
    obstacles = mapReader.getObstacles();
    cookies = mapReader.getCookies();
    pacman = mapReader.getPacman();

    // give pacman obstacles to know where not to go
    pacman.setObstacles(obstacles);
  }

  public void draw(Group root) throws Exception {

    // read map
    this.read(this.mapFileName);

    // build map
    MapBuilder mapBuilder = new MapBuilder(root);
    mapBuilder.buildObstacles(this.obstacles);
    mapBuilder.buildCookies(this.cookies);
    mapBuilder.buildPacman(this.pacman);
  }

  // TODO: make it into GameManager
  public void movePacman(KeyEvent event) {
    switch (event.getCode()) {
      case RIGHT:
          pacman.moveRight.start();
        break;
      case LEFT:
          pacman.moveLeft.start();
        break;
      case UP:
          pacman.moveUp.start();
        break;
      case DOWN:
          pacman.moveDown.start();
        break;
    }
  }

  public void stopPacman(KeyEvent event) {
    switch (event.getCode()) {
      case RIGHT:
        pacman.moveRight.stop();
        break;
      case LEFT:
        pacman.moveLeft.stop();
        break;
      case UP:
        pacman.moveUp.stop();
        break;
      case DOWN:
        pacman.moveDown.stop();
        break;
    }
  }


}
