 // CHECKSTYLE:OFF
package minesweeper.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Pair;
import minesweeper.Board;
import minesweeper.model.Difficulty;

public class GameView extends GridPane {

	private static Logger logger = LoggerFactory.getLogger(GameView.class);
	private BoardView boardView;
	private Difficulty difficulty = Difficulty.EASY;
	private Button newGameButton = new Button("New Game");
	private Button gameModeButton = new Button("Game Mode");
	private Label mineLabel = new Label();
	private HBox buttonLayout = new HBox(10);
	private HBox scoreLayout = new HBox(10);
	private VBox mainLayout = new VBox(10);
	private Timer timer = new Timer();
	private boolean timerOn = false;
	private boolean viewChanged = false;
	private boolean close = false;
	private EventHandler<MouseEvent> filter;

	public GameView() {
		boardView = new BoardView(difficulty);
		mineLabel.setFont(new Font("Arial", 15));
		mineLabel.setAlignment(Pos.CENTER);
		mineLabel.setMinWidth(25);

		gameModeButton.autosize();
		newGameButton.autosize();
		updateLabel(mineLabel, boardView.minesLeft());

		buttonLayout.getChildren().addAll(newGameButton, gameModeButton);
		buttonLayout.setAlignment(Pos.CENTER);

		timer.setAlignment(Pos.CENTER_RIGHT);

		scoreLayout.getChildren().addAll(timer, buttonLayout, mineLabel);
		scoreLayout.setAlignment(Pos.CENTER);
		scoreLayout.setPadding(new Insets(5));

		mainLayout.getChildren().addAll(scoreLayout, boardView);
		mainLayout.setAlignment(Pos.TOP_CENTER);
		mainLayout.setPadding(new Insets(5));

		this.getChildren().addAll(mainLayout);

		assignEventToButtons();
		assignEventToBoardView();

	}

	private void updateLabel(Label mine, int mineNumber) {
		mine.setText(String.valueOf(mineNumber));
	}

	private void assignEventToButtons() {
		newGameButton.setOnMouseClicked(e -> {
			if (e.getButton().equals(MouseButton.PRIMARY)) {
				newGame();
			}
		});
		gameModeButton.setOnMouseClicked(e -> {
			if (e.getButton().equals(MouseButton.PRIMARY)) {
				switchGameMode();
				this.fireEvent(e);
			}
		});
	}

	private void newGame() {
		logger.info("New game started.");
		boardView.newGame();
		timer.stop();
		timer.reset();
		timerOn = false;
		updateLabel(mineLabel, boardView.minesLeft());
		removeEventToBoardView();
		assignEventToBoardView();
	}

	private void switchGameMode() {
		GameModeView gameModeView = new GameModeView();
		Pair<Boolean, BoardView> pair = gameModeView.showGameModeView();
		if (pair.getKey()) {
			mainLayout.getChildren().clear();
			boardView = pair.getValue();
			newGame();
			mainLayout.getChildren().addAll(scoreLayout, boardView);
			viewChanged = true;
		}
		logger.info("Difficulty changed to:{}",pair.getValue().getBoard().getDifficulty());
	}

	private void removeEventToBoardView() {
		boardView.removeEventFilter(MouseEvent.MOUSE_CLICKED, filter);
	}

	private void assignEventToBoardView() {
		filter = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (!timerOn) {
					timer.start();
					timerOn = true;
				} else if (boardView.win() || boardView.lose()) {
					timer.stop();
					WinGameView win = new WinGameView(timer.getTime());
					switch (win.showWinGameView(boardView.getBoard().getDifficulty(), boardView.win())) {
					case 0:
						newGame();

						break;
					case 1:
						switchGameMode();
						fireEvent(event);
						break;
					default:
						close = true;
						fireEvent(event);
						break;
					}
				}
				updateLabel(mineLabel, boardView.minesLeft());

			}
		};
		boardView.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
	}

	public boolean isViewChanged() {
		return viewChanged;
	}

	public void setViewChanged(boolean viewChanged) {
		this.viewChanged = viewChanged;
	}

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	public boolean exitMessage() {
		ExitView exitView = new ExitView();
		if (boardView.getBoard().isMinesPlanted())
			return exitView.showExitView(boardView.getBoard(), timer);
		else
			return true;
	}

	public boolean loadMessage() {
		File saveFile = new File("save.xml");
		if (saveFile.isFile()) {
			LoadView loadView = new LoadView();
			Pair<Boolean, Board> result = loadView.showLoadView(boardView.getBoard());
			if (result.getKey()) {
				logger.info("Game loaded.");
				mainLayout.getChildren().clear();
				boardView = new BoardView(result.getValue().getDifficulty());
				if (result.getValue().getDifficulty()==Difficulty.CUSTOM) {
					boardView.setCustomProperties(result.getValue().getRow(), result.getValue().getColumn(),
							result.getValue().getMineNumber());
				}
				newGame();
				boardView.setBoard(result.getValue());
				boardView.setMinesPlanted(true);
				boardView.refresView();
				timer.setTime(result.getValue().getTime());
				timer.start();
				timerOn = true;
				updateLabel(mineLabel, boardView.minesLeft());
				mainLayout.getChildren().addAll(scoreLayout, boardView);
				try {
					Files.delete(saveFile.toPath());
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;
			}
			try {
				Files.delete(saveFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;

	}
}
