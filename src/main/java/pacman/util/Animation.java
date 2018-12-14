package pacman.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import pacman.Main;

/**
 *
 *
 * <h1>Animation</h1>
 *
 * <p>An {@link Animation} is an object of utility to do some animation with the game running.
 *
 * <p>Currently just has one method for fun.
 *
 * <p>Usage:
 *
 * <blockquote>
 *
 * <pre>
 *    Animation animation = new Animation(fileName);
 *    Animation.shakeStage();
 * </pre>
 *
 * </blockquote>
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see GameManager
 */
public class Animation {
  private int stageX = 0;
  private int stageY = 0;

  /** Shake the primary stage. */
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

    int cycleCount = 4;
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
