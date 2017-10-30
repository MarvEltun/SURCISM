package com.pae14_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class RemoteControlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);
        setTitle("Remote Control");

        setSupportActionBar((Toolbar) findViewById(R.id.mainToolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
