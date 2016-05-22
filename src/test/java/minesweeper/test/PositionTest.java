package minesweeper.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import minesweeper.model.Position;

public class PositionTest {

	Position position;
	
	@Before
	public void setUp() throws Exception {
		position=new Position(0, 0);
	}

	@Test
	public void equalsSelfTest() {
		assertTrue(position.equals(position));
	}
	
	@Test
	public void equalsNullTest() {
		assertFalse(position.equals(null));
	}

	@Test
	public void equalsNotPositionTest() {
		assertFalse(position.equals(new Integer(5)));
	}
	
	@Test
	public void equalsSamePositionTest() {
		Position pos = new Position();
		pos.setColumn(0);
		pos.setRow(0);
		assertTrue(position.equals(pos));
	}
}
