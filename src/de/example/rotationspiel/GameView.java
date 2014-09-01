package de.example.rotationspiel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.view.SurfaceView;

public class GameView extends SurfaceView {
	
	private Ball ball;

	public GameView(Context context) {
		super(context);
		//transparenter Hintergrund
		setZOrderOnTop(true);
		getHolder().setFormat(PixelFormat.TRANSPARENT);
		ball = new Ball(getResources());
		//um onDraw Methode aufzurufen
		setWillNotDraw(false);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		ball.drawBitmap(canvas);
		super.onDraw(canvas);
	}
	
}
