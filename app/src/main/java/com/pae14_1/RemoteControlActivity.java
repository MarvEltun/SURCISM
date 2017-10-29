package com.pae14_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RemoteControlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);
        setTitle(this.getString(R.string.rc_robot));

    }
}
