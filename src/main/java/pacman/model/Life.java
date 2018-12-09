package pacman.model;

public class Life {
  private int total;
  private int remain;

  public Life(int total) {
    this.total = total;
    this.remain = total;
  }

  public Life() {
    this(3);
  }

  public void lost() {
    remain--;
    if (remain < 0) {
      remain = 0;
    }
  }

  public void gain() {
    remain++;
    if (remain > total) {
      remain = total;
    }
  }

  public int getRemain() {
    return remain;
  }

  public int getTotal() {
    return total;
  }
}
