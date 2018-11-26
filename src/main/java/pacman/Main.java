package pacman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage theStage) throws Exception {
//    Parent root = FXMLLoader.load(getClass().getResource("pacman.fxml"));
    theStage.setTitle("Pacman");

    Group root = new Group();
    Scene theScene = new Scene(root);
    theStage.setScene(theScene);

    Canvas canvas = new Canvas(1225, 600);
    root.getChildren().add(canvas);
    GameManager gameManager = new GameManager(root);

    gameManager.drawBoard();

    theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
    theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
    theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));

    theStage.show();
  }
}
