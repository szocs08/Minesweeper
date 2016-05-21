package minesweeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import minesweeper.model.Difficulty;
import minesweeper.model.Field;
import minesweeper.model.Position;

/**
 * @author Gábor
 *
 */
@XmlRootElement
public class Board {

	private TreeMap<Position, Field> board = new TreeMap<>((c1, c2) -> c1.compareTo(c2));
	private int row;
	private int column;
	private int mineNumber;
	private int flags;
	private int shownFields;
	private int time;
	private boolean blownUp = false;
	private boolean minesPlanted = false;
	private Difficulty difficulty;

	public Board() {
		super();
	}

	public Board(Difficulty diff) {
		difficulty = diff;
		switch (difficulty) {
		case EASY:
			this.row = 9;
			this.column = 9;
			this.mineNumber = 10;
			initEmptyBoard();
			break;
		case MEDIUM:
			this.row = 16;
			this.column = 16;
			this.mineNumber = 40;
			initEmptyBoard();
			break;
		case HARD:
			this.row = 16;
			this.column = 30;
			this.mineNumber = 99;
			initEmptyBoard();
			break;
		default:
			break;
		}

	}

	public boolean areWeDead() {
		return blownUp;
	}

	public void flagAMine(Position position) {
		if (!board.get(position).isVisible() && !areWeDead() && !won()) {
			if (board.get(position).isFlaged()) {
				flags--;
				board.get(position).setFlaged(false);
			} else {
				flags++;
				board.get(position).setFlaged(true);
			}
		}
	}

	@XmlElement(required = true)
	public TreeMap<Position, Field> getBoard() {
		return board;
	}

	@XmlAttribute(required = true)
	public int getColumn() {
		return column;
	}


	@XmlAttribute(required = true)
	public Difficulty getDifficulty() {
		return difficulty;
	}

	public Field getField(Position position) {
		return board.get(position);
	}

	@XmlAttribute(required = true)
	public int getFlags() {
		return flags;
	}

	@XmlAttribute(required = true)
	public int getMineNumber() {
		return mineNumber;
	}

	@XmlAttribute(required = true)
	public int getRow() {
		return row;
	}

	@XmlAttribute(required = true)
	public int getShownFields() {
		return shownFields;
	}

	@XmlAttribute(required = true)
	public int getTime() {
		return time;
	}

	public void initEmptyBoard() {
		flags = 0;
		shownFields = 0;
		blownUp = false;
		Map<Position, Field> map = new HashMap<Position, Field>();
		board.clear();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				map.put(new Position(i, j), new Field());
			}
		}
		board.putAll(map);
		minesPlanted = false;
	}

	@XmlAttribute(required = true)
	public boolean isMinesPlanted() {
		return minesPlanted;
	}

	public void minePlanting(Position startPosition) {
		// lehetséges akna pozíciók
		List<Position> list = new ArrayList<Position>();
		Random rand = new Random();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				list.add(new Position(i, j));
			}
		}
		// kattintás helyének eltávolítása
		list.remove(startPosition);
		// aknásítás
		for (int i = 0; i < mineNumber; i++) {
			int temp = rand.nextInt(list.size());
			board.get(list.get(temp)).setMine(true);
			list.remove(temp);

		}
		// számítás
		for (Map.Entry<Position, Field> entry : board.entrySet()) {
			list.clear();
			list.addAll(entry.getKey().neighbours());
			int value = 0;
			for (Position position : list) {
				if (position.getRow() >= 0 && position.getRow() < row && position.getColumn() >= 0
						&& position.getColumn() < column && board.get(position).isMine())
					value++;
			}
			entry.getValue().setValue(value);
		}
		minesPlanted = true;
	}

	public int remainingMines() {
		return mineNumber - flags;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void setCustomProperties(int row, int column, int mineNumber) {
		if (row < 9)
			this.row = 9;
		else if (row > 24)
			this.row = 24;
		else
			this.row = row;

		if (column < 9)
			this.column = 9;
		else if (column > 30)
			this.column = 30;
		else
			this.column = column;

		if (mineNumber < 10)
			this.mineNumber = 10;
		else if (mineNumber > ((this.row * this.column) / 5) * 4)
			this.mineNumber = ((this.row * this.column) / 5) * 4;
		else
			this.mineNumber = mineNumber;

		initEmptyBoard();
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public void setMineNumber(int mineNumber) {
		this.mineNumber = mineNumber;
	}

	public void setMinesPlanted(boolean minesPlanted) {
		this.minesPlanted = minesPlanted;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setShownFields(int shownFields) {
		this.shownFields = shownFields;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public void showField(Position pos) {

		if (!board.get(pos).isFlaged() && !areWeDead() && !won()) {
			board.get(pos).setVisible(true);
			shownFields++;
			if (board.get(pos).isMine()) {
				blownUp = true;
				shownFields--;
				showMines();

			} else if (board.get(pos).getValue() == 0) {
				for (Position position : pos.neighbours()) {
					if (position.getRow() >= 0 && position.getRow() < row && position.getColumn() >= 0
							&& position.getColumn() < column && !board.get(position).isVisible())
						showField(position);

				}
			}
		}
	}

	public void showMines() {
		for (Field field : board.values()) {
			if (field.isMine())
				field.setVisible(true);
		}
	}

	public boolean won() {
		return row * column - mineNumber == shownFields;
	}


}
