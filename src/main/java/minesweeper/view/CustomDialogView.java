 // CHECKSTYLE:OFF
package minesweeper.view;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class CustomDialogView extends Dialog<ButtonType> {

	private GridPane grid = new GridPane();
	private ButtonType buttonOK = new ButtonType("OK", ButtonData.OK_DONE);
	private ButtonType buttonCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	private Label row = new Label("Row number (9-24):");
	private Label column = new Label("column number (9-30):");
	private Label mine = new Label("Mine number(10-576):");
	private TextField rowNumber = new TextField();
	private TextField columnNumber = new TextField();
	private TextField mineNumber = new TextField();

	public CustomDialogView() {
		super();
		this.setTitle("Custom game");
		this.getDialogPane().getButtonTypes().addAll(buttonOK, buttonCancel);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(5));
		grid.addColumn(0, row, column, mine);
		grid.addColumn(1, rowNumber, columnNumber, mineNumber);

		this.getDialogPane().setContent(grid);
	}

	public Pair<Boolean, int[]> showCustomDialogView() {
		Optional<ButtonType> result = this.showAndWait();
		if (result.get() == buttonOK) {
			if (!rowNumber.getText().isEmpty() || !columnNumber.getText().isEmpty()
					|| !mineNumber.getText().isEmpty()) {
				int[] returnValue = new int[3];
				try {
					returnValue[0] = Integer.valueOf(rowNumber.getText().trim());
					returnValue[1] = Integer.valueOf(columnNumber.getText().trim());
					returnValue[2] = Integer.valueOf(mineNumber.getText().trim());
				} catch (NumberFormatException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText("Wrong number(s).");
					alert.setTitle("Wrong Number(s)");
					alert.showAndWait();
					return new Pair<Boolean, int[]>(false, null);
				}
				return new Pair<Boolean, int[]>(true, returnValue);
			}
			return new Pair<Boolean, int[]>(false, null);
		} else {
			return new Pair<Boolean, int[]>(false, null);
		}
	}

}
