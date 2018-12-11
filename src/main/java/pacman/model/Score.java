package pacman.model;

import java.util.Date;

public class Score {
  private int value;
  private String nickName;
  private Date time;

  public Score(String nickName) {
    this.value = 0;
    this.nickName = nickName;
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

  public String getNickName() {
    return nickName;
  }

  public Date getTime() {
    return time;
  }
}
