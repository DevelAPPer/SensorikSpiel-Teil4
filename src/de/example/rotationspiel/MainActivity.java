package de.example.rotationspiel;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.Surface;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends ActionBarActivity implements SensorEventListener {
	
	private SensorManager sm;
	private Sensor accelerometer;
	private Display dspl;
	private Context context;
	public int x_acc,y_acc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = MainActivity.this;
		//Title Bar verstecken
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Leiste mit Akkustand und Uhrzeit verstecken
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//Zugriff auf SensorManager,Sensor und Display
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		WindowManager mWindowManger = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		dspl = mWindowManger.getDefaultDisplay();
		//GameView starten
		setContentView(new GameView(this));
		}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		//wird aufgerufen wenn sich die Messgenauigkeit des Beschleunigungssensores ändert
		//eng Accelerometer
	}

	@Override
	public void onSensorChanged(SensorEvent event) {		
		event.values[0]*=10;
		event.values[1]*=10;
		event.values[2]*=10; //10=speedfactor
		switch (dspl.getRotation()) {
		case Surface.ROTATION_0:
			x_acc = (int) -event.values[0];
			y_acc = (int) event.values[1];
			break;
		case Surface.ROTATION_90:
			//Bei Rotation 90 und Rotation 270 werden x-Achse und y-Achse getauscht, da sich die Lage
			//des Smartphones ändert
			x_acc = (int) event.values[1];
			y_acc = (int) event.values[0];
			break;
		case Surface.ROTATION_180:
			//Vorzeichen der x-Achse und y-Achse werden getauscht
			x_acc = (int) event.values[0];
			y_acc = (int) -event.values[1];
			break;
		case Surface.ROTATION_270:
			x_acc = (int) -event.values[1];
			y_acc = (int) -event.values[0];
			break;
		}
	}
	//onPause() wird aufgerufen wenn sich das Smartphone schlafen legt 
	@Override
	protected void onPause() {
		super.onPause();
		//Im Zustand werden keine Sensor Werte benötigt
		sm.unregisterListener(this);
	}
	//onResume() wird aufgerufen wenn das Smartphone "aufgeweckt" wird
	@Override
	protected void onResume() {
		super.onResume();
		//Sensor Werte werden wieder verarbeitet
		sm.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_GAME);
	}
}
