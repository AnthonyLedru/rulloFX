/**
 * 
 */
package rullofx.board;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class BoardController {
    private BoardView boardView;

    public BoardController(BoardView boardView) {
        this.boardView = boardView;
    }
    
    public void onCellClicked(MouseEvent event, int row,int column){
    	if(event.getButton() == MouseButton.PRIMARY)
    		this.boardView.getModel().toggleActiveState(row,column);
    	if(event.getButton() == MouseButton.SECONDARY)
    		this.boardView.getModel().toggleLockedState(row,column);
    }
    
    public void	onRowSumClicked(int row){
    	if(this.boardView.getModel().isRowTargetReached(row)){
    		for(int i=0; i<this.boardView.getModel().getColumnCount() ; i++)
    			if(!this.boardView.getModel().isCellLocked(row, i))
    				this.boardView.getModel().toggleLockedState(row, i);
    	}
    }
    
    public void	onColumnSumClicked(int column){
    	if(this.boardView.getModel().isColumnTargetReached(column)){
    		for(int i=0; i<this.boardView.getModel().getRowCount() ; i++)
    			if(!this.boardView.getModel().isCellLocked(i, column))
    		this.boardView.getModel().toggleLockedState(i, column);
    	}
    }
    
    
}
