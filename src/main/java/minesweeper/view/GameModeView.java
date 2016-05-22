 // CHECKSTYLE:OFF
package minesweeper.view;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.util.Pair;
import javafx.scene.control.ButtonBar.ButtonData;
import minesweeper.model.Difficulty;

public class GameModeView extends Dialog<ButtonType> {

	private ButtonType buttonEasy = new ButtonType("Easy");
	private ButtonType buttonMedium = new ButtonType("Medium");
	private ButtonType buttonHard = new ButtonType("Hard");
	private ButtonType buttonCustom = new ButtonType("Custom");
	private ButtonType buttonCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

	public GameModeView() {
		super();
		this.setTitle("Game mode selection");
		this.setContentText("Choose a difficulty:");

		this.getDialogPane().getButtonTypes().setAll(buttonEasy, buttonMedium, buttonHard, buttonCustom, buttonCancel);

	}

	public Pair<Boolean, BoardView> showGameModeView() {
		Optional<ButtonType> result = this.showAndWait();
		if (result.get() == buttonEasy) {

			return new Pair<Boolean, BoardView>(true, new BoardView(Difficulty.EASY));

		} else if (result.get() == buttonMedium) {

			return new Pair<Boolean, BoardView>(true, new BoardView(Difficulty.MEDIUM));

		} else if (result.get() == buttonHard) {

			return new Pair<Boolean, BoardView>(true, new BoardView(Difficulty.HARD));

		} else if (result.get() == buttonCustom) {
			CustomDialogView customDialog = new CustomDialogView();
			Pair<Boolean, int[]> pair = customDialog.showCustomDialogView();
			if (pair.getKey()) {
				BoardView returnValue = new BoardView(Difficulty.CUSTOM);
				returnValue.setCustomProperties(pair.getValue()[0], pair.getValue()[1], pair.getValue()[2]);
				return new Pair<Boolean, BoardView>(true, returnValue);
			} else {
				
				return new Pair<Boolean, BoardView>(false, null);
				
			}

		} else {
			return new Pair<Boolean, BoardView>(false, null);
		}
	}

}
