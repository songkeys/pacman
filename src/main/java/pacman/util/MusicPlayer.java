package pacman.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import pacman.constant.FileName;

/**
 *
 *
 * <h1>SceneSwitch</h1>
 *
 * <p>A {@link MusicPlayer} is an object of utility to provide some methods to play sounds in the
 * game.
 *
 * <p><b>Note:</b> this class is implemented an {@link Enum}, thus to be a singleton class.
 *
 * <p>Usage:
 *
 * <blockquote>
 *
 * <pre>
 *    SceneSwitch.INSTANCE.{PLAY_ANYTHING}();
 * </pre>
 *
 * </blockquote>
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 */
public enum MusicPlayer {
  /** The shared instance for global use. */
  INSTANCE;

  /** The {@link MediaPlayer} for beginning. */
  private final MediaPlayer beginningPlayer;
  /** The {@link MediaPlayer} for chomp. */
  private final MediaPlayer chompPlayer;
  /** The {@link MediaPlayer} for death. */
  private final MediaPlayer deathPlayer;
  /** The {@link MediaPlayer} for setup. */
  private final MediaPlayer setupPlayer;
  /** Whether or not the music is on */
  private boolean isOn;

  /** The constructor initializes every {@link MediaPlayer} in this class. */
  MusicPlayer() {
    beginningPlayer = createPlayer(FileName.MUSIC_BEGINNING);
    chompPlayer = createPlayer(FileName.MUSIC_CHOMP);
    deathPlayer = createPlayer(FileName.MUSIC_DEATH);
    setupPlayer = createPlayer(FileName.MUSIC_SETUP);
    isOn = true;
  }

  /**
   * Creates a {@link MediaPlayer} based on the given filename.
   *
   * @param fileName the music file to be created into a {@link MediaPlayer}
   * @return a {@link MediaPlayer} based on the given filename
   */
  private MediaPlayer createPlayer(String fileName) {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(fileName);
    String path = null;
    try {
      path = Objects.requireNonNull(resource).toURI().getPath();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    Media sound = new Media(new File(Objects.requireNonNull(path)).toURI().toString());

    return new MediaPlayer(sound);
  }

  /**
   * Returns whether or not the music is on.
   *
   * @return {@code true} if the music is on; {@code false} otherwise.
   */
  public boolean isOn() {
    return isOn;
  }

  /** Toggles the music. */
  public void toggle() {
    isOn = !isOn;

    // stop all player
    beginningPlayer.stop();
    chompPlayer.stop();
    deathPlayer.stop();
    setupPlayer.stop();
  }

  /** Plays the beginning media. */
  public void playBeginning() {
    if (isOn) {
      beginningPlayer.seek(Duration.ZERO);
      beginningPlayer.play();
    }
  }

  /** Plays the chomp media. */
  public void playChomp() {
    if (isOn) {
      chompPlayer.seek(Duration.ZERO);
      chompPlayer.play();
    }
  }

  /** Plays the death media. */
  public void playDeath() {
    if (isOn) {
      deathPlayer.seek(Duration.ZERO);
      deathPlayer.play();
    }
  }

  /** Plays the setup media. */
  public void playSetup() {
    if (isOn) {
      setupPlayer.seek(Duration.ZERO);
      setupPlayer.play();
    }
  }
}
