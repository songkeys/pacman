package pacman.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pacman.constant.FileName;

public class SelectController {
  //  @FXML private FlowPane leftPane;
  //  @FXML private FlowPane rightPane;
  @FXML private ComboBox backgroundComboBox;
  @FXML private ComboBox wallComboBox;

  @FXML
  public void initialize() {
    // init the 3d effect of panes
    // the 3d effect on boards will throw an exception of running warning in JavaFX
    // see: https://github.com/scalafx/scalafx/issues/265
    //
    //    leftPane.getTransforms().add(new Rotate(-30, 0, 0, 0, Rotate.Y_AXIS));
    //    rightPane.getTransforms().add(new Rotate(30, 0, 0, 0, Rotate.Y_AXIS));
    //
    // fallback:

    initBackgroundComboBox();
    initWallComboBox();
  }

  @FXML
  protected void handleBackgroundChange(ActionEvent event) {
    backgroundComboBox.setButtonCell(new ListCellFactory());
    backgroundComboBox.getValue();
  }

  @FXML
  protected void handleWallChange(ActionEvent event) {
    wallComboBox.setButtonCell(new ListCellFactory());
    wallComboBox.getValue();
  }

  @FXML
  private void initBackgroundComboBox() {
    ObservableList<String> options = FXCollections.observableArrayList();
    options.addAll(FileName.IMAGE_BACKGROUNDS);
    backgroundComboBox.setItems(options);
    backgroundComboBox.setCellFactory(f -> new ListCellFactory());
  }

  @FXML
  private void initWallComboBox() {
    ObservableList<String> options = FXCollections.observableArrayList();
    options.addAll(FileName.IMAGE_OBSTACLES);
    wallComboBox.setItems(options);
    wallComboBox.setCellFactory(f -> new ListCellFactory());
  }

  public class ListCellFactory extends ListCell<String> {
    private ImageView imageView = new ImageView();

    @Override
    protected void updateItem(String item, boolean empty) {
      super.updateItem(item, empty);
      setGraphic(null);
      setText(null);
      if (item != null) {
        imageView.setImage(new Image(item, true));
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        setGraphic(imageView);
        // get filename
        String filename = item.substring(item.lastIndexOf("/") + 1); // remove path
        filename = filename.substring(0, filename.lastIndexOf(".")); // remove type suffix
        setText(filename);
      }
    }
  }
}
