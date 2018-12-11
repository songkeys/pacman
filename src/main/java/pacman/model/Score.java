package pacman.model;

import java.io.Serializable;
import java.util.Date;

public class Score implements Serializable {
  private int value;
  private String nickname;
  private Date time;

  public Score(String nickname) {
    this.value = 0;
    this.nickname = nickname;
    this.time = new Date();
  }

  public Score() {
    this("Unknown Player");
  }

  public void gain(int value) {
    this.value += value;
  }

  public void lose(int value) {
    this.value -= value;
  }

  public void settle() {
    this.time = new Date();
  }

  public int getValue() {
    return value;
  }

  public String getNickname() {
    return nickname;
  }

  public Date getTime() {
    return time;
  }
}
