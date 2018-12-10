package pacman.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import pacman.constant.FileName;
import pacman.model.ScoreBoard;

public class ScoreBoardReader {

  private String path;
  private ScoreBoard scoreBoard;

  public ScoreBoardReader(String fileName) {
    this.path = FileName.SCORE_BOARD_PATH + fileName;
    String title = fileName.substring(0, fileName.lastIndexOf("."));
    this.scoreBoard = new ScoreBoard(title);
  }

  public void read() {
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] country = line.split(","); // use comma as separator
        System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
      }
    } catch (IOException e) {
      // file not found
    }
  }

  public ScoreBoard getScoreBoard() {
    return scoreBoard;
  }
}
