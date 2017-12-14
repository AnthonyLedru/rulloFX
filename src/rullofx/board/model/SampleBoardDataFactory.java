package rullofx.board.model;

public class SampleBoardDataFactory implements BoardDataFactory{
	public BoardData createBoardData(){
		BoardData board = new BoardData(3,3);
		board.initCell(0,0,1);
		board.initCell(0,1,2);
		board.initCell(0,2,3);
		board.initCell(1,0,4);
		board.initCell(1,1,5);
		board.initCell(1,2,6);
		board.initCell(2,0,7);
		board.initCell(2,1,8);
		board.initCell(2,2,9);
		board.initRowSum(0,3);
		board.initRowSum(1,6);
		board.initRowSum(2,17);
		board.initColumnSum(0,1);
		board.initColumnSum(1,10);
		board.initColumnSum(2,15);
		return board;
	}
}
