package com.kaushiksamba.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class Sensor_Details extends Fragment
{
    public SensorManager sensorManager;
    public Sensor sensor;

    public Sensor_Details()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_sensor__details, container, false);
        Bundle bundle = getArguments();
        start_sensor(bundle.getString("Sensor"));
        update_text(v);
        return v;
    }

    public void update_text(View v)
    {
        TextView name = (TextView) v.findViewById(R.id.sensor_name_detail);
        TextView vendor = (TextView) v.findViewById(R.id.sensor_vendor_detail);
        TextView version = (TextView) v.findViewById(R.id.sensor_version_detail);
        TextView power = (TextView) v.findViewById(R.id.sensor_power_detail);
        TextView range = (TextView) v.findViewById(R.id.sensor_range_detail);
        TextView resolution = (TextView) v.findViewById(R.id.sensor_resolution_detail);
        name.setText(sensor.getName());
        vendor.setText(sensor.getVendor());
        version.setText(Integer.toString(sensor.getVersion()));
        power.setText(Float.toString(sensor.getPower()));
        range.setText(Float.toString(sensor.getMaximumRange()));
        resolution.setText(Float.toString(sensor.getResolution()));
    }
    public void start_sensor(String sensor_name)
    {
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        switch (sensor_name)
        {
            case "Accelerometer":   sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                                    break;
            case "Gyroscope":       sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                                    break;
            case "Proximity":       sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                                    break;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }
}
