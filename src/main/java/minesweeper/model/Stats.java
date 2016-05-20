package minesweeper.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A játék statisztikáit tároló osztály.
 * @author Gábor
 *
 */

@XmlRootElement
public class Stats {

	/**
	 * A nehézségi szintenként eltárolt statisztikák egy {@code HashMap}-ben.  
	 */
	private Map<Difficulty, Data> statistics = new HashMap<>();

	/**
	 *  Az alapértlemezett üres konstruktor.
	 *  <p>
	 *  Létrehoz egy üres {@code Stats} objektumot.
	 */
	public Stats() {
		super();
	}

	/**
	 * Létrehoz egy {@code Stats} objektumot, aminek a {@code statistics} {@code Map}-je a
	 * paraméterben megadott lesz.
	 * @param statistics a {@code Stats} objektum {@code statistics} {@code Map}-jének az értéke
	 */
	public Stats(Map<Difficulty, Data> statistics) {
		super();
		this.statistics = statistics;
	}

	/**
	 * Visszaadja a {@code statistics} {@code Map}-et.
	 * @return a {@code statistics} {@code Map}
	 */
	public Map<Difficulty, Data> getStatistics() {
		return statistics;
	}

	/**
	 * Beálltítja a {@code statistics} {@code Map}-et a megadott értékre.
	 * @param statistics a {@code statistics} {@code Map} által felveendő értéke
	 */
	public void setStatistics(Map<Difficulty, Data> statistics) {
		this.statistics = statistics;
	}

}
