package com.pae14_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;


public class ConnectActivity extends AppCompatActivity {
    Button blueCntBtn;
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    ListView devicelist;


    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView av, View v, int arg2, long arg3) {
            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);
            // Make an intent to start next activity.
            Intent i = new Intent(ConnectActivity.this, HomePageActivity.class);
            //Change the activity.
            Toast.makeText(getApplicationContext(), address, Toast.LENGTH_LONG).show();
            //i.putExtra(EXTRA_ADDRESS, address); //this will be received at ledControl (class) Activity
            startActivity(i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        blueCntBtn = (Button) findViewById(R.id.cnt_btn);
        enablingBluetooth();
        blueCntBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pairedDevicesList();
                //Intent intent = new Intent(ConnectActivity.this, HomePageActivity.class);
                //startActivity(intent);
                //finish();
            }
        });
    }

    public void enablingBluetooth() {

        myBluetooth = BluetoothAdapter.getDefaultAdapter();

        if (myBluetooth == null) {
            //Showing a message that the device has no bluetooth adapter
            Toast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.not_available_bluetooth), Toast.LENGTH_LONG).show();

            //finish();
        } else {
            if (!myBluetooth.isEnabled()) {
                //Ask to the user turn the bluetooth on
                Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnBTon, 1);
            }
        }
    }


    private void pairedDevicesList() {
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice bt : pairedDevices) {
                list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        devicelist = (ListView) findViewById(R.id.bluetooth_devices);
        devicelist.setVisibility(View.VISIBLE);
        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.devices_item, list);
        devicelist.setAdapter(adapter);
        devicelist.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked

    }

}


