package minesweeper.model;

/**Egy néhézségiszinthet tartozó statisztikai adatok.
 * @author Gábor
 *
 */
public class Data {
	/**
	 * A már lejátszott játékok száma. 
	 */
	private int gamesPlayed;
	/**
	 * A megnyert játékok száma. 
	 */
	private int gamesWon;
	/**
	 * A leggyorsabb idő ami alatt sikerült nyerni.
	 */
	private int bestTime;
	/**
	 * A nyert és lejátszott játékok százalékos aránya.
	 */
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
	 * Visszaadja a lejátszott játékok számát
	 * @return a lejátszott játékok száma
	 */
	public int getGamesPlayed() {
		return gamesPlayed;
	}

	/**
	 * Visszaadja a megnyert játékok számát
	 * @return a megnyert játékok száma
	 */
	public int getGamesWon() {
		return gamesWon;
	}

	/**
	 * @return
	 */
	public int getPercentage() {
		return percentage;
	}

	/**
	 * @param bestTime
	 */
	public void setBestTime(int bestTime) {
		this.bestTime = bestTime;
	}

	/**
	 * @param gamesPlayed
	 */
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
		this.percentage = (int) ((double) gamesWon / (double) gamesPlayed * 100);
	}

	/**
	 * @param gamesWon
	 */
	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
		this.percentage = (int) ((double) gamesWon / (double) gamesPlayed * 100);
	}

	/**
	 * @param percentage
	 */
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

}
