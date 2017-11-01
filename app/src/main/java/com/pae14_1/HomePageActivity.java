package com.pae14_1;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class HomePageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ConnectBT connectBT = new ConnectBT();
        connectBT.execute();
    }

    public void navigate(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ultra_sonic:
                intent = new Intent(HomePageActivity.this, UltraSonicActivity.class);
                startActivity(intent);
                break;
            case R.id.infrared:
                intent = new Intent(HomePageActivity.this, InfraRedActivity.class);
                startActivity(intent);
                break;
            case R.id.rc:
                intent = new Intent(HomePageActivity.this, RemoteControlActivity.class);
                startActivity(intent);
                break;
            case R.id.self_balance:
                intent = new Intent(HomePageActivity.this, SelfBalanceActivity.class);
                startActivity(intent);
                break;


        }
    }


    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {

        private ProgressDialog progress;
        BluetoothAdapter myBluetooth = null;
        BluetoothSocket btSocket = null;
        private boolean isBtConnected = false;
        final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");



        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(HomePageActivity.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(Globals.address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            } catch (IOException e) {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                Toast.makeText(HomePageActivity.this, HomePageActivity.this.getString(R.string.cnt_failed), Toast.LENGTH_LONG).show();
                HomePageActivity.this.finish();
            } else {
                Toast.makeText(HomePageActivity.this, HomePageActivity.this.getString(R.string.connected), Toast.LENGTH_LONG).show();

                isBtConnected = true;
            }
            progress.dismiss();
        }
    }


}
