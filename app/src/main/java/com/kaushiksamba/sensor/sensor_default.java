package com.kaushiksamba.sensor;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class sensor_default extends ActionBarActivity {

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_default);

        Intent intent = getIntent();
        bundle = getIntent().getExtras();
        String sensor_name = intent.getStringExtra("Sensor");
/*
//  When returning from the proximity sensor graphic, bundled data is no longer available
        if(bundle==null)
        {
            sensor_name = "Proximity";
            bundle = new Bundle();
            bundle.putString("Sensor",sensor_name);
        }
*/
        if(findViewById(R.id.fragment_container)!=null)
        {
            if(savedInstanceState!=null)
            {
                return ;
            }
            if(sensor_name.equals("Accelerometer") || sensor_name.equals("Gyroscope") || sensor_name.equals("Proximity"))
            {
                AccelerometerFragment fragment = new AccelerometerFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment, "data").commit();
            }
        }
        enable_graphic_button(sensor_name);
    }

    private void enable_graphic_button(final String sensor_name)
    {
        if(sensor_name.equals("Proximity"))
        {
            Button button = (Button) findViewById(R.id.view_graphic);
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(sensor_name.equals("Proximity"))
                    {
                        Intent intent = new Intent(getApplicationContext(),ProximityGraphic.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
    public void show_hardware(View view)
    {
        if(getSupportFragmentManager().findFragmentByTag("hardware")==null)
        {
            Sensor_Details newfragment = new Sensor_Details();
            newfragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,newfragment,"hardware").commit();
        }
    }
    public void show_data(View view)
    {
        if(getSupportFragmentManager().findFragmentByTag("data")==null)
        {
            AccelerometerFragment newfragment = new AccelerometerFragment();
            newfragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,newfragment,"data").commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode==1) //Proximity = 1
        {
            bundle.putString("Sensor","Proximity");
        }
    }
}
