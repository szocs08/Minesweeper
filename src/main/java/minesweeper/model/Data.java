package minesweeper.model;

public class Data {
	int gamesPlayed;
	int gamesWon;
	int bestTime;
	int percentage;

	public Data() {
		super();
	}

	public Data(int gamesPlayed, int gamesWon, int bestTime) {
		super();
		this.gamesPlayed = gamesPlayed;
		this.gamesWon = gamesWon;
		this.bestTime = bestTime;
		this.percentage = (int) ((double) gamesWon / (double) gamesPlayed * 100);
	}

	public int getBestTime() {
		return bestTime;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setBestTime(int bestTime) {
		this.bestTime = bestTime;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
		this.percentage = (int) ((double) gamesWon / (double) gamesPlayed * 100);
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
		this.percentage = (int) ((double) gamesWon / (double) gamesPlayed * 100);
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

}
