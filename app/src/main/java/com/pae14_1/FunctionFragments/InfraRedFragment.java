package com.pae14_1.FunctionFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pae14_1.HomePageActivity;
import com.pae14_1.R;

/**
 * Created by MarvEltun on 03/11/2017.
 */

public class InfraRedFragment extends MainFragment{
    public View rootView;
    public final static String classTitle = "Line Follower";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_infra_red, container, false);

        initUI();

        return rootView;
    }

    public void initUI() {


    }

    @Override
    public void onBackPressed() {
        ((HomePageActivity) getActivity()).getSupportFragmentManager().popBackStack();
        ((HomePageActivity) getActivity()).getSupportActionBar().setTitle(HomePageFragment.classTitle);
        ((HomePageActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
