package com.pae14_1;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomePageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
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


}
