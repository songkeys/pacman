package pacman;

import java.util.HashSet;
import java.util.Set;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pacman.constant.Direction;
import pacman.model.Cookie;
import pacman.model.Ghost;
import pacman.model.Maze;
import pacman.model.Obstacle;
import pacman.model.Pacman;
import pacman.model.Score;

public class GameManager {

  private Pacman pacman;
  private Group root;
  private Set<Cookie> cookieSet;
  private Set<Ghost> ghosts;
  private AnimationTimer leftPacmanAnimation;
  private AnimationTimer rightPacmanAnimation;
  private AnimationTimer upPacmanAnimation;
  private AnimationTimer downPacmanAnimation;
  private Maze maze;
  private int lifes;
  private int score;
  private Score scoreBoard;
  private boolean gameEnded;
  private int cookiesEaten;

  /** Constructor */
  public GameManager(Group root) {
    this.root = root;
    this.maze = new Maze();
    this.pacman = new Pacman(2.5 * Obstacle.THICKNESS, 2.5 * Obstacle.THICKNESS);
    this.cookieSet = new HashSet<>();
    this.ghosts = new HashSet<>();
    this.leftPacmanAnimation = this.createAnimation("left");
    this.rightPacmanAnimation = this.createAnimation("right");
    this.upPacmanAnimation = this.createAnimation("up");
    this.downPacmanAnimation = this.createAnimation("down");
    this.lifes = 3;
    this.score = 0;
    this.cookiesEaten = 0;
  }

  /** Set one life less */
  private void lifeLost() {
    this.leftPacmanAnimation.stop();
    this.rightPacmanAnimation.stop();
    this.upPacmanAnimation.stop();
    this.downPacmanAnimation.stop();
    for (Ghost ghost : ghosts) {
      ghost.getAnimation().stop();
    }
    this.pacman.setX(2.5 * Obstacle.THICKNESS);
    this.pacman.setY(2.5 * Obstacle.THICKNESS);
    lifes--;
    score -= 10;
    this.scoreBoard.lifes.setText("Lifes: " + this.lifes);
    this.scoreBoard.score.setText("Score: " + this.score);
    if (lifes == 0) {
      this.endGame();
    }
  }

  /** Ends the game */
  private void endGame() {
    this.gameEnded = true;
    root.getChildren().remove(pacman);
    for (Ghost ghost : ghosts) {
      root.getChildren().remove(ghost);
    }
    javafx.scene.text.Text endGame = new javafx.scene.text.Text("Game Over, press ESC to restart");
    endGame.setX(Obstacle.THICKNESS * 3);
    endGame.setY(Obstacle.THICKNESS * 28);
    endGame.setFont(Font.font("Arial", 40));
    endGame.setFill(Color.ROYALBLUE);
    root.getChildren().remove(this.scoreBoard.score);
    root.getChildren().remove(this.scoreBoard.lifes);
    root.getChildren().add(endGame);
  }

  /** Restart the game */
  public void restartGame(KeyEvent event) throws Exception {
    if (event.getCode() == KeyCode.ESCAPE && gameEnded) {
      root.getChildren().clear();
      this.cookieSet.clear();
      this.ghosts.clear();
      this.drawMap();
      this.pacman.setX(2.5 * Obstacle.THICKNESS);
      this.pacman.setY(2.5 * Obstacle.THICKNESS);
      this.lifes = 3;
      this.score = 0;
      this.cookiesEaten = 0;
      gameEnded = false;
    }
  }

  /** Draws the map of the game with the cookies and the Pacman */
  public void drawMap() throws Exception {
    this.maze.draw(root);

    root.getChildren().add(this.pacman);
    this.generateGhosts();
    root.getChildren().addAll(this.ghosts);
    this.scoreBoard = new Score(root);
  }

  /** Generates the ghosts for the pacman! */
  public void generateGhosts() {
    this.ghosts.add(
        new Ghost(
            18.5 * Obstacle.THICKNESS, 12.5 * Obstacle.THICKNESS, Color.DEEPPINK, maze, this));
    this.ghosts.add(
        new Ghost(
            22.5 * Obstacle.THICKNESS, 12.5 * Obstacle.THICKNESS, Color.GREENYELLOW, maze, this));
    this.ghosts.add(
        new Ghost(28.5 * Obstacle.THICKNESS, 12.5 * Obstacle.THICKNESS, Color.BLACK, maze, this));
    this.ghosts.add(
        new Ghost(
            28.5 * Obstacle.THICKNESS, 9.5 * Obstacle.THICKNESS, Color.SPRINGGREEN, maze, this));
  }

  /** Moves the pacman */
  public void movePacman(KeyEvent event) {
    for (Ghost ghost : this.ghosts) {
      ghost.run();
    }
    switch (event.getCode()) {
      case RIGHT:
          this.rightPacmanAnimation.start();
        break;
      case LEFT:
          this.leftPacmanAnimation.start();
        break;
      case UP:
          this.upPacmanAnimation.start();
        break;
      case DOWN:
          this.downPacmanAnimation.start();
        break;
    }
  }

  /** Stops the pacman */
  public void stopPacman(KeyEvent event) {
    switch (event.getCode()) {
      case RIGHT:
        this.rightPacmanAnimation.stop();
        break;
      case LEFT:
        this.leftPacmanAnimation.stop();
        break;
      case UP:
        this.upPacmanAnimation.stop();
        break;
      case DOWN:
        this.downPacmanAnimation.stop();
        break;
    }
  }

  /** Creates an animation of the movement. */
  private AnimationTimer createAnimation(String direction) {
    double step = 5;
    return new AnimationTimer() {
      public void handle(long currentNanoTime) {
        switch (direction) {
          case "left":
            if (!maze.isTouching(pacman.getX() - pacman.getWidth(), pacman.getY(), 15)) {
              pacman.setX(pacman.getX() - step);
              checkCookieCoalition(pacman, "x");
              checkGhostCoalition();
            }
            break;
          case "right":
            if (!maze.isTouching(pacman.getX() + pacman.getWidth(), pacman.getY(), 15)) {
              pacman.setX(pacman.getX() + step);
              checkCookieCoalition(pacman, "x");
              checkGhostCoalition();
            }
            break;
          case "up":
            if (!maze.isTouching(pacman.getX(), pacman.getY() - pacman.getWidth(), 15)) {
              pacman.setY(pacman.getY() - step);
              checkCookieCoalition(pacman, "y");
              checkGhostCoalition();
            }
            break;
          case "down":
            if (!maze.isTouching(pacman.getX(), pacman.getY() + pacman.getWidth(), 15)) {
              pacman.setY(pacman.getY() + step);
              checkCookieCoalition(pacman, "y");
              checkGhostCoalition();
            }
            break;
        }
      }
    };
  }

  /** Checks if the Pacman touches cookies. */
  private void checkCookieCoalition(Pacman pacman, String axis) {
    double pacmanCenterY = pacman.getX();
    double pacmanCenterX = pacman.getX();
    double pacmanLeftEdge = pacmanCenterX - pacman.getWidth();
    double pacmanRightEdge = pacmanCenterX + pacman.getWidth();
    double pacmanTopEdge = pacmanCenterY - pacman.getWidth();
    double pacmanBottomEdge = pacmanCenterY + pacman.getWidth();
    for (Cookie cookie : cookieSet) {
      double cookieCenterX = cookie.getX();
      double cookieCenterY = cookie.getY();
      double cookieLeftEdge = cookieCenterX - cookie.getWidth();
      double cookieRightEdge = cookieCenterX + cookie.getWidth();
      double cookieTopEdge = cookieCenterY - cookie.getWidth();
      double cookieBottomEdge = cookieCenterY + cookie.getWidth();
      if (axis.equals("x")) {
        // pacman goes right
        if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge)
            && (pacmanRightEdge >= cookieLeftEdge && pacmanRightEdge <= cookieRightEdge)) {
          if (cookie.isVisible()) {
            this.score += cookie.getValue();
            this.cookiesEaten++;
          }
          cookie.hide();
        }
        // pacman goes left
        if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge)
            && (pacmanLeftEdge >= cookieLeftEdge && pacmanLeftEdge <= cookieRightEdge)) {
          if (cookie.isVisible()) {
            this.score += cookie.getValue();
            this.cookiesEaten++;
          }
          cookie.hide();
        }
      } else {
        // pacman goes up
        if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge)
            && (pacmanBottomEdge >= cookieTopEdge && pacmanBottomEdge <= cookieBottomEdge)) {
          if (cookie.isVisible()) {
            this.score += cookie.getValue();
            this.cookiesEaten++;
          }
          cookie.hide();
        }
        // pacman goes down
        if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge)
            && (pacmanTopEdge <= cookieBottomEdge && pacmanTopEdge >= cookieTopEdge)) {
          if (cookie.isVisible()) {
            this.score += cookie.getValue();
            this.cookiesEaten++;
          }
          cookie.hide();
        }
      }
      this.scoreBoard.score.setText("Score: " + this.score);
      if (this.cookiesEaten == this.cookieSet.size()) {
        this.endGame();
      }
    }
  }

  /** Checks if pacman is touching a ghost */
  public void checkGhostCoalition() {
    double pacmanCenterY = pacman.getY();
    double pacmanCenterX = pacman.getX();
    double pacmanLeftEdge = pacmanCenterX - pacman.getWidth();
    double pacmanRightEdge = pacmanCenterX + pacman.getWidth();
    double pacmanTopEdge = pacmanCenterY - pacman.getWidth();
    double pacmanBottomEdge = pacmanCenterY + pacman.getWidth();
    for (Ghost ghost : ghosts) {
      double ghostLeftEdge = ghost.getX();
      double ghostRightEdge = ghost.getX() + ghost.getWidth();
      double ghostTopEdge = ghost.getY();
      double ghostBottomEdge = ghost.getY() + ghost.getHeight();
      if ((pacmanLeftEdge <= ghostRightEdge && pacmanLeftEdge >= ghostLeftEdge)
          || (pacmanRightEdge >= ghostLeftEdge && pacmanRightEdge <= ghostRightEdge)) {
        if ((pacmanTopEdge <= ghostBottomEdge && pacmanTopEdge >= ghostTopEdge)
            || (pacmanBottomEdge >= ghostTopEdge && pacmanBottomEdge <= ghostBottomEdge)) {
          lifeLost();
        }
      }
    }
  }
}
