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

public class ScoreBoardReader {

  private String path;
  private ScoreBoard scoreBoard;

  public ScoreBoardReader(String fileName) {
    this.path = FileName.SCORE_BOARD_PATH + fileName;
    this.scoreBoard = new ScoreBoard();
  }

  private static Score convertFromByteString(String byteString)
      throws IOException, ClassNotFoundException {
    final byte[] bytes = Base64.getDecoder().decode(byteString);
    try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = new ObjectInputStream(bis)) {
      return (Score) in.readObject();
    }
  }

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

  public ScoreBoard getScoreBoard() {
    return scoreBoard;
  }
}
