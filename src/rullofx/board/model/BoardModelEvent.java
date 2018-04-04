package rullofx.board.model;

public class BoardModelEvent {

	public enum EventType {
		ACTIVATION_EVENT, LOCK_EVENT, REACHED_COLUMN_EVENT, REACHED_ROW_EVENT, START_EVENT, SOLVED_EVENT, RESET_EVENT

	}
	public final EventType eventType;
	public final int row;
	public final int column;

	public BoardModelEvent(EventType e, int row, int column) {
		this.eventType = e;
		this.row = row;
		this.column = column;
	}

	public BoardModelEvent(EventType e) {
		this.eventType = e;
		this.row = 0;
		this.column = 0;
	}

	@Override
	public String toString() {
		return "BoardModelEvent [row=" + this.row + ", column=" + this.column + "]";
	}
}
