package pacman.controller;

import javafx.fxml.FXML;
import pacman.constant.SceneName;
import pacman.util.SceneSwitch;

public class HomeController {

  @FXML
  protected void handleClickStart() throws Exception {
    SceneSwitch sceneSwitch = new SceneSwitch();
    sceneSwitch.switchTo(SceneName.SELECT);
  }
}