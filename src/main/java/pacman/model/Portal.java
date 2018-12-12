package pacman.model;

import pacman.constant.FileName;
import pacman.constant.PortalType;

public class Portal extends Grid {

  private Portal twinPortal;
  private boolean isOpen;

  public Portal(Map map, double x, double y, PortalType portalType) {
    super(map, x, y);

    // set image
    switch (portalType) {
      case A:
        setImage(FileName.IMAGE_PORTAL_A);
        break;
      case B:
        setImage(FileName.IMAGE_PORTAL_B);
        break;
    }

    // init status
    isOpen = true;
  }

  public Portal getTwinPortal() {
    return twinPortal;
  }

  public void setTwinPortal(Portal portal) {
    this.twinPortal = portal;
  }

  public void close() {
    isOpen = false;
  }

  public void open() {
    isOpen = true;
  }

  public boolean isOpen() {
    return isOpen;
  }
}
