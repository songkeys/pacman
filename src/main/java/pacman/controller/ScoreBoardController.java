package pacman.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import pacman.model.Score;
import pacman.model.ScoreBoard;
import pacman.util.ScoreBoardReader;

/**
 *
 *
 * <h1>ScoreBoardController</h1>
 *
 * <p>A {@link ScoreBoardController} is a controller for ScoreBoard scene.
 *
 * @author Song Zhang
 * @version 1.0
 * @since 1.0
 */
public class ScoreBoardController {

  /** The list of score board. */
  @FXML ListView scoreBoardList;
  /** The title of the map (level). */
  private String title;

  /**
   * Changes the title shown on the screen.
   *
   * @param title the desired title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Initializes the UI based on the title of a map.
   *
   * <p>This methods tries to find a score board read from a score board file of the map file name,
   * and renders the result into UI.
   */
  public void initUi() {
    ScoreBoardReader scoreBoardReader = new ScoreBoardReader(title + ".txt");
    scoreBoardReader.read();
    ScoreBoard scoreBoard = scoreBoardReader.getScoreBoard();
    ObservableList<Score> options = FXCollections.observableArrayList();
    options.addAll(scoreBoard.getScores());
    scoreBoardList.setItems(options);
    scoreBoardList.setCellFactory(c -> new ScoreBoardListCellFactory());
  }

  /** This is the factory for producing score board list cell. */
  private class ScoreBoardListCellFactory extends ListCell<Score> {
    @Override
    protected void updateItem(Score score, boolean empty) {
      super.updateItem(score, empty);
      setGraphic(null);
      setText(null);
      if (score != null && !empty) {
        setText(score.getNickname() + ": " + score.getValue());
      }
    }
  }
}
