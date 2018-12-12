package pacman.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import pacman.model.Score;
import pacman.model.ScoreBoard;
import pacman.util.ScoreBoardReader;

public class ScoreBoardController {

  @FXML ListView scoreBoardList;
  private String title;
  private ScoreBoard scoreBoard;

  public void setTitle(String title) {
    this.title = title;
  }

  public void initUI() {
    ScoreBoardReader scoreBoardReader = new ScoreBoardReader(title + ".txt");
    scoreBoardReader.read();
    scoreBoard = scoreBoardReader.getScoreBoard();
    ObservableList<Score> options = FXCollections.observableArrayList();
    options.addAll(scoreBoard.getScores());
    scoreBoardList.setItems(options);
    scoreBoardList.setCellFactory(c -> new ScoreBoardListCellFactory());
  }

  private class ScoreBoardListCellFactory extends ListCell<Score> {
    private ImageView imageView = new ImageView();

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
