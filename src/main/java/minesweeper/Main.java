 // CHECKSTYLE:OFF
package minesweeper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import minesweeper.view.GameView;

public class Main extends Application {
	
	private static Logger	logger = LoggerFactory.getLogger(Main.class);
	private GameView gameView;

	@Override
	public void start(Stage primaryStage) {
		
		logger.info("User {} started the game.", System.getProperty("user.name"));
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
				logger.info("User {} stopped the game.", System.getProperty("user.name"));
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
