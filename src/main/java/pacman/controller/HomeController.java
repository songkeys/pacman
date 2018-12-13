package pacman.controller;

import javafx.fxml.FXML;
import pacman.util.SceneSwitch;

/**
 *
 *
 * <h1>HomeController</h1>
 *
 * <p>A {@link HomeController} is a controller for Home scene.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 */
public class HomeController {

  /**
   * Called when the start menu-item is clicked.
   *
   * <p>This method switches the scene to Select.
   */
  @FXML
  protected void handleClickStart() {
    SceneSwitch.INSTANCE.switchToSelect();
  }

  /**
   * Called when the exit menu-item is clicked.
   *
   * <p>This method switches the scene to Select.
   */
  @FXML
  public void handleClickExit() {
    SceneSwitch.INSTANCE.exitApplication();
  }
}
