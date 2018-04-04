package rullofx.board.model;

public class Cell {

	private int value;
	private boolean active = true;
	private boolean locked = false;

	public Cell(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isLocked() {
		return this.locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean toggleActiveState() {
		boolean res = false;
		if (!this.locked) {
			if (this.active) {
				this.active = false;
			} else {
				this.active = true;
			}
			res = true;
		}
	return res;
	}

	public boolean toggleLockedState() {
		boolean res = false;
		if (this.active) {
			if (this.locked) {
				this.locked = false;
			} else {
				this.locked = true;
			}
			res = true;
		}
		return res;
	}

}
