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

	public BoardData(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.cells = new Cell[rowCount][columnCount];
		this.columnSums = new Sum[columnCount];
		this.rowSums = new Sum[rowCount];
	}

	public int getColumnCount() {
		return this.columnCount;
	}

	public int getRowCount() {
		return this.rowCount;
	}

	public Cell getCell(int row, int column) {
		return this.cells[row][column];
	}

	public void initCell(int row, int column, int value) {
		this.cells[row][column] = new Cell(value);
	}

	public Sum getColumnSum(int column) {
		return this.columnSums[column];
	}

	public void initColumnSum(int column, int target) {
		Column c = new Column(column);
		Sum s = new Sum(target, c);
		columnSums[column] = s;
	}

	public Sum getRowSum(int row) {
		return this.rowSums[row];
	}

	public void initRowSum(int row, int target) {
		Row r = new Row(row);
		Sum s = new Sum(target, r);
		rowSums[row] = s;
	}
}
