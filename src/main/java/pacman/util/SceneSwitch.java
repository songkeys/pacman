package pacman.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import pacman.GameManager;
import pacman.Main;
import pacman.constant.FileName;
import pacman.constant.MapConfig;
import pacman.constant.SceneName;
import pacman.model.Map;

public class SceneSwitch {

  private void hideStage() {
    Main.getPrimaryStage().hide();
  }

  private void showStage() {
    Main.getPrimaryStage().show();
  }

  private void setScene(Scene scene) {
    Main.getPrimaryStage().setScene(scene);
  }

  private void switchToStart() throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/pacman/view/start.fxml"));
    Scene startScene = new Scene(root);
    setScene(startScene);
  }

  private void switchToGame() throws Exception {
    Group root = new Group();
    Scene theScene = new Scene(root);
    setScene(theScene);

    Canvas canvas = new Canvas(MapConfig.WIDTH, MapConfig.HEIGHT);
    root.getChildren().add(canvas);

    Map map = new Map(FileName.MAP_LEVEL_1);
    map.draw(root);

    GameManager gameManager = new GameManager(map);

    theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
    theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
  }

  public void switchTo(SceneName sceneName) throws Exception {
    hideStage();
    switch (sceneName) {
      case START:
        switchToStart();
        break;
      case GAME:
        switchToGame();
        break;
      default:
        switchToStart();
    }
    showStage();
  }
}
