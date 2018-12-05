package pacman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  private static Stage primaryStage;

  public static void main(String[] args) {
    launch(args);
  }

  // usage: Main.getPrimaryStage().setScene(newScene);
  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;

    primaryStage.setResizable(false);
    primaryStage.setTitle("Pacman");

    // set up start scene
    Parent root = FXMLLoader.load(getClass().getResource("/pacman/view/start.fxml"));
    Scene startScene = new Scene(root);
    primaryStage.setScene(startScene);

    primaryStage.show();
  }
}
