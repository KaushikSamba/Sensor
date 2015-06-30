package com.kaushiksamba.sensor;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class AccelerometerFragment extends Fragment implements SensorEventListener
{
    public SensorManager sensorManager;
    public SensorEventListener sensorEventListener;
    public Sensor sensor;
    public String sensor_name;
    boolean all_three;
    TextView x_data, y_data, z_data;

    public AccelerometerFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_accelerometer, container, false);
        Bundle bundle = getArguments();
        sensor_name = bundle.getString("Sensor");
        x_data = (TextView) v.findViewById(R.id.x_detail);
        y_data = (TextView) v.findViewById(R.id.y_detail);
        z_data = (TextView) v.findViewById(R.id.z_detail);
        start_sensor();
        if(!all_three) if_not_all_three(v);
        return v;
    }

    public void start_sensor()
    {
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        switch (sensor_name)
        {
            case "Accelerometer":   sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                                    all_three = true;
                                    break;
            case "Gyroscope":       sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                                    all_three = true;
                                    break;
            case "Proximity":       sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                                    all_three = false;
                                    break;
        }
        sensorEventListener = this;
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void if_not_all_three(View v)
    {
        y_data.setVisibility(View.INVISIBLE);
        z_data.setVisibility(View.INVISIBLE);
        TextView y_placeholder = (TextView) v.findViewById(R.id.y_name);
            y_placeholder.setVisibility(View.INVISIBLE);
        TextView z_placeholder = (TextView) v.findViewById(R.id.z_name);
            z_placeholder.setVisibility(View.INVISIBLE);
        String new_string = "Hello";
        if(sensor_name.equals("Proximity")) new_string = "Distance";
        TextView x_placeholder = (TextView) v.findViewById(R.id.x_name);
            x_placeholder.setText(new_string);
    }
    @Override
    public void onSensorChanged(SensorEvent event)
    {
//        Sensor newSensor = event.sensor;
//        if(newSensor.getType()==Sensor.TYPE_ACCELEROMETER)
//        {
        float x = event.values[0];
        x_data.setText(Float.toString(x));
        if(all_three)
        {
            float y = event.values[1];
            float z = event.values[2];
            y_data.setText(Float.toString(y));
            z_data.setText(Float.toString(z));
        }
/*
            else
            {
                y_data.setVisibility(View.INVISIBLE);
                z_data.setVisibility(View.INVISIBLE);
            }
*/
//        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume()
    {
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }
}
