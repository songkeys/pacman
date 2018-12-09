package pacman.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import pacman.Main;
import pacman.constant.FileName;
import pacman.constant.MapConfig;
import pacman.constant.SceneName;
import pacman.controller.GameController;
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
    Pane root = FXMLLoader.load(getClass().getResource(FileName.VIEW_START));
    Scene startScene = new Scene(root);
    setScene(startScene);
  }

  private void switchToGame() throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource(FileName.VIEW_GAME));
    Pane root = loader.load();
    Scene gameScene = new Scene(root);
    setScene(gameScene);

    Pane mapPane = (Pane) gameScene.lookup("#map");
    Canvas canvas = new Canvas(MapConfig.WIDTH, MapConfig.HEIGHT);
    mapPane.getChildren().add(canvas);

    Map map = new Map(FileName.MAP_LEVEL_1);
    map.draw(mapPane);

    GameController gameController = loader.getController();
    GameManager gameManager = new GameManager(map, gameController);

    gameScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.handleKeyPressed(event));
    gameScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.handleKeyReleased(event));
  }

  public void switchTo(SceneName sceneName) throws Exception {
    hideStage();
    switch (sceneName) {
      case HOME:
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
