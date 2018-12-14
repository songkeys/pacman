package pacman.util;

import java.util.Set;
import javafx.scene.input.KeyEvent;
import pacman.constant.GameStatus;
import pacman.controller.GameController;
import pacman.model.Cookie;
import pacman.model.Ghost;
import pacman.model.Life;
import pacman.model.Map;
import pacman.model.Pacman;
import pacman.model.Score;
import pacman.model.Spawn;

/**
 *
 *
 * <h1>GameManager</h1>
 *
 * <p>A {@link GameManager} is an object of utility to manage the game status and process globally,
 * and reflect the realtime results in the UI.
 *
 * <p><b>Note:</b> this class is implemented an {@link Enum}, thus to be a singleton class.
 *
 * <p>Usage:
 *
 * <blockquote>
 *
 * <pre>
 *    GameManager.INSTANCE.{ANY_METHOD_HERE}()
 * </pre>
 *
 * </blockquote>
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see GameController
 * @see GameStatus
 */
public enum GameManager {
  /** The shared instance for global use. */
  INSTANCE;

  /** The current playing {@link Map}. */
  private Map map;

  /** The current {@link GameController}. */
  private GameController gameController;

  /** The current {@link GameStatus}. */
  private GameStatus gameStatus;

  /** The current {@link Life}. */
  private Life life;

  /** The current {@link Score}. */
  private Score score;

  /**
   * Initializes the game properties based on the given {@link Map} and updates UI via {@link
   * GameController}.
   *
   * @param map the current {@link Map}
   * @param gameController the {@link GameController} linked to the current displayed UI
   */
  public void init(Map map, GameController gameController) {
    // add map
    this.map = map;

    // add game controller
    this.gameController = gameController;

    // init game status
    this.gameStatus = GameStatus.PAUSE;

    // init life
    this.life = new Life();

    // init score
    this.score = new Score(map.getNickname());

    // init UI
    this.initUi();
  }

  /**
   * Returns the current {@link GameStatus}.
   *
   * @return the current {@link GameStatus}
   */
  public GameStatus getGameStatus() {
    return gameStatus;
  }

  /**
   * Starts the game.
   *
   * <p>This method calls {@link #wakeUpGhosts()} and set the {@link #gameStatus} to {@link
   * GameStatus#START}.
   */
  public void startGame() {
    if (gameStatus == GameStatus.PAUSE) {
      wakeUpGhosts();
      gameStatus = GameStatus.START;
    }
  }

  /**
   * Pauses the game.
   *
   * <p>This method calls {@link #freezeGhosts()} and set the {@link #gameStatus} to {@link
   * GameStatus#PAUSE}.
   */
  public void pauseGame() {
    freezeGhosts();
    gameStatus = GameStatus.PAUSE;
  }

  /**
   * Loses the game.
   *
   * <p>This method calls {@link #endGame()}, {@link #calculateScore()} and {@link #navigateBack()}
   * to the Select scene.
   */
  public void loseGame() {
    if (getGameStatus() == GameStatus.START) {
      endGame();
      calculateScore();
      navigateBack();
    }
  }

  /**
   * Wins the game.
   *
   * <p>This method calls {@link #endGame()}, {@link #calculateScore()} and {@link #navigateBack()}
   * to the Select scene.
   */
  public void winGame() {
    if (getGameStatus() == GameStatus.START) {
      endGame();
      calculateScore();
      navigateBack();
    }
  }

  /**
   * Ends the game.
   *
   * <p>This method calls {@link #freezeGhosts()} and set the {@link #gameStatus} to {@link
   * GameStatus#END}.
   */
  public void endGame() {
    freezeGhosts();
    gameStatus = GameStatus.END;
  }

  /**
   * This method is called when any {@link Ghost} is touching the {@link Pacman}.
   *
   * @param ghost the {@link Ghost} touching the {@link Pacman}
   */
  public void handleGhostTouched(Ghost ghost) {
    if (gameStatus == GameStatus.END) {
      return;
    }
    sendPacmanToSpawn();
    life.lose();
    score.lose(ghost.getValue());
    if (life.getRemaining() <= 0) {
      MusicPlayer.INSTANCE.playDeath();
      loseGame();
    }
    updateUi();
  }

  /**
   * This method is called when any {@link Cookie} is touching the {@link Pacman}.
   *
   * @param cookie the {@link Cookie} touching the {@link Pacman}
   */
  public void handleCookieTouched(Cookie cookie) {
    cookie.eat();
    MusicPlayer.INSTANCE.playChomp();
    score.gain(cookie.getValue());
    updateUi();
    checkWin();
  }

  /**
   * This method is called when any key is pressed.
   *
   * @param event the {@link KeyEvent} happens when the key is pressed.
   */
  public void handleKeyPressed(KeyEvent event) {
    if (gameStatus == GameStatus.END) {
      return;
    }
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

  /**
   * This method is called when any key is released.
   *
   * @param event the {@link KeyEvent} happens when the key is released.
   */
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
      default:
    }
  }

  /** Wakes up all {@link Ghost}s to make them move. */
  private void wakeUpGhosts() {
    for (Ghost ghost : map.getGhosts()) {
      ghost.run();
    }
  }

  /** Freezes all {@link Ghost}s to make them not able to move. */
  private void freezeGhosts() {
    for (Ghost ghost : map.getGhosts()) {
      ghost.freeze();
    }
  }

  /** Sends {@link Pacman} to the {@link Spawn}. */
  private void sendPacmanToSpawn() {
    new Animation().shakeStage();
    Spawn spawn = map.getSpawn();
    map.getPacman().setX(spawn.getX());
    map.getPacman().setY(spawn.getY());
  }

  /** Initializes the UI (title, score count, life count). */
  private void initUi() {
    gameController.setTitle(map.getMapConfig().getTitle());
    gameController.setScoreCount(0);
    gameController.setLifeCount(life.getRemaining(), life.getTotal());
  }

  /** Updates the UI (life count, score count). */
  private void updateUi() {
    gameController.setLifeCount(life.getRemaining(), life.getTotal());
    gameController.setScoreCount(score.getValue());
  }

  /** Calculates the final {@link Score} and writes it into a corresponding file. */
  private void calculateScore() {
    ScoreBoardWriter scoreBoardWriter =
        new ScoreBoardWriter(map.getMapConfig().getTitle() + ".txt");
    score.settle();
    scoreBoardWriter.write(score);
  }

  /** Tests if all cookies are eaten. If true, calls {@link #winGame()}. */
  private void checkWin() {
    // check if all cookie is touched
    boolean isAllEaten = true;
    Set<Cookie> cookies = map.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.isExisting()) {
        isAllEaten = false;
      }
    }
    if (isAllEaten) {
      winGame();
    }
  }

  /** Navigates back to the Select scene, and pops up the ScoreBoard stage at the same time. */
  private void navigateBack() {
    // navigate back to select
    SceneSwitch.INSTANCE.switchToSelect();

    // popup score board
    SceneSwitch.INSTANCE.popupScoreBoard(map.getMapConfig().getTitle());
  }
}
