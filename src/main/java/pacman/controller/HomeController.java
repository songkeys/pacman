package pacman.controller;

import javafx.fxml.FXML;
import pacman.util.SceneSwitch;

public class HomeController {

  @FXML
  protected void handleClickStart() throws Exception {
    SceneSwitch.INSTANCE.switchToSelect();
  }
}
