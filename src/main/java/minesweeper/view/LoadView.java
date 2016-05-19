package minesweeper.view;

import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.util.Pair;
import minesweeper.Board;
import minesweeper.DOM.BoardDOM;

public class LoadView extends Dialog<ButtonType> {

	private ButtonType yesButton = new ButtonType("Yes");
	private ButtonType noButton = new ButtonType("No");

	public LoadView() {
		super();
		this.setTitle("Load savefile");
		this.setContentText("Would you like to load your previous progress?");
		this.getDialogPane().getButtonTypes().addAll(yesButton, noButton);
	}

	public Pair<Boolean, Board> showLoadView(Board board) {
		Optional<ButtonType> result = this.showAndWait();
		BoardDOM boardLoader = new BoardDOM(board);

		if (result.get() == yesButton) {
			Board loadedBoard=boardLoader.load();
			return new Pair<Boolean, Board>(true,loadedBoard);
		}
		return new Pair<Boolean, Board>(false,null);
	}

}
