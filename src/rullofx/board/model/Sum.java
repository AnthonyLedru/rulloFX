package rullofx.board.model;

public class Sum {

	private int target;
	private int current = 0;
	private Array<Cell> cells;
	
	public Sum(int target, Array<Cell> cells) {
		this.cells = cells;
		this.target = target;
		/*for (int i = 0; i < cells.size(); i++) {
System.out.println("  "+i);
			this.current += cells.get(i).getValue();
		}*/
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

	/**
	 * Indique si la valeur courante est égale à la valeur à atteindre
	 * 
	 * @return vrai si la valeur courante est égale à la valeur à atteindre,
	 *         faux sinon
	 */
	public boolean isTargetReached() {
		return this.current == this.target;
	}

	/**
	 * Met à jour la somme courante, en ne tenant compte que des cellules
	 * actives.
	 */
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
