package pacman.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.Base64;
import pacman.constant.FileName;
import pacman.model.Score;
import pacman.model.ScoreBoard;

/**
 *
 *
 * <h1>ScoreBoardReader</h1>
 *
 * <p>A {@link ScoreBoardReader} is an object of utility to read a {@link ScoreBoard} from a file.
 *
 * <p>Usage:
 *
 * <blockquote>
 *
 * <pre>
 *    ScoreBoardReader scoreBoardReader = new ScoreBoardReader(fileName);
 *    scoreBoardReader.read();
 *    ScoreBoard scoreBoard = scoreBoardReader.getScoreBoard();
 * </pre>
 *
 * </blockquote>
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see ScoreBoardWriter
 * @see ScoreBoard
 * @see Score
 */
public class ScoreBoardReader {

  /** The path of the score board file. */
  private String path;
  /** The score board read from the file. */
  private ScoreBoard scoreBoard;

  /**
   * Allocates a new {@link ScoreBoardReader}.
   *
   * @param fileName the file name of the score board file to be read
   */
  public ScoreBoardReader(String fileName) {
    this.path = FileName.SCORE_BOARD_PATH + fileName;
    this.scoreBoard = new ScoreBoard();
  }

  /**
   * Converts a byte string to a {@link Score} object and returns it.
   *
   * @param byteString a byte string in a line from a score board file
   * @return a {@link Score} object converted from the given byte string
   * @throws Exception if anything goes wrong with reading object
   */
  private static Score convertFromByteString(String byteString) throws Exception {
    final byte[] bytes = Base64.getDecoder().decode(byteString);
    try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = new ObjectInputStream(bis)) {
      return (Score) in.readObject();
    }
  }

  /** Reads the score board file. */
  public void read() {
    File f = new File(path);
    if (f.exists()) {
      try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        String line;
        while ((line = br.readLine()) != null) {
          try {
            Score score = convertFromByteString(line);
            scoreBoard.addScore(score);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Returns the {@link ScoreBoard} retrieved from the score board file.
   *
   * @return the {@link ScoreBoard} retrieved from the score board file
   */
  public ScoreBoard getScoreBoard() {
    return scoreBoard;
  }
}
