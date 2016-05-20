package minesweeper.model;

/**
 * Egy akna mezőt reprezentáló osztály.
 * Ez az osztály tartalmazzák az adott mező tulajdonságait.
 * @author Gábor
 *
 */
public class Field {
	/**
	 * Egy adott mező értéke.
	 * A vele szomszédos aknák száma.
	 */
	private int value;
	/**
	 * Az adott mező akna-e.
	 * Az alapértelmezett értéke {@code false}.
	 */
	private boolean mine=false;
	/**
	 * Az adott mező meg van-e jelölve.
	 * Az alapértelmezett értéke {@code false}.
	 */
	private boolean flaged=false;
	/**
	 * Az adott mező látható-e.
	 * Az alapértelmezett értéke {@code false}.
	 */
	private boolean visible=false;
	
	/**
	 * Az alapértelmezett üres konstruktor.
	 */
	public Field() {
		super();
	}
	

	/**
	 * 
	 * @param value
	 * @param mine
	 */
	public Field(int value, boolean mine) {
		super();
		this.value = value;
		this.mine = mine;
	}

	/**
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return
	 */
	public boolean isFlaged() {
		return flaged;
	}

	/**
	 * @return
	 */
	public boolean isMine() {
		return mine;
	}

	/**
	 * @return
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param flaged
	 */
	public void setFlaged(boolean flaged) {
		this.flaged = flaged;
	}

	/**
	 * @param mine
	 */
	public void setMine(boolean mine) {
		this.mine = mine;
	}

	/**
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
}
