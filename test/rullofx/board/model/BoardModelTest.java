package rullofx.board.model;

import static org.junit.Assert.*;

import java.util.EnumMap;
import java.util.Observable;
import java.util.Observer;

import org.junit.Before;
import org.junit.Test;

import rullofx.board.model.BoardModelEvent.EventType;

public class BoardModelTest implements Observer {
	EnumMap<BoardModelEvent.EventType, BoardModelEvent> receivedEvents;
	BoardDataFactory factory = new SampleBoardDataFactory();
	BoardModel model;

	@Before
	public void setUp() throws Exception {
		model = new BoardModel(factory);
		model.startGame();
		receivedEvents = new EnumMap<EventType, BoardModelEvent>(BoardModelEvent.EventType.class);
		model.addObserver(this);
	}

	@Test
	public void testToggleActiveState() {
		model.data.getCell(1, 1).setLocked(true);
		assertTrue(model.data.getCell(1, 1).isActive());
	}

	@Test
	public void testToggleLockedState() {
		model.data.getCell(1, 1).setActive(false);
		model.toggleLockedState(1, 1);
		assertFalse(model.data.getCell(1, 1).isLocked());
	}

	@Test
	public void testReset() {
		model.data.getCell(1, 1).setActive(false);
		model.data.getCell(1, 2).setLocked(true);
		model.reset();
		assertTrue(model.data.getCell(1, 1).isActive());
		assertFalse(model.data.getCell(1, 2).isLocked());
	}

	@Test
	public void testIsSolvedFalse() {
		assertFalse(model.isSolved());
	}

	@Test
	public void testIsSolved() {
		model.reset();
		model.data.getCell(2, 1).setActive(false);
		model.data.getCell(3, 1).setActive(false);
		model.data.getCell(1, 3).setActive(false);
		model.data.getCell(2, 2).setActive(false);
		model.data.getColumnSum(1).update();
		model.data.getColumnSum(2).update();
		model.data.getColumnSum(3).update();
		model.data.getRowSum(1).update();
		model.data.getRowSum(2).update();
		model.data.getRowSum(3).update();
		assertTrue(model.isSolved());
	}

	@Override
	public void update(Observable o, Object arg) {
		BoardModelEvent e = (BoardModelEvent) arg;
		receivedEvents.put(e.eventType, e);
	}

	@Test
	public void testStartGame_startEventFired() {
		// invocation d'une méthode censée émettre un événement
		model.startGame();
		// récupération de l'événement attendu
		BoardModelEvent event = receivedEvents.get(BoardModelEvent.EventType.START_EVENT);
		// vérification de sa présence
		assertNotNull(event);
	}
	
	@Test
	public void testActivationEvent(){
		model.toggleActiveState(1, 1);
		assertNotNull(receivedEvents.get(BoardModelEvent.EventType.ACTIVATION_EVENT));
	}
	
	@Test
	public void testActivationEventError(){
		model.toggleLockedState(1, 1);
		model.toggleActiveState(1, 1);
		assertNull(receivedEvents.get(BoardModelEvent.EventType.ACTIVATION_EVENT));
	}
	
	@Test
	public void testLockedEvent(){
		model.toggleLockedState(1, 1);
		assertNotNull(receivedEvents.get(BoardModelEvent.EventType.LOCK_EVENT));
	}
	
	@Test
	public void testReachedColumnEvent(){
		model.data.getCell(2, 1).setActive(false);
		model.data.getCell(3, 1).setActive(false);
		model.data.getCell(1, 1).setActive(false);
		model.toggleActiveState(1, 1);
		assertNotNull(receivedEvents.get(BoardModelEvent.EventType.REACHED_COLUMN_EVENT));
	}

	@Test
	public void testReachedRowEvent(){
		model.data.getCell(1, 1).setActive(false);
		model.data.getCell(1, 2).setActive(false);
		model.data.getCell(1, 3).setActive(false);
		model.toggleActiveState(1, 3);
		assertNotNull(receivedEvents.get(BoardModelEvent.EventType.REACHED_ROW_EVENT));
	}
	
	@Test
	public void testSolvedEvent(){
		model.reset();
		model.toggleActiveState(2, 1);
		model.toggleActiveState(3, 1);
		model.toggleActiveState(1, 3);
		model.toggleActiveState(2, 2);
		assertNotNull(receivedEvents.get(BoardModelEvent.EventType.SOLVED_EVENT));
	}
	
	

}