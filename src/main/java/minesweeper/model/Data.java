package minesweeper.model;

/**Egy néhézségiszinthez tartozó statisztikai adatok.
 * @author Gábor
 *
 */
public class Data {

	private int gamesPlayed;
	private int gamesWon;
	private int bestTime;
	private int percentage;

	/**
	 * Az alapértelmezett üres konstruktor.
	 * <p>
	 * Létrehoz egy üres {@code Data} objektumot.
	 */
	public Data() {
		super();
	}

	/**
	 * Létrehoz egy {@code Data} objektumot, a megadott értékeknek megfelelően.
	 * 
	 * @param gamesPlayed a lejátszott játékok száma
	 * @param gamesWon a megnyert játékok száma
	 * @param bestTime a leggyorsabban megnyert játék ideje 
	 */
	public Data(int gamesPlayed, int gamesWon, int bestTime) {
		super();
		this.gamesPlayed = gamesPlayed;
		this.gamesWon = gamesWon;
		this.bestTime = bestTime;
		this.percentage = (int) ((double) gamesWon / (double) gamesPlayed * 100);
	}

	/**
	 * Visszaadja a leggyorsabb időt.
	 * @return	a leggyorsabb idő
	 */
	public int getBestTime() {
		return bestTime;
	}

	/**
	 * Visszaadja a lejátszott játékok számát.
	 * @return a lejátszott játékok száma
	 */
	public int getGamesPlayed() {
		return gamesPlayed;
	}

	/**
	 * Visszaadja a megnyert játékok számát.
	 * @return a megnyert játékok száma
	 */
	public int getGamesWon() {
		return gamesWon;
	}

	/**
	 * Visszaadja nyert és lejátszott játékok százalékos arányát.
	 * @return a nyert és lejátszott játékok százalékos aránya
	 */
	public int getPercentage() {
		return percentage;
	}

	/**
	 * Beállítja a leggyorsabb időt.
	 * @param bestTime a leggyorsabb idő felveendő értéke
	 */
	public void setBestTime(int bestTime) {
		this.bestTime = bestTime;
	}

	/**
	 * Beállítja a lejátszott játékok számát és ennek megfelelően
	 * átírja a százalékos arányt.
	 * @param gamesPlayed a lejátszott játékok felveendő értéke
	 */
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
		this.percentage = (int) ((double) gamesWon / (double) gamesPlayed * 100);
	}

	/**
	 * Beállítja a megnyert játékok számát és ennek megfelelően
	 * átírja a százalékos arányt.
	 * @param gamesWon a megnyert játékok felveendő értéke
	 */
	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
		this.percentage = (int) ((double) gamesWon / (double) gamesPlayed * 100);
	}

	/**
	 * Beállítja megnyert és lejátszott játékok százalékos arányát.
	 * @param percentage a megnyert és lejátszott játékok százalékos arányának felveendő értéke
	 */
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

}
