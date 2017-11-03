package com.pae14_1;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.pae14_1.FunctionFragments.HomePageFragment;
import com.pae14_1.FunctionFragments.InfraRedActivity;
import com.pae14_1.FunctionFragments.InfraRedFragment;
import com.pae14_1.FunctionFragments.MainFragment;
import com.pae14_1.FunctionFragments.RemoteControlActivity;
import com.pae14_1.FunctionFragments.RemoteControlFragment;
import com.pae14_1.FunctionFragments.SelfBalanceActivity;
import com.pae14_1.FunctionFragments.UltraSonicActivity;
import com.pae14_1.FunctionFragments.UltraSonicFragment;
import com.pae14_1.Misc.Globals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomePageActivity extends AppCompatActivity {


    public ConnectBT connectBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        connectBT = new ConnectBT();
        connectBT.execute();
        setSupportActionBar((Toolbar) findViewById(R.id.mainToolbar));
        if (savedInstanceState == null) {
            openFragment(new HomePageFragment(), HomePageFragment.classTitle, false);
        } else {
            //Todo: This is kind of hard coded solution.
            //Todo: This line should be replaced with much more universal solution.
            //Todo: It says that screen can be landscape mode only in remote control fragment.
            //  openFragment(new RemoteControlFragment(), RemoteControlFragment.classTitle, true);
        }
    }

    private void openFragment(MainFragment fragmentClass, String title, boolean backEnabled) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragmentClass); // f1_container is your FrameLayout container
        //ft.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
        setTitle(title);
        if (backEnabled) {
            ft.addToBackStack(null);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        ft.commit();
    }


    public void navigate(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.ultra_sonic:
                openFragment(new UltraSonicFragment(), UltraSonicFragment.classTitle, true);
                break;
            case R.id.infrared:
                openFragment(new InfraRedFragment(), InfraRedFragment.classTitle, true);
                break;
            case R.id.rc:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                openFragment(new RemoteControlFragment(), RemoteControlFragment.classTitle, true);
                break;
            case R.id.self_balance:
                intent = new Intent(HomePageActivity.this, SelfBalanceActivity.class);
                startActivity(intent);
                break;


        }
    }


    public class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {

        private ProgressDialog progress;
        BluetoothAdapter myBluetooth = null;
        public BluetoothSocket btSocket = null;
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        List<Fragment> arrayList = getSupportFragmentManager().getFragments();

        //Todo: optimize this algo!
        int count = 0;
        for (Fragment item : arrayList) {
            if (item != null) {
                count++;
            } else {
                break;
            }
        }
        if (count > 1) {
            ((MainFragment) arrayList.get(count - 1)).onBackPressed();
        } else {
            super.onBackPressed();
        }

    }
}
