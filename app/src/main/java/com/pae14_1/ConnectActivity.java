package com.pae14_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConnectActivity extends AppCompatActivity {
    Button blueCntBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        blueCntBtn = (Button) findViewById(R.id.cnt_btn);
        blueCntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ConnectActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
