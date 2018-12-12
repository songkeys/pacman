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

public class ScoreBoardWriter {

  private String path;

  public ScoreBoardWriter(String fileName) {
    this.path = FileName.SCORE_BOARD_PATH + fileName;
  }

  private static String convertToByteString(Score object) throws IOException {
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos)) {
      out.writeObject(object);
      final byte[] byteArray = bos.toByteArray();
      return Base64.getEncoder().encodeToString(byteArray);
    }
  }

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

  public void write(Score score) {
    try {
      writeNewLine(convertToByteString(score));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
