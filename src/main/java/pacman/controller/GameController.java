package pacman.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import pacman.util.GameManager;
import pacman.util.SceneSwitch;

/**
 *
 *
 * <h1>GameController</h1>
 *
 * <p>A {@link GameController} is a controller for Game scene.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 */
public class GameController {

  /**
   * The title shown on the screen.
   */
  @FXML private Text title;

  /**
   * The count of life shown on the screen.
   */
  @FXML private Text lifeCount;

  /**
   * The count of score shown on the screen.
   */
  @FXML private Text scoreCount;

  /**
   * Changes the title shown on the screen.
   * @param title the desired title
   */
  @FXML
  public void setTitle(String title) {
    this.title.setText(title);
  }

  /**
   * Changes the count of life shown on the screen.
   * @param remaining remaining life
   * @param total total life
   */
  @FXML
  public void setLifeCount(int remaining, int total) {
    this.lifeCount.setText(remaining + "/" + total);
  }

  /**
   * Changes the count of score shown on the screen.
   * @param scoreCount the count of score
   */
  @FXML
  public void setScoreCount(int scoreCount) {
    this.scoreCount.setText(Integer.toString(scoreCount));
  }

  /**
   * Called when the home icon is clicked.
   *
   * This method ends the game and switch to Home scene.
   */
  @FXML
  protected void handleHomeClicked(){
    GameManager.INSTANCE.endGame();
    SceneSwitch.INSTANCE.switchToHome();
  }

  /**
   * Called when the score board icon is clicked.
   *
   * This method pauses the game and pops up a score board window.
   */
  @FXML
  protected void handleScoreBoardClicked() {
    GameManager.INSTANCE.pauseGame();
    SceneSwitch.INSTANCE.popupScoreBoard(title.getText());
  }
}
