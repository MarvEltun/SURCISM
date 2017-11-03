package com.pae14_1.FunctionFragments;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.pae14_1.HomePageActivity;
import com.pae14_1.R;

/**
 * Created by aslan on 11/2/2017.
 */

public class UltraSonicFragment extends MainFragment {

    public View rootView;
    public final static String classTitle = "Ultrasonic";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ultra_sonic, container, false);

        initUI();

        return rootView;
    }

    public void initUI() {


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            Activity a = getActivity();
            if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void onBackPressed() {
        ((HomePageActivity) getActivity()).getSupportFragmentManager().popBackStack();
        ((HomePageActivity) getActivity()).getSupportActionBar().setTitle(HomePageFragment.classTitle);
        ((HomePageActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

}


