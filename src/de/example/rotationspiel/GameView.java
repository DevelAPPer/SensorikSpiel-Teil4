package de.example.rotationspiel;

import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	
	public Ball ball;
	public MainActivity ma;
	public int hoehespielflaeche,breitespielflaeche;
	private Monster monster;

	public GameView(MainActivity ma1) {
		super(ma1);
		ma = ma1;
		//transparenter Hintergrund
		setZOrderOnTop(true);
		getHolder().setFormat(PixelFormat.TRANSPARENT);
		ball = new Ball(getResources(),this);
		monster = new Monster(getResources(),this);
		//um onDraw Methode aufzurufen
		setWillNotDraw(false);
		//Display ausmessen
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		breitespielflaeche = metrics.widthPixels;
		hoehespielflaeche = metrics.heightPixels;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		postInvalidate();
		monster.drawBitmap(canvas);
		ball.drawBitmap(canvas);
		super.onDraw(canvas);
	}
}
