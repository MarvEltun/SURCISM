package com.pae14_1.FunctionFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pae14_1.R;


/**
 * Created by aslan on 11/1/2017.
 */

public class HomePageFragment extends MainFragment {

    public View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_draft, container, false);

        initUI();

        return rootView;
    }

    public void initUI(){

    }
}