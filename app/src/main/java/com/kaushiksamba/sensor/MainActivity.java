package com.kaushiksamba.sensor;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ActionBarActivity
{

    String array[] = {"Accelerometer","Gyroscope","Proximity","Sound","Magnetic","Temperature"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setMenuItems();
        startSensor();
    }

    private void startSensor()
    {
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //    Toast.makeText(getApplicationContext(), array[position], Toast.LENGTH_SHORT).show();
                Intent intent = null;
                switch (array[position])
                {
                    case "Accelerometer":   intent = new Intent(getApplicationContext(), sensor_default.class);
                                            break;
                    case "Gyroscope":       intent = new Intent(getApplicationContext(), sensor_default.class);
                                            break;
                    case "Proximity":       intent = new Intent(getApplicationContext(), sensor_default.class);
                                            break;
                }
                if (intent == null)
                    Toast.makeText(getApplicationContext(), "Not enabled", Toast.LENGTH_SHORT).show();
                else
                {
                    intent.putExtra("Sensor", array[position]);
                    startActivity(intent);
                }
            }
        });
    }
    public class myArrayAdapter extends BaseAdapter
    {
        private Context context;
        private String[] items;
        private LayoutInflater inflater;

        public myArrayAdapter(Context context,String[] items)
        {
            this.context = context;
            this.items = items;
            inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount()
        {
            return items.length;
        }

        @Override
        public String getItem(int position)
        {
            return items[position];
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view = inflater.inflate(R.layout.custom_menu_list_item,parent,false);
            TextView sensor_name = (TextView) view.findViewById(R.id.sensor_name);
            sensor_name.setText(items[position] + " Sensor");
            ImageView sensor_image = (ImageView) view.findViewById(R.id.img);
            int id = getResources().getIdentifier(items[position].toLowerCase(),"drawable",getPackageName());
            sensor_image.setImageResource(id);
            return view;
        }
    }
    private void setMenuItems()
    {
        //  ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,R.layout.custom_menu_list_item,array);
        myArrayAdapter adapter = new myArrayAdapter(this,array);
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
}
