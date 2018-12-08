package pacman.model;

import javafx.scene.shape.Rectangle;
import pacman.constant.MapConfig;

public class Grid extends Rectangle {

  public Grid(double x, double y) {

    // set position
    this.setX(x * MapConfig.GRID_LENGTH);
    this.setY(y * MapConfig.GRID_LENGTH);

    // set height and width
    this.setHeight(MapConfig.GRID_LENGTH);
    this.setWidth(MapConfig.GRID_LENGTH);
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
