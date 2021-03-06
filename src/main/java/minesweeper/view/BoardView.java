// CHECKSTYLE:OFF
package minesweeper.view;

import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import minesweeper.Board;
import minesweeper.model.Difficulty;
import minesweeper.model.Field;
import minesweeper.model.Position;

public class BoardView extends GridPane {

	private static Logger logger = LoggerFactory.getLogger(BoardView.class);
	private double buttonHeight = 25;
	private double buttonWidth = 25;
	private Board board;
	private TreeMap<Position, Button> buttons = new TreeMap<>((c1, c2) -> c1.compareTo(c2));
	private boolean minesPlanted = false;
	private Image flagImage = new Image(getClass().getClassLoader().getResourceAsStream("flag.png"),
			(double) buttonHeight, (double) buttonHeight, true, false);
	private Image mineImage = new Image(getClass().getClassLoader().getResourceAsStream("mine.png"),
			(double) buttonHeight, (double) buttonHeight, true, false);
	private Image oneImage = new Image(getClass().getClassLoader().getResourceAsStream("1.png"), (double) buttonHeight,
			(double) buttonHeight, true, false);
	private Image twoImage = new Image(getClass().getClassLoader().getResourceAsStream("2.png"), (double) buttonHeight,
			(double) buttonHeight, true, false);
	private Image threeImage = new Image(getClass().getClassLoader().getResourceAsStream("3.png"),
			(double) buttonHeight, (double) buttonHeight, true, false);
	private Image fourImage = new Image(getClass().getClassLoader().getResourceAsStream("4.png"), (double) buttonHeight,
			(double) buttonHeight, true, false);
	private Image fiveImage = new Image(getClass().getClassLoader().getResourceAsStream("5.png"), (double) buttonHeight,
			(double) buttonHeight, true, false);
	private Image sixImage = new Image(getClass().getClassLoader().getResourceAsStream("6.png"), (double) buttonHeight,
			(double) buttonHeight, true, false);
	private Image sevenImage = new Image(getClass().getClassLoader().getResourceAsStream("7.png"),
			(double) buttonHeight, (double) buttonHeight, true, false);
	private Image eightImage = new Image(getClass().getClassLoader().getResourceAsStream("8.png"),
			(double) buttonHeight, (double) buttonHeight, true, false);

	public BoardView(Difficulty difficulty) {
		board = new Board(difficulty);
		logger.trace("Difficulty:{}", difficulty);
		if (difficulty != Difficulty.CUSTOM) {
			for (Position position : board.getBoard().keySet()) {
				buttons.put(position, new Button());
				buttons.get(position).setMinHeight(buttonHeight);
				buttons.get(position).setMinWidth(buttonWidth);
				buttons.get(position).setMaxHeight(buttonHeight);
				buttons.get(position).setMaxWidth(buttonWidth);
				GridPane.setRowIndex(buttons.get(position), position.getRow() + 1);
				GridPane.setColumnIndex(buttons.get(position), position.getColumn() + 1);

				this.getChildren().add(buttons.get(position));
			}
			this.setAlignment(Pos.BOTTOM_CENTER);
			assignEvent();
		}
	}

	private void assignEvent() {
		for (Map.Entry<Position, Button> buttonsEntry : buttons.entrySet()) {
			buttonsEntry.getValue().setOnMouseClicked(e -> {

				if (e.getButton().equals(MouseButton.PRIMARY)) {

					if (!minesPlanted) {
						board.minePlanting(buttonsEntry.getKey());
						board.showField(buttonsEntry.getKey());
						minesPlanted = true;
					} else {
						board.showField(buttonsEntry.getKey());
						logger.trace("Visible:{}", board.getField(buttonsEntry.getKey()).isVisible());
					}

				} else if (e.getButton().equals(MouseButton.SECONDARY)) {
					board.flagAMine(buttonsEntry.getKey());
					logger.trace("Flag:{}", board.getField(buttonsEntry.getKey()).isFlaged());

				}
				if (board.won())
					board.showMines();
				for (Map.Entry<Position, Field> boardEntry : board.getBoard().entrySet()) {
					showField(boardEntry.getKey());
				}
				fireEvent(e);
			});
		}

	}

	private void showField(Position position) {
		buttons.get(position).setGraphic(null);
		if (board.getBoard().get(position).isFlaged()) {
			buttons.get(position).setGraphic(new ImageView(flagImage));
		} else if (board.getBoard().get(position).isVisible()) {
			buttons.get(position).setDisable(true);
			if (board.getBoard().get(position).isMine()) {
				buttons.get(position).setGraphic(new ImageView(mineImage));
			} else {
				switch (board.getBoard().get(position).getValue()) {
				case 1:
					buttons.get(position).setGraphic(new ImageView(oneImage));
					break;
				case 2:
					buttons.get(position).setGraphic(new ImageView(twoImage));
					break;
				case 3:
					buttons.get(position).setGraphic(new ImageView(threeImage));
					break;
				case 4:
					buttons.get(position).setGraphic(new ImageView(fourImage));
					break;
				case 5:

					buttons.get(position).setGraphic(new ImageView(fiveImage));
					break;
				case 6:
					buttons.get(position).setGraphic(new ImageView(sixImage));
					break;
				case 7:
					buttons.get(position).setGraphic(new ImageView(sevenImage));
					break;
				case 8:
					buttons.get(position).setGraphic(new ImageView(eightImage));
					break;
				default:
					buttons.get(position).setGraphic(null);
					break;
				}
			}
		} else {
			buttons.get(position).setGraphic(null);
		}

	}

	public boolean win() {
		return board.won();
	}

	public boolean lose() {
		return board.isBlownUp();
	}

	public int minesLeft() {
		return board.remainingMines();
	}

	public void newGame() {
		board.initEmptyBoard();
		for (Map.Entry<Position, Button> buttonsEntry : buttons.entrySet()) {
			buttonsEntry.getValue().setDisable(false);
			buttonsEntry.getValue().setGraphic(null);
			buttonsEntry.getValue().setText("");
		}
		minesPlanted = false;
	}

	public void setCustomProperties(int row, int column, int mineNumber) {
		board.setCustomProperties(row, column, mineNumber);
		for (Position position : board.getBoard().keySet()) {
			buttons.put(position, new Button());
			buttons.get(position).setMinHeight(buttonHeight);
			buttons.get(position).setMinWidth(buttonWidth);
			GridPane.setRowIndex(buttons.get(position), position.getRow() + 1);
			GridPane.setColumnIndex(buttons.get(position), position.getColumn() + 1);
			this.getChildren().add(buttons.get(position));
		}
		this.setAlignment(Pos.BOTTOM_CENTER);
		assignEvent();
	}

	public void refresView() {
		for (Map.Entry<Position, Button> buttonsEntry : buttons.entrySet()) {
			if (board.getField(buttonsEntry.getKey()).isVisible())
				showField(buttonsEntry.getKey());

		}
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean isMinesPlanted() {
		return minesPlanted;
	}

	public void setMinesPlanted(boolean minesPlanted) {
		this.minesPlanted = minesPlanted;
	}
}
