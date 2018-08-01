package com.digicraft.wateradventures.Fragments;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.digicraft.wateradventures.Adapters.SearchAdapter;
import com.digicraft.wateradventures.Adapters.SearchSpinnerAdapter;
import com.digicraft.wateradventures.R;
import com.digicraft.wateradventures.Sales.SportDetailActivity;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements DatePickerDialog.OnDateSetListener {


    public SearchFragment() {
        // Required empty public constructor
    }


    SalesOpener opener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        opener = (SalesOpener) context;
    }

    TextView location_picker;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search, container, false);


        AutoCompleteTextView searchBox = root.findViewById(R.id.searchBox);

        searchBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationDialog(v);
            }
        });
        location_picker = root.findViewById(R.id.pick_a_location_tv);
        ImageButton searchBtn = root.findViewById(R.id.searchBtn);

        location_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationDialog(v);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            opener.goToResultScreen();
            }
        });

        ArrayList<String> names = new ArrayList<>();
        names.add("Scuba diving");
        names.add("Scuba Snorkelling");
        names.add("Scuba Windsurfing");
        names.add("Scuba Parasailing");
        names.add("Scuba Surfing");
        names.add("Scuba Wakeboarding");


        final ArrayAdapter<String> a =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, names);
        searchBox.setAdapter(a);

        TextView datePicker = root.findViewById(R.id.datePicker);
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

        ListView listView = root.findViewById(R.id.search_lv);

        SearchAdapter adapter = new SearchAdapter(getActivity());
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getActivity() , SportDetailActivity.class));
                showToast("Item Clicked.");
                return true;
            }
        });

        return root;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    public void showToast(String msg)
    {
        Toast.makeText(getActivity() , msg ,Toast.LENGTH_SHORT).show();
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            //dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
//            btnDate.setText(ConverterDate.ConvertDate(year, month + 1, day));
        }
    }

    public void locationDialog(View view)
    {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the
        // dialog layout


        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.searchable_spinner_dialog, null, false);
        builder.setView(formElementsView);
        builder.setTitle("Pick a Location");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
            }
        });
        // Add action buttons

        ListView listView = formElementsView.findViewById(R.id.searchable_spinner_listview);


        ArrayList<String> areas = new ArrayList<>();
        areas.add("Grand Velas Rivera Maya");
        areas.add("Fiesta Americana");
        areas.add("Grand Velas Rivera Maya");
        SearchSpinnerAdapter adapter = new SearchSpinnerAdapter(getActivity() ,areas );
        listView.setAdapter(adapter);


        builder.create();
        builder.show();

    }

}
