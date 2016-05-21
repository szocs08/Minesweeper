package minesweeper.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Pozíciók reprezentálása használt osztály,
 * sor-oszlop érték párként.
 * @author Gábor
 *
 */
public class Position implements Comparable<Position> {
	/**
	 * Melyik sorban található a megadott {@code Position}.
	 */
	private int row;
	/**
	 * Melyik oszlopban található a megadott {@code Position}.
	 */
	private int column;

	/**
	 * Az alapértelmezett üres konstruktor.
	 */
	public Position() {
		super();
	}

	/**
	 * Létrehoz egy {@code Position} objektumot a megadott értékek alapján. 
	 * @param row melyik sorban található a megadott {@code Position}.
	 * @param column melyik oszlopban található a megadott {@code Position}
	 */
	public Position(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	@Override
	public int compareTo(Position o) {
		if (Integer.compare(this.row, o.row) == 0)
			return Integer.compare(this.column, o.column);
		return Integer.compare(this.row, o.row);
	}

	@SuppressWarnings("checkstyle:javadocmethod")
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Position))
			return false;
		Position o = (Position) obj;
		return this.row==o.row && this.column==o.column;

	}

	/**
	 * Visszaadja hogy melyik oszlopban található a megadott {@code Position}.
	 * @return melyik oszlopban található a megadott {@code Position}
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Visszaadja hogy melyik sorban található a megadott {@code Position}.
	 * @return melyik sorban található a megadott {@code Position}
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Visszaadja az ezzel a {@code Position}-nel szomszédos {@code Position}-ket egy {@code List}-ben.
	 * @return a szomszédos {@code Position}-k egy {@code List}-ben
	 */
	public List<Position> neighbours() {
		List<Position> list = new ArrayList<Position>();
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = column - 1; j <= column + 1; j++) {
					list.add(new Position(i, j));
			}
		}
		list.remove(new Position(row, column));
		return list;
	}
	
	/**
	 * Beállítja hogy melyik oszlopban található a megadott {@code Position}.
	 * @param column melyik oszlopban található a megadott {@code Position}
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * Beállítja hogy melyik sorban található a megadott {@code Position}.
	 * @param row melyik sorban található a megadott {@code Position}
	 */
	public void setRow(int row) {
		this.row = row;
	}

}
