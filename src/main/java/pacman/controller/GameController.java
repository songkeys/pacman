package pacman.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import pacman.constant.SceneName;
import pacman.util.SceneSwitch;

public class GameController {
  @FXML private Text title;

  @FXML private Text lifeCount;

  @FXML private Text scoreCount;

  @FXML
  public void setTitle(String title) {
    this.title.setText(title);
  }

  @FXML
  public void setLifeCount(int remaining, int total) {
    this.lifeCount.setText(remaining + "/" + total);
  }

  @FXML
  public void setScoreCount(int scoreCount) {
    this.scoreCount.setText(Integer.toString(scoreCount));
  }

  @FXML
  protected void handleClickHome() throws Exception {
    SceneSwitch sceneSwitch = new SceneSwitch();
    sceneSwitch.switchTo(SceneName.HOME);
  }
}