package com.digicraft.wateradventures.QR;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.digicraft.wateradventures.Adapters.ExtraOrdinaryAdapter;
import com.digicraft.wateradventures.ExtraOrdCancellation.ExtraOrdinaryActivity;
import com.digicraft.wateradventures.ModifyReservation.ModifyReservationActivity;
import com.digicraft.wateradventures.R;
import com.digicraft.wateradventures.Sales.SalesMainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class QrNumberFragment extends Fragment {


    public QrNumberFragment() {
        // Required empty public constructor
    }

    Opener filter;
    TextView modifyReservation;

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

        modifyReservation = root.findViewById(R.id.modifyReservation);
        modifyReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity() , " Modify Reservation Clicked" , Toast.LENGTH_SHORT).show();
                //Here we will gp to new Modify Reservation activity.
                startActivity(new Intent(v.getContext() , ExtraOrdinaryActivity.class));
            }
        });

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
