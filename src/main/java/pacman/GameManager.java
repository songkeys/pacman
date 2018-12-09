package pacman;

import javafx.scene.input.KeyEvent;
import pacman.constant.GameStatus;
import pacman.model.Ghost;
import pacman.model.Life;
import pacman.model.Map;
import pacman.model.Spawn;

public class GameManager {

  private int lifes;
  private int score;
  private boolean gameEnded;
  private int cookiesEaten;

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

  public void handleTouchedGhost() {
    sendPacmanToSpawn();
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
    Spawn spawn = map.getSpawn();
    map.getPacman().setX(spawn.getX());
    map.getPacman().setY(spawn.getY());
  }
  /** Set one life less */
  //  private void lifeLost() {
  //    for (Ghost ghost : ghosts) {
  //      //      ghost.getAnimation().stop();
  //    }
  //    this.pacman.setX(2.5 * Obstacle.THICKNESS);
  //    this.pacman.setY(2.5 * Obstacle.THICKNESS);
  //    lifes--;
  //    score -= 10;
  //    this.scoreBoard.lifes.setText("Lifes: " + this.lifes);
  //    this.scoreBoard.score.setText("Score: " + this.score);
  //    if (lifes == 0) {
  //      this.endGame();
  //    }
  //  }
  //
  //  /** Ends the game */
  //  private void endGame() {
  //    this.gameEnded = true;
  //    root.getChildren().remove(pacman);
  //    for (Ghost ghost : ghosts) {
  //      root.getChildren().remove(ghost);
  //    }
  //    javafx.scene.text.Text endGame = new javafx.scene.text.Text("Game Over, press ESC to
  // restart");
  //    endGame.setX(Obstacle.THICKNESS * 3);
  //    endGame.setY(Obstacle.THICKNESS * 28);
  //    endGame.setFont(Font.font("Arial", 40));
  //    endGame.setFill(Color.ROYALBLUE);
  //    root.getChildren().remove(this.scoreBoard.score);
  //    root.getChildren().remove(this.scoreBoard.lifes);
  //    root.getChildren().add(endGame);
  //  }
  //
  //  /** Restart the game */
  //  public void restartGame(KeyEvent event) throws Exception {
  //    if (event.getCode() == KeyCode.ESCAPE && gameEnded) {
  //      root.getChildren().clear();
  //      this.cookieSet.clear();
  //      this.ghosts.clear();
  //      this.pacman.setX(2.5 * Obstacle.THICKNESS);
  //      this.pacman.setY(2.5 * Obstacle.THICKNESS);
  //      this.lifes = 3;
  //      this.score = 0;
  //      this.cookiesEaten = 0;
  //      gameEnded = false;
  //    }
  //  }
}
