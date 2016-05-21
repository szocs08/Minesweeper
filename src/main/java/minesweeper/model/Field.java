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
	 * Létrehoz egy {@code Field} objektumot, a megadott értékeknek megfelelően. 
	 * @param value a szomszédos aknák száma
	 * @param mine a mező akna-e
	 */
	public Field(int value, boolean mine) {
		super();
		this.value = value;
		this.mine = mine;
	}

	/**
	 * Visszaadja a szomszédos aknák számát.
	 * @return a szomszédos aknák száma
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Visszaadja hogy a mező aknának van-e jelölve.
	 * @return a mező aknanának van-e jelölve
	 */
	public boolean isFlaged() {
		return flaged;
	}

	/**
	 * Visszaadja hogy a mező akna-e.
	 * @return a mező akna-e
	 */
	public boolean isMine() {
		return mine;
	}

	/**
	 * Visszaadja hogy a mező tartalma látható-e.
	 * @return a mező tartalma látható-e
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Beállítja hogy a mező aknának van-e jelölve.
	 * @param flaged a mező aknanának van-e jelölve
	 */
	public void setFlaged(boolean flaged) {
		this.flaged = flaged;
	}

	/**
	 * Beállítja hogy a mező akna-e.
	 * @param mine a mező akna-e
	 */
	public void setMine(boolean mine) {
		this.mine = mine;
	}

	/**
	 * Beállítja a szomszédos aknák számát.
	 * @param value a szomszédos aknák száma
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Beállítja hogy a mező tartalma látható-e.
	 * @param visible a mező tartalma látható-e
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
}
