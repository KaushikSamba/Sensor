package com.kaushiksamba.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class ProximityGraphic extends ActionBarActivity {

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_graphic);
        img = (ImageView) findViewById(R.id.lightbulb);
        start_sensor();
    }

    private void start_sensor()
    {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event)
            {
                int id;
                if(event.values[0]==sensor.getMaximumRange()) id = getResources().getIdentifier("on","drawable",getPackageName());
                    else id = getResources().getIdentifier("off","drawable",getPackageName());
                img.setImageResource(id);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        },sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop()
    {
        setResult(1);
        finish();
        super.onStop();
    }
}
