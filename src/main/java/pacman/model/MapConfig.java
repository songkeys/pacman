package pacman.model;

public class MapConfig {
  private String title;

  private double gridLength;
  private double pacmanStep;
  private double ghostStep;
  private double cookiePadding;
  private double ghostPadding;
  private double pacmanStepRate = 0.1;
  private double ghostStepRate = 0.1;
  private double cookiePaddingRate = 0.3;
  private double ghostPaddingRate = 0.2;

  public MapConfig(double gridLength) {
    this.gridLength = gridLength;
    this.calculate();
  }

  public void calculate() {
    updateGhostStep();
    updatePacmanStep();
    updateCookiePadding();
    updateGhostPadding();
  }

  private void updatePacmanStep() {
    pacmanStep = gridLength * pacmanStepRate;
  }

  private void updateGhostStep() {
    ghostStep = gridLength * ghostStepRate;
  }

  private void updateCookiePadding() {
    cookiePadding = gridLength * cookiePaddingRate;
  }

  private void updateGhostPadding() {
    ghostPadding = gridLength * ghostPaddingRate;
  }

  public double getCookiePadding() {
    return cookiePadding;
  }

  public double getGhostPadding() {
    return ghostPadding;
  }

  public double getGhostStep() {
    return ghostStep;
  }

  public double getPacmanStep() {
    return pacmanStep;
  }

  public double getGridLength() {
    return gridLength;
  }

  public void setGridLength(double gridLength) {
    this.gridLength = gridLength;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPacmanStepRate(double pacmanStepRate) {
    this.pacmanStepRate = pacmanStepRate;
    updatePacmanStep();
  }

  public void setGhostStepRate(double ghostStepRate) {
    this.ghostStepRate = ghostStepRate;
    updateGhostStep();
  }

  public void setGhostPaddingRate(double ghostPadding) {
    this.ghostPaddingRate = ghostPadding;
    updateGhostPadding();
  }

  public void setCookiePaddingRate(double cookiePadding) {
    this.cookiePaddingRate = cookiePadding;
    updateCookiePadding();
  }
}
