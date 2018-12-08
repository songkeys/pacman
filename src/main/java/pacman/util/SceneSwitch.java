package pacman.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
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
    this.hideStage();
    Parent root = FXMLLoader.load(getClass().getResource("/pacman/view/start.fxml"));
    Scene startScene = new Scene(root);
    setScene(startScene);
    showStage();
  }

  private void switchToGame() throws Exception {
    this.hideStage();
    Group root = new Group();
    Scene theScene = new Scene(root);
    setScene(theScene);

    Canvas canvas = new Canvas(MapConfig.WIDTH, MapConfig.HEIGHT);
    root.getChildren().add(canvas);
    //    GameManager gameManager = new GameManager(root);

    //    gameManager.drawMap();

    Map map = new Map(FileName.MAP_LEVEL_1);
    map.draw(root);

    theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> map.movePacman(event));
    theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> map.stopPacman(event));
    //    theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
    //    theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
    //    theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));
    showStage();
  }

  public void switchTo(SceneName sceneName) throws Exception {
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
  }
}
