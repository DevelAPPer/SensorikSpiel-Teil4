package de.example.rotationspiel;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Monster {
	
	private GameView gameview;
	private Bitmap monsterbitmap;
	public Rect destRect,bitmapRect;
	private MonsterThread monsterthread;

	public Monster(Resources resources, GameView gameView) {
		gameview = gameView;
		monsterbitmap = BitmapFactory.decodeResource(resources, R.drawable.monster);
		bitmapRect = new Rect(0,0,monsterbitmap.getWidth(),monsterbitmap.getHeight());
		destRect = new Rect(0,0,monsterbitmap.getWidth(),monsterbitmap.getHeight());
		monsterthread = new MonsterThread(this,gameview);
		monsterthread.start();
	}
	public void drawBitmap(Canvas canvas) {
		destRect.right = destRect.left + monsterbitmap.getWidth();
		destRect.bottom = destRect.top + monsterbitmap.getHeight();
		canvas.drawBitmap(monsterbitmap, bitmapRect,destRect,null);
	}
	public void setY(int value) {
		destRect.top = value;
		destRect.bottom = destRect.top + monsterbitmap.getHeight();
	}
	public void setX(int value) {
		destRect.left = value;
		destRect.right = destRect.left + monsterbitmap.getWidth();
	}

	public void addx(int value) {
		if ((destRect.right+value)>gameview.breitespielflaeche) {
			value = gameview.breitespielflaeche - destRect.right;
		}
		if ((destRect.left+value)<0) {
			value = destRect.left*(-1);
		}
		destRect.left = destRect.left+value;
		destRect.right = destRect.left + monsterbitmap.getWidth();
	}

	public void addy(int value) {
		if ((destRect.bottom+value)>gameview.hoehespielflaeche) {
			value = gameview.hoehespielflaeche - destRect.bottom;
		}
		if ((destRect.top+value)<0) {
			value = destRect.top*(-1);
		}
		destRect.top = destRect.top+value;
		destRect.bottom = destRect.top + monsterbitmap.getHeight();
	}

}
