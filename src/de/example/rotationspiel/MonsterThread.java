package de.example.rotationspiel;

import java.util.concurrent.TimeUnit;

public class MonsterThread extends Thread {

	private Monster monster;
	private GameView gameview;
	private int factorx,factory;
	private int monstermittelx,monstermittely;
	private int ballmittelx,ballmittely;
	private int differenzpositionenx,differenzpositioneny;

	public MonsterThread(Monster monster1, GameView gameview1) {
		monster = monster1;
		gameview = gameview1;
	}
	@Override
	public void run() {
		try {
			monster.setX(gameview.breitespielflaeche - gameview.breitespielflaeche/2);
			monster.setY(gameview.hoehespielflaeche-gameview.hoehespielflaeche/3);
			while(true) {
				ballmittelx = (gameview.ball.destRect.left+gameview.ball.destRect.right)/2;
				ballmittely = (gameview.ball.destRect.top+gameview.ball.destRect.bottom)/2;
				monstermittelx = (monster.destRect.left+monster.destRect.right)/2;
				monstermittely = (monster.destRect.top+monster.destRect.bottom)/2;
				differenzpositionenx = ballmittelx - monstermittelx;
				differenzpositioneny = ballmittely - monstermittely;
				if (differenzpositioneny == 0 ) {
					factory = 0;
				} else if (differenzpositioneny>0) {
					factory = 1;
				} else 
					factory = -1;
				if (differenzpositionenx == 0) {
					factorx = 0;
				} else if(differenzpositionenx>0) {
					factorx = 1;
				} else 
					factorx = -1;
				TimeUnit.MILLISECONDS.sleep(10);
				monster.addx(5*factorx);
				monster.addy(5*factory);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.run();
	}
	

}
