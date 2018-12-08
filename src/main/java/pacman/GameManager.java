package pacman;

import java.util.Set;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pacman.model.Cookie;
import pacman.model.Ghost;
import pacman.model.Map;
import pacman.model.Obstacle;
import pacman.model.Pacman;
import pacman.model.Score;

public class GameManager {

  private int lifes;
  private int score;
  private Score scoreBoard;
  private boolean gameEnded;
  private int cookiesEaten;

  private Map map;

  /** Constructor */
  public GameManager(Map map) {
    this.map = map;
  }

  public void startGame() {

  }

  public void movePacman(KeyEvent event) {
    switch (event.getCode()) {
      case RIGHT:
        map.getPacman().moveRight.start();
        break;
      case LEFT:
        map.getPacman().moveLeft.start();
        break;
      case UP:
        map.getPacman().moveUp.start();
        break;
      case DOWN:
        map.getPacman().moveDown.start();
        break;
    }
  }

  public void stopPacman(KeyEvent event) {
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
//    javafx.scene.text.Text endGame = new javafx.scene.text.Text("Game Over, press ESC to restart");
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
