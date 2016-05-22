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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import minesweeper.model.Difficulty;
import minesweeper.model.Field;
import minesweeper.model.Position;

/**
 * Az akna mezőkből álló tábla, amely játék fő logikáját tartalmazza.
 * 
 * @author Gábor
 *
 */
@XmlRootElement
public class Board {

	private static Logger logger = LoggerFactory.getLogger(Board.class);

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

	/**
	 * Az alapértelmezett üres konstruktor.
	 */
	public Board() {
		super();
	}

	/**
	 * Létrehoz egy {@code Board} objektumot a megadott értékek alapján.
	 * {@code Difficulty.Custom} esetén a
	 * {@link #setCustomProperties(int row, int column, int mineNumber)
	 * setCustomProperties} metódus hívásával lehet megadni az egyedi adatokat
	 * egyszerűen.
	 * 
	 * @param diff
	 *            a {@code Difficulty} ami alapján a {@code Board} értékeit
	 *            beállítjuk
	 */
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

	/**
	 * Megváltoztatja a megadott {@code Position}-ben lévő mező {@code flaged}
	 * értékét. A metódus lefutása függ attól hogy a mező látható-e vagy a játék
	 * győzelemmel vagy vereséggel véget ért-e.
	 * 
	 * @param position
	 *            a {@code Position} ahol a mezőt meg kell változtatni
	 */
	public void flagAMine(Position position) {
		if (!board.get(position).isVisible() && !isBlownUp() && !won()) {
			if (board.get(position).isFlaged()) {
				flags--;
				board.get(position).setFlaged(false);
			} else {
				flags++;
				board.get(position).setFlaged(true);
			}
			logger.trace("Flags:{}", this.flags);
		}
	}

	/**
	 * Visszaadja {@code Position}, {@code Field} párokat tartalmazó {@code Map}
	 * -et.
	 * 
	 * @return a {@code Position}, {@code Field} párokat tartalmazó {@code Map}
	 */
	@XmlElement(required = true)
	public TreeMap<Position, Field> getBoard() {
		return board;
	}

	/**
	 * Visszadja az oszlopok számát.
	 * 
	 * @return az oszlopok száma
	 */
	@XmlAttribute(required = true)
	public int getColumn() {
		return column;
	}

	/**
	 * Visszadja a választott nehézségi szintet.
	 * 
	 * @return a választott nehézségi szint
	 */
	@XmlAttribute(required = true)
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * Visszadja megadott {@code Position}-ben lévő {@code Field}-t.
	 * 
	 * @param position
	 *            a {@code Position} ahonnan a {@code Field}-t keressük
	 * @return megadott {@code Position}-ben lévő {@code Field}
	 */
	public Field getField(Position position) {
		return board.get(position);
	}

	/**
	 * Visszadja hány mezőt jelöltünk már meg.
	 * 
	 * @return hány mezőt jelöltünk már meg
	 */
	@XmlAttribute(required = true)
	public int getFlags() {
		return flags;
	}

	/**
	 * Visszadja az aknák számát.
	 * 
	 * @return az aknák száma
	 */
	@XmlAttribute(required = true)
	public int getMineNumber() {
		return mineNumber;
	}

	/**
	 * Visszadja a sorok számát.
	 * 
	 * @return a sorok száma
	 */
	@XmlAttribute(required = true)
	public int getRow() {
		return row;
	}

	/**
	 * Visszadja a már felfedett mezők számát.
	 * 
	 * @return a már felfedett mezők száma
	 */
	@XmlAttribute(required = true)
	public int getShownFields() {
		return shownFields;
	}

	/**
	 * Visszadja a felhasznált időt.
	 * 
	 * @return a felhasznált idő
	 */
	@XmlAttribute(required = true)
	public int getTime() {
		return time;
	}

	/**
	 * Inicializálja a kezdeti értékeket egy üres táblával.
	 */
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

	/**
	 * Visszadja hogy felrobbantunk-e.
	 * 
	 * @return felrobbantunk-e
	 */
	public boolean isBlownUp() {
		return blownUp;
	}

	/**
	 * Visszadja hogy telepítettük-e már az aknákat.
	 * 
	 * @return telepítettük-e már az aknákat
	 */
	@XmlAttribute(required = true)
	public boolean isMinesPlanted() {
		return minesPlanted;
	}

	/**
	 * Az aknák telepítése, ügyelve arra hogy a megadott kezdő {@code Position}
	 * -ben ne legyen akna.
	 * 
	 * @param startPosition
	 *            a kezdő {@code Position}
	 */
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

	/**
	 * Visszaadja hány aknát jelöltünk már meg.
	 * 
	 * @return a megjelölt aknák száma
	 */
	public int remainingMines() {
		return mineNumber - flags;
	}

	/**
	 * Beállítja hogy felrobbantunk-e.
	 * 
	 * @param blownUp
	 *            felrobbantunk-e
	 */
	public void setBlownUp(boolean blownUp) {
		this.blownUp = blownUp;
	}

	/**
	 * Beállítja hogy mennyi az oszlopok száma.
	 * 
	 * @param column
	 *            az oszlopok száma
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Beállítja hogy az sorok, oszlopok, és aknák száma mennyi legyen.
	 * Megszabott határértékeken belül tartja az értékeket.
	 * 
	 * @param row
	 *            a sorok száma
	 * @param column
	 *            az oszlopok száma
	 * @param mineNumber
	 *            az aknák száma
	 */
	public void setCustomProperties(int row, int column, int mineNumber) {
		if (row < 9) {
			this.row = 9;
			logger.warn("Row value is too low.");
		} else if (row > 24) {
			this.row = 24;
			logger.warn("Row value is too high.");
		} else
			this.row = row;

		if (column < 9) {
			this.column = 9;
			logger.warn("Column value is too low.");
		} else if (column > 30) {
			this.column = 30;
			logger.warn("Column value is too high.");
		} else
			this.column = column;

		if (mineNumber < 10) {
			this.mineNumber = 10;
			logger.warn("Minenumber value is too low.");
		} else if (mineNumber > ((this.row * this.column) / 5) * 4) {
			this.mineNumber = ((this.row * this.column) / 5) * 4;
			logger.warn("Minenumber value is too high.");
		} else
			this.mineNumber = mineNumber;
		logger.debug("Row:{}", this.row);
		logger.debug("Column:{}", this.column);
		logger.debug("Minenumber:{}", this.mineNumber);
		initEmptyBoard();
	}

	/**
	 * Beállítja hogy mi legyen a nehézségi szint.
	 * 
	 * @param difficulty
	 *            a nehézségi szint
	 */
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * Beállítja hogy hány mező lett már megjelölve.
	 * 
	 * @param flags
	 *            hány mező lett már megjelölve
	 */
	public void setFlags(int flags) {
		this.flags = flags;
	}

	/**
	 * Beállítja hogy mennyi az aknák száma.
	 * 
	 * @param mineNumber
	 *            az aknák száma
	 */
	public void setMineNumber(int mineNumber) {
		this.mineNumber = mineNumber;
	}

	/**
	 * Beállítja hogy az aknák telepítve lettek-e már.
	 * 
	 * @param minesPlanted
	 *            az aknák telepítve lettek-e már
	 */
	public void setMinesPlanted(boolean minesPlanted) {
		this.minesPlanted = minesPlanted;
	}

	/**
	 * Beállítja hogy mennyi az oszlopok száma.
	 * 
	 * @param row
	 *            az oszlopok száma
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Beállítja hogy hogy hány mező lett már felfedve.
	 * 
	 * @param shownFields
	 *            hány mező lett már felfedve
	 */
	public void setShownFields(int shownFields) {
		this.shownFields = shownFields;
	}

	/**
	 * Beállítja hogy mennyi idő telt el.
	 * 
	 * @param time
	 *            az eltelt idő
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * Felfedi az adott pozícióban lévő mezőt.
	 * 
	 * @param pos
	 *            a felfedni kívánt mező {@code Position}-je
	 */
	public void showField(Position pos) {

		if (!board.get(pos).isFlaged() && !isBlownUp() && !won()) {
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

	/**
	 * Felfedi az összes aknát.
	 */
	public void showMines() {
		for (Field field : board.values()) {
			if (field.isMine())
				field.setVisible(true);
		}
	}

	/**
	 * Visszaadja hogy nyertünk-e már.
	 * 
	 * @return nyertünk-e már
	 */
	public boolean won() {
		return row * column - mineNumber == shownFields;
	}

}
