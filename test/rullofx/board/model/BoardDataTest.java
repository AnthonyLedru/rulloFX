package rullofx.board.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardDataTest {

	BoardData data;

	@Before
	public void setUp() throws Exception {
		data = new BoardData(3, 3);
	}

	@Test
	public void testInitCell() {
		data.initCell(1,1,9);
		assertTrue(data.getCell(1,1).getValue()==9);
	}
	
	@Test
	public void testInitColumnSum(){
		data.initCell(0,1,2);
		data.initCell(1,1,1);
		data.initCell(2,1,2);
		data.initColumnSum(1, 5);
		assertTrue( data.getColumnSum(1).getTarget() == 5 );
	}
	
	@Test
	public void testInitRowSum(){
		data.initCell(1,0,2);
		data.initCell(1,1,1);
		data.initCell(1,2,2);
		data.initRowSum(1, 5);
		assertTrue( data.getRowSum(1).getTarget() == 5 );
	}
}
