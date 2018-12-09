package pacman.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import pacman.Main;

public class Animation {
  int stageX = 0;
  int stageY = 0;
  int cycleCount = 4;

  public void shakeStage() {
    Timeline timelineX =
        new Timeline(
            new KeyFrame(
                Duration.seconds(0.1),
                t -> {
                  if (stageX == 0) {
                    Main.getPrimaryStage().setX(Main.getPrimaryStage().getX() + 10);
                    stageX = 1;
                  } else {
                    Main.getPrimaryStage().setX(Main.getPrimaryStage().getX() - 10);
                    stageX = 0;
                  }
                }));

    timelineX.setCycleCount(cycleCount);
    timelineX.setAutoReverse(false);
    timelineX.play();

    Timeline timelineY =
        new Timeline(
            new KeyFrame(
                Duration.seconds(0.1),
                t -> {
                  if (stageY == 0) {
                    Main.getPrimaryStage().setY(Main.getPrimaryStage().getY() + 10);
                    stageY = 1;
                  } else {
                    Main.getPrimaryStage().setY(Main.getPrimaryStage().getY() - 10);
                    stageY = 0;
                  }
                }));

    timelineY.setCycleCount(cycleCount);
    timelineY.setAutoReverse(false);
    timelineY.play();
  }
}
