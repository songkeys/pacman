package pacman.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class GameController {
  @FXML
  private Text title;

  @FXML
  public void setTitle(String title) {
    this.title.setText(title);
  }
}
