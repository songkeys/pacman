package pacman.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import pacman.Main;
import pacman.constant.FileName;
import pacman.constant.MapResolution;
import pacman.controller.GameController;
import pacman.model.Map;

public enum SceneSwitch {
  INSTANCE;

  private void hideStage() {
    Main.getPrimaryStage().hide();
  }

  private void showStage() {
    Main.getPrimaryStage().show();
  }

  private void setScene(Scene scene) {
    Main.getPrimaryStage().setScene(scene);
  }

  public void switchToHome() throws Exception {
    hideStage();
    Pane root = FXMLLoader.load(getClass().getResource(FileName.VIEW_HOME));
    Scene scene = new Scene(root);
    setScene(scene);
    showStage();
  }

  public void switchToSelect() throws Exception {
    hideStage();
    Pane root = FXMLLoader.load(getClass().getResource(FileName.VIEW_SELECT));
    Scene scene = new Scene(root);
    setScene(scene);
    showStage();
  }

  public void switchToGame(Map map) throws Exception {
    hideStage();
    FXMLLoader loader = new FXMLLoader(getClass().getResource(FileName.VIEW_GAME));
    Pane root = loader.load();
    Scene gameScene = new Scene(root);
    setScene(gameScene);

    Pane mapPane = (Pane) gameScene.lookup("#map");
    Canvas canvas = new Canvas(MapResolution.WIDTH, MapResolution.HEIGHT);
    mapPane.getChildren().add(canvas);
    map.draw(mapPane);
    mapPane.setStyle(
        "-fx-background-image: url('"
            + map.getBackgroundFileName()
            + "');"
            + "-fx-background-size: "
            + map.getMapConfig().getGridLength()
            + "px "
            + map.getMapConfig().getGridLength()
            + "px;");

    GameController gameController = loader.getController();
    GameManager.INSTANCE.init(map, gameController);

    gameScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> GameManager.INSTANCE.handleKeyPressed(event));
    gameScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> GameManager.INSTANCE.handleKeyReleased(event));
    showStage();
  }
}
