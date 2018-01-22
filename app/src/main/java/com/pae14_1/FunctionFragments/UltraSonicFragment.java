package com.pae14_1.FunctionFragments;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pae14_1.HomePageActivity;
import com.pae14_1.R;

import java.io.IOException;

import static com.pae14_1.Globals.connectBT;

/**
 * Created by aslan on 11/2/2017.
 */

public class UltraSonicFragment extends MainFragment {

    public static final String TAG = UltraSonicFragment.class.getSimpleName();

    public View rootView;
    public final static String classTitle = "Ultrasonic";
    Handler bluetoothIn;
    final int handlerState = 0;  //used to identify handler message
    private StringBuilder recDataString = new StringBuilder();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView in $TAG");
        rootView = inflater.inflate(R.layout.fragment_ultra_sonic, container, false);
        initUI();
        return rootView;
    }

    public void initUI() {
        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {                                     //if message is what we want
                    String readMessage = (String) msg.obj;                                                                // msg.arg1 = bytes from connect thread
                    recDataString.append(readMessage);                                      //keep appending to string until ~
                    int endOfLineIndex = recDataString.indexOf("~");                    // determine the end-of-line
                    if (endOfLineIndex > 0) {                                           // make sure there data before ~
                        String dataInPrint = recDataString.substring(0, endOfLineIndex);    // extract string
                        ((TextView) rootView.findViewById(R.id.main_dist_indicator)).setText(dataInPrint + " cm\n");
                        Log.i("kmj", recDataString.toString());
                        if (recDataString.charAt(0) == '#')                             //if it starts with # we know it is what we are looking for
                        {
                            //  String sensor0 = recDataString.substring(1, 5);             //get sensor value from string between indices 1-5
                            //   String sensor1 = recDataString.substring(6, 10);            //same again...
                            //   String sensor2 = recDataString.substring(11, 15);
                            //   String sensor3 = recDataString.substring(16, 20);

                            //    sensorView0.setText(" Sensor 0 Voltage = " + sensor0 + "V");    //update the textviews with sensor values
                            //   sensorView1.setText(" Sensor 1 Voltage = " + sensor1 + "V");
                            //   sensorView2.setText(" Sensor 2 Voltage = " + sensor2 + "V");
                            //   sensorView3.setText(" Sensor 3 Voltage = " + sensor3 + "V");
                        }
                        recDataString.delete(0, recDataString.length());                    //clear all string data

                    }
                }
            }
        };


    }

    @Override
    public void onResume() {
        super.onResume();
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        };
//
//        //runnable.run();
        (new BtDataReader()).execute();
        Log.i("kj", "kbj");
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Activity a = getActivity();
            if (a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void onBackPressed() {
        ((HomePageActivity) getActivity()).getSupportFragmentManager().popBackStack();
        ((HomePageActivity) getActivity()).getSupportActionBar().setTitle(HomePageFragment.classTitle);
        ((HomePageActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    private class BtDataReader extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            connectBT.runReader(bluetoothIn, 0);
            return null;
        }
    }

    ///////////////////////////////////////////


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach in $TAG");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate in $TAG");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy in $TAG");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView in $TAG");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach in $TAG");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause in $TAG");
    }
}


