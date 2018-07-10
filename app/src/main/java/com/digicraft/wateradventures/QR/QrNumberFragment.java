package com.digicraft.wateradventures.QR;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.digicraft.wateradventures.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QrNumberFragment extends Fragment {


    public QrNumberFragment() {
        // Required empty public constructor
    }

    Opener filter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        filter = (Opener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_qr_number, container, false);

        final ImageButton filterBtn = root.findViewById(R.id.filter_icon);
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter.openRightNavigationDrawer();
            }
        });

        final ImageButton scannerBtn = root.findViewById(R.id.scanner_icon);
        scannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter.goToEmailFragment();
            }
        });

        return root;
    }

}
