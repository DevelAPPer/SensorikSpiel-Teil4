package de.example.rotationspiel;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
public class Ball {
	
	private Bitmap ballbitmap;
	private Rect destRect,bitmapRect;
	private GameView gameview;
	private BallThread ballthread;
	
	public Ball(Resources resources, GameView gameView1) {
		gameview = gameView1;
		ballbitmap = BitmapFactory.decodeResource(resources,R.drawable.ball);
		//bitmapRect = bei mehreren Kostümen in einer Bitmap wird das gewünschte Kostüm ausgewählt
		bitmapRect = new Rect(0,0,ballbitmap.getWidth(),ballbitmap.getHeight());
		//destRect = Bildschirmkoordinaten/Bildschirmbereich wo Bitmap hingezeichnet werden soll
		destRect = new Rect(0,0,ballbitmap.getWidth(),ballbitmap.getHeight());
		ballthread = new BallThread(Ball.this,gameview);
		ballthread.start();
	}
		
	public void drawBitmap(Canvas canvas) {
		destRect.right = destRect.left + ballbitmap.getWidth();
		destRect.bottom = destRect.top + ballbitmap.getHeight();
		canvas.drawBitmap(ballbitmap, bitmapRect,destRect,null);
	}

	public void addx(int value) {
		if ((destRect.right+value)>gameview.breitespielflaeche) {
			value = gameview.breitespielflaeche - destRect.right;
		}
		if ((destRect.left+value)<0) {
			value = destRect.left*(-1);
		}
		destRect.left = destRect.left+value;
		destRect.right = destRect.left + ballbitmap.getWidth();
	}

	public void addy(int value) {
		if ((destRect.bottom+value)>gameview.hoehespielflaeche) {
			value = gameview.hoehespielflaeche - destRect.bottom;
		}
		if ((destRect.top+value)<0) {
			value = destRect.top*(-1);
		}
		destRect.top = destRect.top+value;
		destRect.bottom = destRect.top + ballbitmap.getHeight();
	}
}
