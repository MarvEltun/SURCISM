package com.pae14_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SelfBalanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_balance);
        setTitle(this.getString(R.string.self_balance_robot));
       // getActionBar().setHomeButtonEnabled(true);
    }
}
