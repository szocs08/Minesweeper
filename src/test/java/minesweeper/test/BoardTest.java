package minesweeper.test;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import minesweeper.Board;
import minesweeper.model.Difficulty;
import minesweeper.model.Field;
import minesweeper.model.Position;

public class BoardTest {

	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board(Difficulty.EASY);
		board.minePlanting(new Position(0, 0));
	}

	@Test
	public void emptyConstructorTest() {

		board = new Board();
		assertEquals(0, board.getRow());
		assertEquals(0, board.getColumn());
		assertEquals(0, board.getMineNumber());

	}
	
	@Test
	public void easyConstructorTest() {

		board = new Board(Difficulty.MEDIUM);
		assertEquals(16, board.getRow());
		assertEquals(16, board.getColumn());
		assertEquals(40, board.getMineNumber());

	}

	@Test
	public void mediumConstructorTest() {

		board = new Board(Difficulty.MEDIUM);
		assertEquals(16, board.getRow());
		assertEquals(16, board.getColumn());
		assertEquals(40, board.getMineNumber());
	}

	@Test
	public void hardConstructorTest() {

		board = new Board(Difficulty.HARD);
		assertEquals(16, board.getRow());
		assertEquals(30, board.getColumn());
		assertEquals(99, board.getMineNumber());
	}

	@Test
	public void customConstructorLowValueTest() {
		board = new Board(Difficulty.CUSTOM);

		board.setCustomProperties(8, 8, 9);

		assertEquals(9, board.getRow());
		assertEquals(9, board.getColumn());
		assertEquals(10, board.getMineNumber());

	}

	@Test
	public void customConstructorHighValueTest() {
		board = new Board(Difficulty.CUSTOM);

		board.setCustomProperties(25, 31, 577);

		assertEquals(24, board.getRow());
		assertEquals(30, board.getColumn());
		assertEquals(576, board.getMineNumber());

	}

	@Test
	public void customConstructorRightValueTest() {
		board = new Board(Difficulty.CUSTOM);

		board.setCustomProperties(24, 30, 576);

		assertEquals(24, board.getRow());
		assertEquals(30, board.getColumn());
		assertEquals(576, board.getMineNumber());

	}
	
	@Test
	public void setterTest() {
		board.setDifficulty(Difficulty.CUSTOM);
		board.setRow(10);
		board.setColumn(10);
		board.setMineNumber(10);
		board.setFlags(10);
		board.setTime(10);
		board.setMinesPlanted(true);
		assertEquals(Difficulty.CUSTOM,board.getDifficulty());
		assertEquals(10,board.getRow());
		assertEquals(10,board.getColumn());
		assertEquals(10,board.getFlags());
		assertEquals(10,board.getMineNumber());
		assertEquals(10,board.getTime());
		assertTrue(board.isMinesPlanted());

	}

	@Test
	public void minePlantingStartPositionTest() {
		assertFalse(board.getField(new Position(0, 0)).isMine());
	}

	@Test
	public void minePlantingMineNumberTest() {
		int count = 0;
		for (Map.Entry<Position, Field> entry : board.getBoard().entrySet()) {
			if (entry.getValue().isMine())
				count++;
		}
		assertEquals(10, count);
	}

	@Test
	public void showFieldTest() {
		board.showField(new Position(0, 0));
		assertTrue(board.getField(new Position(0, 0)).isVisible());
	}
	
	@Test
	public void shownFieldTest() {
		board.getBoard().put(new Position(0,0), new Field(1,false));
		board.showField(new Position(0,0));
		assertEquals(1, board.getShownFields());
	}

	@Test
	public void showMineTest() {
		Position mine = new Position();
		for (Map.Entry<Position, Field> entry : board.getBoard().entrySet()) {
			if (entry.getValue().isMine())
				mine = entry.getKey();
		}
		board.showField(mine);
		assertTrue(board.getField(mine).isVisible());

		assertTrue(board.getField(mine).isVisible());
	}

	@Test
	public void showAllMinesTest() {
		Position mine = new Position();
		int count = 0;

		for (Map.Entry<Position, Field> entry : board.getBoard().entrySet()) {
			if (entry.getValue().isMine())
				mine = entry.getKey();
		}
		board.showField(mine);

		for (Map.Entry<Position, Field> entry : board.getBoard().entrySet()) {
			if (entry.getValue().isMine() && entry.getValue().isVisible())
				count++;
		}

		assertEquals(10, count);
	}

	@Test
	public void flagAMineTest() {
		board.flagAMine(new Position(0, 0));
		assertTrue(board.getField(new Position(0, 0)).isFlaged());

	}
	
	@Test
	public void flagAFlagedMineTest() {
		board.flagAMine(new Position(0, 0));
		board.flagAMine(new Position(0, 0));
		assertFalse(board.getField(new Position(0, 0)).isFlaged());

	}
	
	@Test
	public void flagAVisibleMineTest() {
		board.showField(new Position(0, 0));
		board.flagAMine(new Position(0, 0));
		assertFalse(board.getField(new Position(0, 0)).isFlaged());

	}
	
	@Test
	public void flagAMineWhenWonTest() {
		board.setShownFields(71);
		board.flagAMine(new Position(0, 0));
		assertFalse(board.getField(new Position(0, 0)).isFlaged());

	}
	
	@Test
	public void flagAMineWhenBlownUpTest() {
		board.setBlownUp(true);
		board.flagAMine(new Position(0, 0));
		assertFalse(board.getField(new Position(0, 0)).isFlaged());

	}
	
	@Test
	public void remainingMinesTest() {
		board.flagAMine(new Position(0, 0));
		assertEquals(9, board.remainingMines());

	}
	
	
	@Test
	public void wonBoardTest() {
		board.setShownFields(71);
		assertTrue(board.won());

	}
	
	@Test
	public void notYetWonBoardTest() {
		assertFalse(board.won());

	}

}
