package com.pae14_1.FunctionFragments;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pae14_1.HomePageActivity;
import com.pae14_1.R;

import java.io.IOException;

/**
 * Created by MarvEltun on 03/11/2017.
 */

public class InfraRedFragment extends MainFragment {
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
        ((Button) rootView.findViewById(R.id.turnOnBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOnLed();
            }
        });

        ((Button) rootView.findViewById(R.id.turnOffBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnOffLed();
            }
        });

        ((SeekBar) rootView.findViewById(R.id.seekbar)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    ((TextView) rootView.findViewById(R.id.seekbar_data)).setText("b" + String.valueOf(progress) + "e");
                    try {
                        ((HomePageActivity) getActivity()).connectBT.btSocket
                                .getOutputStream().write(("b" + String.valueOf(progress) + "e").getBytes());
                    } catch (IOException e) {

                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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

    private void turnOffLed() {
        if (((HomePageActivity) getActivity()).connectBT.btSocket != null) {
            try {
                ((HomePageActivity) getActivity()).connectBT.btSocket.getOutputStream().write("F".getBytes());
            } catch (IOException e) {
                Toast.makeText(getActivity(), getActivity().getString(R.string.error), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void turnOnLed() {
        if (((HomePageActivity) getActivity()).connectBT.btSocket != null) {
            try {
                ((HomePageActivity) getActivity()).connectBT.btSocket.getOutputStream().write("O".getBytes());
            } catch (IOException e) {
                Toast.makeText(getActivity(), getActivity().getString(R.string.error), Toast.LENGTH_LONG).show();
            }
        }
    }
}
