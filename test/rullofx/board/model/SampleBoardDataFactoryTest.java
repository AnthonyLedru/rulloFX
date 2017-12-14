package rullofx.board.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SampleBoardDataFactoryTest {

	BoardData data;
	
	@Before
	public void setUp() throws Exception {
		SampleBoardDataFactory board = new SampleBoardDataFactory();
		data = board.createBoardData();
	}
	
	@Test
	public void test() {
		assertTrue(data.getRowSum(1).getTarget() == 3);
		assertTrue(data.getRowSum(1).getCurrent() == 6);
		data.getCell(1, 1).setActive(false);
		assertTrue(data.getRowSum(1).getCurrent() == 5);
	}

}
