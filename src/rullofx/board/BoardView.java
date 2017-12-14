package rullofx.board;

import java.util.Observable;
import java.util.Observer;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import rullofx.board.model.BoardModel;
import rullofx.board.model.BoardModelEvent;

public class BoardView extends GridPane implements Observer {
    private BoardController controller = new BoardController(this);
    private BoardModel model;
    private CellView[][] cellViews;
    private RowSumView[] leftSumViews;
    private RowSumView[] rightSumViews;
    private ColumnSumView[] topSumViews;
    private ColumnSumView[] bottomSumViews;
	
    public class CellView extends Label{
    	private int row;
    	private int column;
    	
    	public CellView(int row, int column){
    		this.row = row;
    		this.column = column;
    		this.setText(Integer.toString(BoardView.this.model.getValue(this.row, this.column)));
    		this.getStyleClass().add("cell");
    		this.getStyleClass().add("active");
    		updateActiveState();
    		updateLockedState();
    		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					BoardView.this.controller.onCellClicked(event , CellView.this.row, CellView.this.column);
				}
			});
    	}
    	
		private void updateLockedState() {
			if(!BoardView.this.model.isCellLocked(row, column)){
	    		this.getStyleClass().add("unlocked");
	    		this.getStyleClass().remove("locked");
			}
			else{
				this.getStyleClass().add("locked");
				this.getStyleClass().remove("unlocked");
			}
		}

		private void updateActiveState() {
			if(!BoardView.this.model.isCellActive(row, column)){
				this.getStyleClass().remove("active");
				this.getStyleClass().add("inactive");
	    		
			}
			else{
				this.getStyleClass().remove("inactive");
				this.getStyleClass().add("active");
			}
		}
    }
    
    public class SumView extends Label{
    	public SumView(int target){
    		this.setText(Integer.toString(target));
    		this.getStyleClass().add("sum");
    	}
    	
    	public void setReached(boolean reached){
    		if(reached){
    			this.getStyleClass().add("correct");
    		}
    	}
    }
    
    public class RowSumView extends SumView{
    	private int row;
    	
    	public RowSumView(int row){
    		super(BoardView.this.model.getRowTarget(row));
    		new SumView(BoardView.this.model.getRowTarget(row));
    		this.row = row;
    		this.update();
    		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					BoardView.this.controller.onRowSumClicked(RowSumView.this.row);
				}
			});
    	}
    	
    	public void update(){
			if(!BoardView.this.model.isRowTargetReached(this.row)){
				this.getStyleClass().remove("correct");
				this.getStyleClass().add("sum");
			}
			else{
				this.getStyleClass().add("correct");
			}
    	}
    }
    
    public class ColumnSumView extends SumView{
    	private int column;
    	
    	public ColumnSumView(int column){
    		super(BoardView.this.model.getColumnTarget(column));
    		new SumView(BoardView.this.model.getColumnTarget(column));
    		this.column = column;
    		this.update();
    		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					BoardView.this.controller.onColumnSumClicked(ColumnSumView.this.column);
				}
			});
    	}
    	
    	public void update(){
			if(!BoardView.this.model.isColumnTargetReached(this.column)){
				this.getStyleClass().remove("correct");
				this.getStyleClass().add("sum");
			}
			else{
				this.getStyleClass().add("correct");
			}
    	}
    }
    
    	
	@Override
	public void update(Observable o, Object arg) {
		BoardModelEvent monEvent = ((BoardModelEvent)arg);
		BoardModelEvent.EventType type = monEvent.eventType;
		switch(type){
			case START_EVENT :
				this.init();
				break;
			case ACTIVATION_EVENT :
				this.cellViews[monEvent.row][monEvent.column].updateActiveState();
				this.leftSumViews[monEvent.row].update();
				this.rightSumViews[monEvent.row].update();
				this.topSumViews[monEvent.column].update();
				this.bottomSumViews[monEvent.column].update();
				break;
			case LOCK_EVENT:
				this.cellViews[monEvent.row][monEvent.column].updateLockedState();
				break;
			case REACHED_COLUMN_EVENT:
				this.cellViews[monEvent.row][monEvent.column].updateActiveState();
				this.topSumViews[monEvent.column].update();
				this.bottomSumViews[monEvent.column].update();
				break;
			case REACHED_ROW_EVENT:
				this.cellViews[monEvent.row][monEvent.column].updateActiveState();
				this.leftSumViews[monEvent.row].update();
				this.rightSumViews[monEvent.row].update();
				break;
			case RESET_EVENT:
				this.cellViews[monEvent.row][monEvent.column].updateActiveState();
				this.leftSumViews[monEvent.row].update();
				this.rightSumViews[monEvent.row].update();
				this.topSumViews[monEvent.column].update();
				this.bottomSumViews[monEvent.column].update();
				break;
		default:
			break;
		}
	}
	
	private void init() {
		this.leftSumViews = new RowSumView[this.model.getRowCount()];
		this.rightSumViews = new RowSumView[this.model.getRowCount()];
		this.topSumViews = new ColumnSumView[this.model.getColumnCount()];
		this.bottomSumViews = new ColumnSumView[this.model.getColumnCount()];
		
		for(int i=0; i<this.topSumViews.length;i++){
			this.topSumViews[i] = new ColumnSumView(i);
			this.addColumn(i+1, this.topSumViews[i]);
		}
		
		
		for(int i=0; i<this.leftSumViews.length;i++){
			this.leftSumViews[i] = new RowSumView(i);
			this.addRow(i+1, this.leftSumViews[i]);
		}
		

		
		this.cellViews = new CellView[this.model.getRowCount()][this.model.getColumnCount()];
		for(int i=0; i<this.cellViews.length;i++){
			for(int j= 0; j<this.cellViews[i].length;j++){
				this.cellViews[i][j] = new CellView(i,j);
				this.addRow(i+1,cellViews[i][j]);
			}
		}
		
		for(int i=0; i<this.rightSumViews.length;i++){
			this.rightSumViews[i] = new RowSumView(i);
			this.addRow(i+1, this.rightSumViews[i]);
		}
		
		for(int i=0; i<this.bottomSumViews.length;i++){
			this.bottomSumViews[i] = new ColumnSumView(i);
			this.addColumn(i+1, this.bottomSumViews[i]);
		}
	}

	public void setModel(BoardModel model){
		Observable o = (Observable)model;
		this.model = model;	
		if(this.model.countObservers() > 0){
		 	o.deleteObserver(this);
		}
		o.addObserver(this);
	}

	public BoardModel getModel(){
		return this.model;
	}
}