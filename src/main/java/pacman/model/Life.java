package pacman.model;

public class Life {
  private int total;
  private int remaining;

  public Life(int total) {
    this.total = total;
    this.remaining = total;
  }

  public Life() {
    this(3);
  }

  public void lose() {
    remaining--;
    if (remaining < 0) {
      remaining = 0;
    }
  }

  public void gain() {
    remaining++;
    if (remaining > total) {
      remaining = total;
    }
  }

  public int getRemaining() {
    return remaining;
  }

  public int getTotal() {
    return total;
  }
}
