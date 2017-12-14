package rullofx.board.model;

public class BoardData {
	private int columnCount;
	private int rowCount;
	private Cell[][] cells;
	private Sum[] columnSums;
	private Sum[] rowSums;

	public class Column implements Array<Cell> {
		private int column;

		public Column(int column) {
			this.column = column;
		}

		@Override
		public int size() {
			return BoardData.this.cells[this.column].length;
		}

		@Override
		public Cell get(int row) {
			return BoardData.this.cells[row][this.column];
		}
	}

	public class Row implements Array<Cell> {
		private int row;

		public Row(int row) {
			this.row = row;
		}

		@Override
		public int size() {
			return BoardData.this.cells[this.row].length;
		}

		@Override
		public Cell get(int column) {
			return BoardData.this.cells[this.row][column];
		}
	}

	/**
	 * Constructeur
	 * 
	 * @param rowCount
	 *            nombre de lignes
	 * @param columnCount
	 *            nombre de colonnes
	 */
	public BoardData(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.cells = new Cell[rowCount][columnCount];
		this.columnSums = new Sum[columnCount];
		this.rowSums = new Sum[rowCount];
	}

	/**
	 * Retourne le nombre de colonnes du plateau de jeu
	 * 
	 * @return nombre de colonnes
	 */
	public int getColumnCount() {
		return this.columnCount;
	}

	/**
	 * Retourne le nombre de lignes du plateau de jeu
	 * 
	 * @return nombre de lignes
	 */
	public int getRowCount() {
		return this.rowCount;
	}

	/**
	 * Retourne une cellule du plateau de jeu
	 * 
	 * @param row
	 *            ligne de la cellule
	 * @param column
	 *            colonne de la cellule
	 * @return cellule
	 */
	public Cell getCell(int row, int column) {
		return this.cells[row][column];
	}

	/**
	 * Initialise une cellule du plateau de jeu.
	 * 
	 * @param row
	 *            ligne de la cellule
	 * @param column
	 *            colonne de la cellule
	 * @param value
	 *            valeur de la cellule
	 */
	public void initCell(int row, int column, int value) {
		this.cells[row][column] = new Cell(value);
	}

	/**
	 * Retourne la somme d'une colonne du plateau de jeu
	 * 
	 * @param column
	 *            colonne de la somme
	 * @return somme
	 */
	public Sum getColumnSum(int column) {
		return this.columnSums[column];
	}

	/**
	 * Initialise une somme de colonne du plateau de jeu
	 * 
	 * @param column
	 *            colonne de la somme
	 * @param target
	 *            somme à atteindre pour la colonne
	 */
	public void initColumnSum(int column, int target) {
		Column c = new Column(column);
		Sum s = new Sum(target, c);
		columnSums[column] = s;
	}

	/**
	 * Retourne la somme d'une ligne du plateau de jeu
	 * 
	 * @param row
	 *            ligne de la somme
	 * @return somme
	 */
	public Sum getRowSum(int row) {
		return this.rowSums[row];
	}

	/**
	 * Initialise une somme de ligne du plateau de jeu
	 * 
	 * @param row
	 *            ligne de la somme
	 * @param target
	 *            somme à atteindre pour la ligne
	 */
	public void initRowSum(int row, int target) {
		Row r = new Row(row);
		Sum s = new Sum(target, r);
		rowSums[row] = s;
	}
}
