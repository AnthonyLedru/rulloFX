package rullofx.board.model;

import java.util.ArrayList;
import java.util.Random;

public class DefaultBoardDataFactory implements BoardDataFactory {
	
	enum Range {
		  RANGE_1_9(1,9), RANGE_2_4(2,4), RANGE_1_19(1,19);

		  private int min;
		  private int max;
		  
		  Range(int min, int max) {
		    this.min = min;
		    this.max = max;
		  }

		}

	
	private Random rand;
	
	@Override
	public BoardData createBoardData() {
		this.rand = new Random();
		int nbLigne = rand.nextInt(4)+5;
		int nbCol = rand.nextInt(4)+5;
		int ligne = 0;
		int col =0;
		int val=0;
		ArrayList<Integer> cells = new ArrayList<Integer>(nbLigne * nbCol);
		BoardData board = new BoardData(nbLigne,nbCol);
		Range inter=Range.RANGE_1_9;
		switch(rand.nextInt(3)){
			case 0 : inter = Range.RANGE_1_9;
			break;
			case 1 : inter = Range.RANGE_2_4;
			break;
			case 2 : inter = Range.RANGE_1_19;
			break;
		}

		int sumLine=0;
		int sumCol=0;
		int valeurCell = 0;
		for(int i = 0; i<nbLigne; i++){
			for(int j = 0; j<nbCol; j++){;
			valeurCell = rand.nextInt((inter.max - inter.min)+1)+inter.min;
			board.initCell(i,j,valeurCell);
			cells.add(board.getCell(i, j).getValue());
			}
		}

		for (int i = 0; i<cells.size();i++){
			if(rand.nextBoolean()){
				cells.set(i, 0);
			}
		}

		for(int i = 0; i<cells.size(); i++){
			if(i%nbCol==0 && i!=0){
				board.initRowSum(ligne, sumCol);
				sumCol = 0;
				ligne ++;
			}
			sumCol += cells.get(i);
		}
		board.initRowSum(ligne, sumCol);
		

		for(int i = 0; i<nbCol; i++){
			val = col;
			for(int j = 0; j<nbLigne-1; j++){
				sumLine += cells.get(val);
				val += nbCol;
			}	
			board.initColumnSum(i, sumLine);
			col ++;
			sumLine = 0;
		}

		return board;
	}

}
