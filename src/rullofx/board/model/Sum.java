package rullofx.board.model;

public class Sum {

	private int target;
	private int current = 0;
	private Array<Cell> cells;
	
	public Sum(int target, Array<Cell> cells) {
		this.cells = cells;
		this.target = target;
		this.update();
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public boolean isTargetReached() {
		return this.current == this.target;
	}

	public void update() {
		int sum = 0;
		for (int i = 0; i < this.cells.size(); i++) {
			if (this.cells.get(i).isActive()) {
				sum += this.cells.get(i).getValue();
			}
		}
		this.current = sum;
	}
}
