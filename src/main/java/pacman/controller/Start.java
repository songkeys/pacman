package pacman.controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import pacman.GameManager;
import pacman.Main;

public class Start {
  @FXML
  protected void handleClickStart() {
    Main.getPrimaryStage().hide();
    Group root = new Group();
    Scene theScene = new Scene(root);
    Main.getPrimaryStage().setScene(theScene);

    Canvas canvas = new Canvas(1225, 600);
    root.getChildren().add(canvas);
    GameManager gameManager = new GameManager(root);

    gameManager.drawBoard();

    theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.movePacman(event));
    theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.stopPacman(event));
    theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.restartGame(event));
    Main.getPrimaryStage().show();
  }
}
