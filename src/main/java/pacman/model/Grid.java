package pacman.model;

import javafx.scene.shape.Rectangle;
import pacman.constant.MapConfig;

public class Grid extends Rectangle {

  private Map parentMap;

  public Grid(Map parentMap, double row, double column) {

    // set map
    this.parentMap = parentMap;

    // set position
    this.setX(row * MapConfig.GRID_LENGTH);
    this.setY(column * MapConfig.GRID_LENGTH);

    // set height and width
    this.setHeight(MapConfig.GRID_LENGTH);
    this.setWidth(MapConfig.GRID_LENGTH);
  }

  public Map getParentMap() {
    return parentMap;
  }

  public boolean isTouching(Grid grid, double padding) {
    double myX = this.getX();
    double myY = this.getY();
    double itsX = grid.getX();
    double itsY = grid.getY();

    return myX < itsX + MapConfig.GRID_LENGTH - padding
        && myX > itsX - MapConfig.GRID_LENGTH + padding
        && myY < itsY + MapConfig.GRID_LENGTH - padding
        && myY > itsY - MapConfig.GRID_LENGTH + padding;
  }

  public boolean isTouching(Grid grid) {
    return isTouching(grid, 0);
  }
}
