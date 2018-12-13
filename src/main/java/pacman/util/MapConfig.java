package pacman.util;

import pacman.model.Map;

/**
 *
 *
 * <h1>MapConfig</h1>
 *
 * <p>A {@link MapConfig} is an object consisting of configuration of maps.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 * @see Map
 */
public class MapConfig {

  /** The title of the map */
  private String title;

  /** The grid length in this {@link Map} */
  private double gridLength;

  /** The pacman step in this {@link Map} */
  private double pacmanStep;

  /** The ghost step in this {@link Map} */
  private double ghostStep;

  /** The cookie padding in this {@link Map} */
  private double cookiePadding;

  /** The ghost padding in this {@link Map} */
  private double ghostPadding;

  /** The pacman step rate in this {@link Map} */
  private double pacmanStepRate = 0.1;

  /** The ghost step rate in this {@link Map} */
  private double ghostStepRate = 0.1;

  /** The cookie padding rate in this {@link Map} */
  private double cookiePaddingRate = 0.3;

  /** The ghost padding rate in this {@link Map} */
  private double ghostPaddingRate = 0.2;

  /**
   * Allocates a new {@link MapConfig} object.
   *
   * @param gridLength the grid length in this {@link Map}
   */
  public MapConfig(double gridLength) {
    this.gridLength = gridLength;
    this.calculate();
  }

  /** Updates all configuration based on the given ones. */
  public void calculate() {
    updateGhostStep();
    updatePacmanStep();
    updateCookiePadding();
    updateGhostPadding();
  }

  /** Updates the pacman step. */
  private void updatePacmanStep() {
    pacmanStep = gridLength * pacmanStepRate;
  }

  /** Updates the ghost step. */
  private void updateGhostStep() {
    ghostStep = gridLength * ghostStepRate;
  }

  /** Updates the cookie padding. */
  private void updateCookiePadding() {
    cookiePadding = gridLength * cookiePaddingRate;
  }

  /** Updates the ghost padding. */
  private void updateGhostPadding() {
    ghostPadding = gridLength * ghostPaddingRate;
  }

  /**
   * Returns the cookie padding in this {@link Map}.
   *
   * @return the cookie padding in this {@link Map}
   */
  public double getCookiePadding() {
    return cookiePadding;
  }

  /**
   * Returns the ghost padding in this {@link Map}.
   *
   * @return the ghost padding in this {@link Map}
   */
  public double getGhostPadding() {
    return ghostPadding;
  }

  /**
   * Returns the ghost step in this {@link Map}.
   *
   * @return the ghost step in this {@link Map}
   */
  public double getGhostStep() {
    return ghostStep;
  }

  /**
   * Returns the pacman step in this {@link Map}.
   *
   * @return the pacman step in this {@link Map}
   */
  public double getPacmanStep() {
    return pacmanStep;
  }

  /**
   * Returns the grid length in this {@link Map}.
   *
   * @return the grid length in this {@link Map}
   */
  public double getGridLength() {
    return gridLength;
  }

  /**
   * Changes the grid length in this {@link Map}.
   *
   * @param gridLength the grid length in this {@link Map}
   */
  public void setGridLength(double gridLength) {
    this.gridLength = gridLength;
  }

  /**
   * Returns the title of this {@link Map}.
   *
   * @return the title of this {@link Map}
   */
  public String getTitle() {
    return title;
  }

  /**
   * Changes the title of this {@link Map}.
   *
   * @param title the title of this {@link Map}
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Changes the pacman step rate in this {@link Map}, and updates the pacman step.
   *
   * @param pacmanStepRate the pacman step rate in this {@link Map}
   */
  public void setPacmanStepRate(double pacmanStepRate) {
    this.pacmanStepRate = pacmanStepRate;
    updatePacmanStep();
  }

  /**
   * Changes the ghost step rate in this {@link Map}, and updates the ghost step.
   *
   * @param ghostStepRate the ghost step rate in this {@link Map}
   */
  public void setGhostStepRate(double ghostStepRate) {
    this.ghostStepRate = ghostStepRate;
    updateGhostStep();
  }

  /**
   * Changes the ghost padding in this {@link Map}, and updates the ghost padding.
   *
   * @param ghostPadding the ghost padding in this {@link Map}
   */
  public void setGhostPaddingRate(double ghostPadding) {
    this.ghostPaddingRate = ghostPadding;
    updateGhostPadding();
  }

  /**
   * Changes the cookie padding in this {@link Map}, and updates the cookie padding.
   *
   * @param cookiePadding the cookie padding in this {@link Map}
   */
  public void setCookiePaddingRate(double cookiePadding) {
    this.cookiePaddingRate = cookiePadding;
    updateCookiePadding();
  }
}
