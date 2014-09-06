package de.example.rotationspiel;

import java.util.concurrent.TimeUnit;

public class BallThread extends Thread {
	
	private Ball ball;
	private GameView gameview;

	public BallThread(Ball ball1, GameView gameview1) {
		ball = ball1;
		gameview = gameview1;
	}

	@Override
	public void run() {
		try {
			while(true) {
				TimeUnit.MILLISECONDS.sleep(20);
				try {
					ball.addx(gameview.ma.x_acc);
					ball.addy(gameview.ma.y_acc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
