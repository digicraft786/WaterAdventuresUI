package com.digicraft.wateradventures.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.digicraft.wateradventures.Adapters.SearchAdapter;
import com.digicraft.wateradventures.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends Fragment {


    public SearchResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search_result, container, false);
        ListView listView = root.findViewById(R.id.search_result_lv);
        SearchAdapter adapter = new SearchAdapter(getActivity() , true);
        listView.setAdapter(adapter);
        return root;
    }

}
