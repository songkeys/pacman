package pacman.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pacman.util.MusicPlayer;
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

  @FXML public Button music;

  /** Initializes the UI. */
  @FXML
  public void initialize() {
    updatesMusicBtn();
  }

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

  /**
   * Called when the music menu-item is clicked.
   *
   * <p>This method toggles the music and updates UI.
   */
  @FXML
  public void handleClickMusic() {
    MusicPlayer.INSTANCE.toggle();
    updatesMusicBtn();
  }

  /** Updates the music button UI. */
  private void updatesMusicBtn() {
    if (MusicPlayer.INSTANCE.isOn()) {
      music.setText("< Music: ON >");
    } else {
      music.setText("< Music: OFF >");
    }
  }
}
