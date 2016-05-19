package minesweeper.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Timer extends HBox {

	private int time = 0;
	private Label timeLabel = new Label(String.valueOf(time));

	private Timeline timeline;

	public Timer() {
		super();
		timeLabel.setFont(new Font("Arial", 15));
		timeLabel.setAlignment(Pos.CENTER);
		timeLabel.setMinWidth(25);
		this.getChildren().add(timeLabel);
		timeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> {
			time++;
			timeLabel.setText(String.valueOf(time));
		}));
		timeline.setCycleCount(Animation.INDEFINITE);

	}

	public void start() {
		timeline.play();
	}
	
	public void reset(){
		time=0;
		timeLabel.setText(String.valueOf(time));
	}

	public void stop() {
		timeline.stop();
	}

	public void nullify() {
		time = 0;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
