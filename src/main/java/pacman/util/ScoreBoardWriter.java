package pacman.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import pacman.constant.FileName;

public class ScoreBoardWriter {

  private String path;

  public ScoreBoardWriter(String fileName) {
    this.path = FileName.SCORE_BOARD_PATH + fileName;
  }

  private void writeNewLine(String line) throws Exception {
    BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
    writer.write(line);
    writer.newLine();
    writer.close();
  }

  public void init() {
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
}
