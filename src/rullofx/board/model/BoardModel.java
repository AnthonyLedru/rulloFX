/**
 * 
 */
package rullofx.board.model;

import java.util.Observable;

public class BoardModel extends Observable {
	BoardData data = null;
	private BoardDataFactory boardDataFactory;

	public BoardModel() {
		this.boardDataFactory = new SampleBoardDataFactory();
	}

	public BoardModel(BoardDataFactory boardDataFactory) {
		this.boardDataFactory = boardDataFactory;
	}

	public int getColumnCount() {
		return this.data.getColumnCount();
	}

	public int getRowCount() {
		return this.data.getRowCount();
	}

	public int getValue(int row, int column) {
		return this.data.getCell(row, column).getValue();
	}

	public boolean isCellActive(int row, int column) {
		return this.data.getCell(row, column).isActive();
	}

	public boolean isCellLocked(int row, int column) {
		return this.data.getCell(row, column).isLocked();
	}

	public int getColumnTarget(int column) {
		return this.data.getColumnSum(column).getTarget();
	}

	public boolean isColumnTargetReached(int column) {
		return (this.data.getColumnSum(column).getTarget() == this.data.getColumnSum(column).getCurrent());
	}

	public int getRowTarget(int row) {
		return this.data.getRowSum(row).getTarget();
	}

	public boolean isRowTargetReached(int row) {
		return (this.data.getRowSum(row).getTarget() == this.data.getRowSum(row).getCurrent());
	}

	public boolean isSolved() {
		boolean res = true;
		for (int i = 0; i < this.data.getRowCount() && res; i++) {
			res = this.isRowTargetReached(i);
		}
		for (int i = 0; i < this.data.getColumnCount() && res; i++) {
			res = this.isColumnTargetReached(i);
		}
		return res;
	}

	public void startGame() {
		data = boardDataFactory.createBoardData();
		this.setChanged();
		this.notifyObservers(new BoardModelEvent(BoardModelEvent.EventType.START_EVENT));
	}

	public void reset() {
		for (int i = 0; i < data.getRowCount() ; i++) {
			for (int j = 0; j < data.getColumnCount(); j++) {
				data.getCell(i, j).setLocked(false);
				data.getCell(i, j).setActive(true);
			}
		}
		for (int i = 0; i < data.getRowCount() ; i++) {
			this.data.getRowSum(i).update();
		}
		for (int i = 0; i < data.getColumnCount(); i++) {
			this.data.getColumnSum(i).update();
		}
		
	}

	public void toggleActiveState(int row, int column) {
		if (!data.getCell(row, column).isLocked()) {
			data.getCell(row, column).setActive(!data.getCell(row, column).isActive());
			data.getColumnSum(column).update();
			data.getRowSum(row).update();
			this.setChanged();
			if (this.isSolved()) {
				this.setChanged();
				this.notifyObservers(new BoardModelEvent(BoardModelEvent.EventType.SOLVED_EVENT));
			}
			if (this.data.getColumnSum(column).isTargetReached()) {
				this.setChanged();
				this.notifyObservers(new BoardModelEvent(BoardModelEvent.EventType.REACHED_COLUMN_EVENT, row, column));
			}
			if (this.data.getRowSum(row).isTargetReached()) {
				this.setChanged();
				this.notifyObservers(new BoardModelEvent(BoardModelEvent.EventType.REACHED_ROW_EVENT, row, column));
			}
			this.notifyObservers(new BoardModelEvent(BoardModelEvent.EventType.ACTIVATION_EVENT, row, column));
		}
	}

	public void toggleLockedState(int row, int column) {
		if (data.getCell(row, column).isActive()) {
			data.getCell(row, column).setLocked(!data.getCell(row, column).isLocked());
			data.getColumnSum(column).update();
			data.getRowSum(row).update();
			this.setChanged();
			this.notifyObservers(new BoardModelEvent(BoardModelEvent.EventType.LOCK_EVENT, row, column));
		}
	}
}
