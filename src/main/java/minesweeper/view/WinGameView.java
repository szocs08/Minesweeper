 // CHECKSTYLE:OFF
package minesweeper.view;

import java.util.Optional;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import minesweeper.DAO.StatsDom;
import minesweeper.model.Data;
import minesweeper.model.Difficulty;
import minesweeper.model.Stats;

public class WinGameView extends Dialog<ButtonType> {

	private ButtonType buttonNewGame = new ButtonType("New Game");
	private ButtonType buttonAnotherMode = new ButtonType("Another Mode");
	private ButtonType buttonExit = new ButtonType("Exit");
	private Label gratulationsLabel = new Label();
	private Label timeLabel = new Label("Your time: ");
	private Label bestTimeLabel = new Label("Best time: ");
	private Label playedGamesLabel = new Label("Games played: ");
	private Label wonGamesLabel = new Label("Games won: ");
	private Label percentageLabel = new Label("Games won percentage: ");
	private GridPane grid = new GridPane();
	private VBox mainLayout = new VBox(10);
	private int time;

	public WinGameView(int time) {
		super();
		this.time = time;
		grid.addColumn(0, timeLabel, playedGamesLabel);
		grid.addColumn(1, bestTimeLabel, wonGamesLabel);
		grid.setHgap(30);
		grid.setAlignment(Pos.CENTER);
		gratulationsLabel.setAlignment(Pos.CENTER);
		mainLayout.getChildren().addAll(gratulationsLabel, grid, percentageLabel);
		mainLayout.setPadding(new Insets(10));
		this.getDialogPane().getButtonTypes().addAll(buttonNewGame, buttonAnotherMode, buttonExit);
		this.getDialogPane().setContent(mainLayout);
		this.setTitle("Game end");

	}

	public int showWinGameView(Difficulty difficulty, boolean won) {
		StatsDom statsLoader = new StatsDom();
		Stats stats = new Stats();
		if (difficulty != Difficulty.CUSTOM) {
			stats = statsLoader.loadStats();
			if (!stats.getStatistics().containsKey(difficulty)) {
				stats.getStatistics().put(difficulty, new Data(0, 0, -1));
			}
			stats.getStatistics().get(difficulty)
					.setGamesPlayed(stats.getStatistics().get(difficulty).getGamesPlayed() + 1);
			if (won) {

				stats.getStatistics().get(difficulty)
						.setGamesWon(stats.getStatistics().get(difficulty).getGamesWon() + 1);
				gratulationsLabel.setText("Congratulations, You won!");
				if (stats.getStatistics().get(difficulty).getBestTime() > time
						|| stats.getStatistics().get(difficulty).getBestTime() == -1) {
					stats.getStatistics().get(difficulty).setBestTime(time);
				}
			} else {
				gratulationsLabel.setText("Sorry, You lost!");
			}
			timeLabel.setText(timeLabel.getText() + time);
			if (stats.getStatistics().get(difficulty).getBestTime() != -1) {
				bestTimeLabel
						.setText(bestTimeLabel.getText() + stats.getStatistics().get(difficulty).getBestTime());
			}else{
				bestTimeLabel
				.setText(bestTimeLabel.getText());
			}
			playedGamesLabel
					.setText(playedGamesLabel.getText() + stats.getStatistics().get(difficulty).getGamesPlayed());
			wonGamesLabel.setText(wonGamesLabel.getText() + stats.getStatistics().get(difficulty).getGamesWon());
			percentageLabel
					.setText(percentageLabel.getText() + stats.getStatistics().get(difficulty).getPercentage() + "%");
		} else {
			if (won) {
				gratulationsLabel.setText("Congratulations, You won!");
			} else {
				gratulationsLabel.setText("Sorry, You lost!");
			}
			timeLabel.setText(timeLabel.getText() + time);
			playedGamesLabel.setVisible(false);
			wonGamesLabel.setVisible(false);
			bestTimeLabel.setVisible(false);
			percentageLabel.setVisible(false);

		}
		statsLoader.saveStats(stats);
		Optional<ButtonType> result = this.showAndWait();
		if (result.get() == buttonNewGame) {
			return 0;
		} else if (result.get() == buttonAnotherMode) {
			return 1;
		} else {
			return 2;
		}
	}

}
