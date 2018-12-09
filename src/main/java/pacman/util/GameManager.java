package pacman.util;

import javafx.scene.input.KeyEvent;
import pacman.constant.GameStatus;
import pacman.model.Cookie;
import pacman.model.Ghost;
import pacman.model.Life;
import pacman.model.Map;
import pacman.model.Spawn;

public class GameManager {
  private Map map;
  private GameStatus gameStatus;
  private Life life;

  /** Constructor */
  public GameManager(Map map) {
    // add map
    this.map = map;
    map.setParentGameManager(this);

    // init game status
    this.gameStatus = GameStatus.PAUSE;

    // init life
    this.life = new Life();
  }

  public void startGame() {
    if (gameStatus.equals(GameStatus.PAUSE)) {
      wakeUpGhosts();
      gameStatus = GameStatus.START;
    }
  }

  public void pauseGame() {
    freezeGhosts();
    gameStatus = GameStatus.PAUSE;
  }

  public void loseGame() {
    freezeGhosts();
    gameStatus = GameStatus.LOSE;
  }

  public void winGame() {
    freezeGhosts();
    gameStatus = GameStatus.WIN;
  }

  public void handleGhostTouched() {
    sendPacmanToSpawn();
    life.lost();
    if (life.getRemain() <= 0) {
      loseGame();
    }
  }

  public void handleCookieTouched(Cookie cookie) {
    cookie.eat();
  }

  public void handleKeyPressed(KeyEvent event) {
    switch (event.getCode()) {
      case RIGHT:
        startGame();
        map.getPacman().moveRight.start();
        break;
      case LEFT:
        startGame();
        map.getPacman().moveLeft.start();
        break;
      case UP:
        startGame();
        map.getPacman().moveUp.start();
        break;
      case DOWN:
        startGame();
        map.getPacman().moveDown.start();
        break;
      case ESCAPE:
        pauseGame();
        break;
      default:
        startGame();
    }
  }

  public void handleKeyReleased(KeyEvent event) {
    switch (event.getCode()) {
      case RIGHT:
        map.getPacman().moveRight.stop();
        break;
      case LEFT:
        map.getPacman().moveLeft.stop();
        break;
      case UP:
        map.getPacman().moveUp.stop();
        break;
      case DOWN:
        map.getPacman().moveDown.stop();
        break;
    }
  }

  private void wakeUpGhosts() {
    for (Ghost ghost : map.getGhosts()) {
      ghost.run();
    }
  }

  private void freezeGhosts() {
    for (Ghost ghost : map.getGhosts()) {
      ghost.freeze();
    }
  }

  private void sendPacmanToSpawn() {
    new Animation().shakeStage();
    Spawn spawn = map.getSpawn();
    map.getPacman().setX(spawn.getX());
    map.getPacman().setY(spawn.getY());
  }
}
