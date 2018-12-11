package pacman.model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Grid extends Rectangle {

  private Map parentMap;

  public Grid(Map parentMap, double row, double column) {

    // set map
    this.parentMap = parentMap;

    // set position
    double gridLength = parentMap.getMapConfig().getGridLength();

    this.setX(row * gridLength);
    this.setY(column * gridLength);

    // set height and width
    this.setHeight(gridLength);
    this.setWidth(gridLength);
  }

  public Map getParentMap() {
    return parentMap;
  }

  public void setImage(String filename) {
    Image image = new Image(filename);
    setFill(new ImagePattern(image));
  }

  public boolean isTouching(Grid grid, double padding) {
    double myX = this.getX();
    double myY = this.getY();
    double itsX = grid.getX();
    double itsY = grid.getY();
    double gridLength = getParentMap().getMapConfig().getGridLength();

    return myX < itsX + gridLength - padding
        && myX > itsX - gridLength + padding
        && myY < itsY + gridLength - padding
        && myY > itsY - gridLength + padding;
  }
}
