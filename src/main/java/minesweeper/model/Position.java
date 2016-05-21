package minesweeper.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gábor
 *
 */
public class Position implements Comparable<Position> {
	/**
	 * 
	 */
	private int row;
	/**
	 * 
	 */
	private int column;

	/**
	 * 
	 */
	public Position() {
		super();
	}

	/**
	 * @param row
	 * @param column
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
	 * @return
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @return
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return
	 */
	public List<Position> neighbours() {
		List<Position> list = new ArrayList<Position>();
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = column - 1; j <= column + 1; j++) {
				//if (i != row || j != column)
					list.add(new Position(i, j));
			}
		}
		list.remove(new Position(row, column));
		return list;
	}
	
	/**
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

}
