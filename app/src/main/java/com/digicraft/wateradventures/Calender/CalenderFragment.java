package com.digicraft.wateradventures.Calender;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.digicraft.wateradventures.Adapters.CalenderAdapter;
import com.digicraft.wateradventures.Adapters.CalenderDetail;
import com.digicraft.wateradventures.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalenderFragment extends Fragment {


    public CalenderFragment() {
        // Required empty public constructor
    }

    GridView gridView;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_calender, container, false);

        gridView = root.findViewById(R.id.calender_gridView);
        CalenderAdapter adapter = new CalenderAdapter(getActivity());
        gridView.setAdapter(adapter);

        listView = root.findViewById(R.id.calender_detail_lv);
        CalenderDetail detail_adapter = new CalenderDetail(getActivity());
        listView.setAdapter(detail_adapter);

        return root;
    }

}
