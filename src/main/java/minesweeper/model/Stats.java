package minesweeper.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stats {

	Map<Difficulty, Data> statistics = new HashMap<>();

	public Stats() {
		super();
	}

	public Stats(Map<Difficulty, Data> statistics) {
		super();
		this.statistics = statistics;
	}

	public Map<Difficulty, Data> getStatistics() {
		return statistics;
	}

	public void setStatistics(Map<Difficulty, Data> statistics) {
		this.statistics = statistics;
	}

}
