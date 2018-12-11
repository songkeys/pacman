package pacman;

import javafx.application.Application;
import javafx.stage.Stage;
import pacman.util.SceneSwitch;

public class Main extends Application {

  private static Stage primaryStage;

  public static void main(String[] args) {
    launch(args);
  }

  public static Stage getPrimaryStage() {
    return primaryStage;
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;

    primaryStage.setResizable(false);
    primaryStage.setTitle("Pacman");

    // set up start scene
    SceneSwitch.INSTANCE.switchToHome();
  }
}
