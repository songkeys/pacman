package pacman.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import pacman.GameManager;
import pacman.Main;
import pacman.constant.Map;
import pacman.constant.SceneName;

public class SceneSwitch {

  private void switchToStart() throws Exception {
    Main.getPrimaryStage().hide();
    Parent root = FXMLLoader.load(getClass().getResource("/pacman/view/start.fxml"));
    Scene startScene = new Scene(root);
    Main.getPrimaryStage().setScene(startScene);
    Main.getPrimaryStage().show();
  }

  private void switchToGame() throws Exception {
    Main.getPrimaryStage().hide();
    Group root = new Group();
    Scene theScene = new Scene(root);
    Main.getPrimaryStage().setScene(theScene);

    Canvas canvas = new Canvas(MapConfig.WIDTH, MapConfig.HEIGHT);
    root.getChildren().add(canvas);
    GameManager gameManager = new GameManager(root);

    gameManager.drawBoard();

    theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
    theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
//    theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));
    Main.getPrimaryStage().show();
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
