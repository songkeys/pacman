package pacman.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Base64;
import pacman.constant.FileName;
import pacman.model.Score;
import pacman.model.ScoreBoard;

/**
 *
 *
 * <h1>ScoreBoardWriter</h1>
 *
 * <p>A {@link ScoreBoardWriter} is an object of utility to write a {@link Score} to a score board
 * file.
 *
 * <p>Usage:
 *
 * <blockquote>
 *
 * <pre>
 *    ScoreBoardWriter scoreBoardWriter = new ScoreBoardWriter(fileName);
 *    scoreBoardWriter.write(score);
 * </pre>
 *
 * </blockquote>
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see ScoreBoardReader
 * @see ScoreBoard
 * @see Score
 */
public class ScoreBoardWriter {

  /** The path of the score board file. */
  private String path;

  /**
   * Allocates a new {@link ScoreBoardWriter}.
   *
   * @param fileName the file name of the score board file to be written
   */
  public ScoreBoardWriter(String fileName) {
    this.path = FileName.SCORE_BOARD_PATH + fileName;
  }

  /**
   * Converts a a {@link Score} object to a byte string and returns it.
   *
   * @param object a {@link Score} to be written in to the file
   * @return a {@link Score} object converted from the given byte string
   * @throws Exception if anything goes wrong with reading object
   */
  private static String convertToByteString(Score object) throws Exception {
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos)) {
      out.writeObject(object);
      final byte[] byteArray = bos.toByteArray();
      return Base64.getEncoder().encodeToString(byteArray);
    }
  }

  /**
   * Writes a new line to the file.
   * @param line a string line to be written.
   */
  private void writeNewLine(String line) {
    BufferedWriter writer;
    try {
      init();
      writer = new BufferedWriter(new FileWriter(path, true));
      writer.write(line);
      writer.newLine();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Initializes the file if it doesn't exist.
   */
  private void init() {
    File f = new File(path);

    f.getParentFile().mkdirs();
    if (!f.exists()) {
      try {
        f.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Writes the given {@link Score} to the score board file.
   * @param score a {@link Score} to be written to the file.
   */
  public void write(Score score) {
    try {
      writeNewLine(convertToByteString(score));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
