package rullofx.board.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellTest {

	Cell cell;

	@Before
	public void setUp() throws Exception {
		cell = new Cell(42);
	}


	@Test
	public void testToggleActiveState_activeAndUnlockedCell() {
		cell.setActive(true);
		cell.setLocked(false);
		boolean returnValue = cell.toggleActiveState();
		assertTrue("Valeur de retour incorrecte", returnValue);
		assertFalse("Etat de la cellule incorrect", cell.isActive());
	}
	
	

	@Test
	public void testToggleActiveState_activeAndLockedCell() {
		cell.setActive(true);
		cell.setLocked(true);
		boolean returnValue = cell.toggleActiveState();
		assertFalse("Valeur de retour incorrecte", returnValue);
		assertTrue("Etat de la cellule incorrect", cell.isActive());
	}
	

	@Test
	public void testToggleActiveState_inactiveCell() {
		cell.setActive(false);
		cell.setLocked(false);
		boolean returnValue = cell.toggleActiveState();
		assertTrue("Valeur de retour incorrecte", returnValue);
		assertTrue("Etat de la cellule incorrect", cell.isActive());
	}
	
	
	
	public void testToggleLockState_activeAndUnlockedCell() {
		cell.setLocked(false);
		cell.setActive(true);
		assertTrue("Etat non modifie", cell.toggleLockedState());
		assertTrue("Etat de la cellule incorrect", cell.isLocked());
		
	}
	
	public void testToggleLockState_activeAndlockedCell() {
		cell.setLocked(true);
		cell.setActive(true);
		assertTrue("Etat non modifie", cell.toggleLockedState());
		assertFalse("Etat de la cellule incorrect", cell.isLocked());
		
	}

	@Test
	public void testToggleLockState_inactiveCell() {
		cell.setLocked(false);
		cell.setActive(false);
		assertFalse("Etat modifie sur cell non active", cell.toggleLockedState());
		assertFalse("Etat de la cellule incorrect", cell.isLocked());
	}
	
	
	
}
