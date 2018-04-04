package rullofx;

import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import rullofx.board.BoardView;
import rullofx.board.model.BoardModel;
import rullofx.board.model.BoardModelEvent;
import rullofx.board.model.BoardModelEvent.EventType;

public class RulloController {
	@FXML
	StackPane stackPane;

	@FXML
	Button nouveau;

	@FXML
	Button reset;

	private BoardView boardView;
	private Label winLabel;

	public void initialize() {
		stackPane.getChildren().add(boardView = new BoardView());
		BoardModel model = new BoardModel();
		boardView.setModel(model);
		model.startGame();
		this.winLabel = new Label();
		this.winLabel.setText("√");
		this.winLabel.getStyleClass().add("win");
		this.boardView.getModel().addObserver(new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				System.out.println(arg);
				BoardModelEvent monEvent = ((BoardModelEvent) arg);
				BoardModelEvent.EventType type = monEvent.eventType;
				nouveau.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						stackPane.getChildren().clear();
						RulloController.this.initialize();
					}
				});

				reset.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						model.reset();
						
						for (int i = 0; i < model.getColumnCount(); i++) {
							for (int j = 0; i < model.getRowCount(); j++) {	
								//RulloController.update(this, new BoardModelEvent(EventType.RESET_EVENT, i, j));
							}
						}
					}
				});
				if (type == EventType.SOLVED_EVENT) {
					stackPane.getChildren().add(winLabel);
					RulloController.this.boardView.setOpacity(0.5);
					RulloController.this.boardView.setMouseTransparent(true);
					nouveau.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							model.startGame();
							stackPane.getChildren().remove(1);
							stackPane.getChildren().remove(winLabel);
							RulloController.this.boardView.setOpacity(1);
							RulloController.this.boardView.setMouseTransparent(false);
							RulloController.this.initialize();
						}
					});

					reset.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							stackPane.getChildren().remove(winLabel);
							RulloController.this.boardView.setOpacity(1);
							RulloController.this.boardView.setMouseTransparent(false);
							model.reset();
							RulloController.this.initialize();
						}
					});

				}
			}
		});
	}
}
