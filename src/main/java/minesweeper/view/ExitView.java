package minesweeper.view;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import minesweeper.Board;
import minesweeper.DOM.BoardDOM;

import java.util.Optional;

import javafx.scene.control.ButtonBar.ButtonData;

public class ExitView extends Dialog<ButtonType> {
	private ButtonType yesButton = new ButtonType("Yes");
	private ButtonType noButton = new ButtonType("No");
	private ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	
	public ExitView() {
		super();
		this.setTitle("Exit");
		this.setContentText("Would you like to save your progress?");
		this.getDialogPane().getButtonTypes().addAll(yesButton, noButton, cancelButton);
	}

	public boolean showExitView(Board board,Timer timer) {
		Optional<ButtonType> result = this.showAndWait();
		BoardDOM boardSaver = new BoardDOM(board);
		if (result.get() == yesButton) {
			boardSaver.save(timer.getTime());
			return true;

		} else if (result.get() == noButton) {
			return true;

		} else {
			return false;
		}
	}

}
