package minesweeper.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import minesweeper.model.Data;

public class DataTest {

	Data data;
	
	@Before
	public void setUp() throws Exception {
		data=new Data(10, 5, 10);
	}

	@Test
	public void percentageTest() {
		assertEquals(50, data.getPercentage());
	}
	
	@Test
	public void percentageSetPlayedGamesTest() {
		data.setGamesPlayed(20);
		assertEquals(25, data.getPercentage());
	}
	
	@Test
	public void percentageSetWonGamesTest() {
		data.setGamesWon(2);
		assertEquals(20, data.getPercentage());
	}

}
