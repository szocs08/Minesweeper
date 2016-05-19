package minesweeper;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import minesweeper.view.GameView;

public class Main extends Application {

	private GameView gameView;

	@Override
	public void start(Stage primaryStage) {
		gameView = new GameView();
		gameView.setAlignment(Pos.TOP_CENTER);
		Scene scene = new Scene(gameView);
		primaryStage.setTitle("MINESWEEPER");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				if(!gameView.exitMessage())
					event.consume();
			};
		});
		gameView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (gameView.isViewChanged()) {
					primaryStage.sizeToScene();
					primaryStage.centerOnScreen();
					gameView.setViewChanged(false);
				}
				if(gameView.isClose()){
					primaryStage.close();
				}

			}
		});

		
		primaryStage.show();
		if(gameView.loadMessage()){
			primaryStage.sizeToScene();
			primaryStage.centerOnScreen();
		}
			

	}
	
	

	public static void main(String[] args) {
		launch(args);

	}

}
