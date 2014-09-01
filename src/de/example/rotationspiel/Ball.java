package de.example.rotationspiel;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
public class Ball {
	
	private Bitmap ballbitmap;
	private Rect destRect,bitmapRect;

	public Ball(Resources resources) {
		ballbitmap = BitmapFactory.decodeResource(resources,R.drawable.ball);
		//bitmapRect = bei mehreren Kostümen in einer Bitmap wird das gewünschte Kostüm ausgewählt
		bitmapRect = new Rect(0,0,ballbitmap.getWidth(),ballbitmap.getHeight());
		//destRect = Bildschirmkoordinaten/Bildschirmbereich wo Bitmap hingezeichnet werden soll
		destRect = new Rect(0,0,ballbitmap.getWidth(),ballbitmap.getHeight());
	}

	public void drawBitmap(Canvas canvas) {
		destRect.right = destRect.left + ballbitmap.getWidth();
		destRect.bottom = destRect.top + ballbitmap.getHeight();
		canvas.drawBitmap(ballbitmap, bitmapRect,destRect,null);
	}
}
