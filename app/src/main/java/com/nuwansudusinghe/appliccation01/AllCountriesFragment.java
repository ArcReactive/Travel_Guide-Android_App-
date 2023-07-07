package com.nuwansudusinghe.appliccation01;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.appliccation01.R;

/**
 * //@Author NuwanSudusinghe.
 * A simple {@link Fragment} subclass.
 * Use the {@link AllCountriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllCountriesFragment extends Fragment {

    //declerations.
    String[] countries;
//    String[] details;
    ListView countryList;
    DatabaseHelper dbHelper;
    boolean r;
    ArrayAdapter<String> adapter;
    Switch aSwitch;
    String txt;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllCountriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllCountriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllCountriesFragment newInstance(String param1, String param2) {
        AllCountriesFragment fragment = new AllCountriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //handling null exception.
        initDatabaseHelper();
//        dbHelper.insetDataCity(requireContext());

    }

    //correct null point Error.
    private void initDatabaseHelper(){
        if(dbHelper == null){
            dbHelper = new DatabaseHelper(getActivity());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_countries, container, false);

        Resources res = getResources();
        countries = res.getStringArray(R.array.country);//get data from values.
        countryList = (ListView) view.findViewById(R.id.countries_list);
//        details = res.getStringArray(R.array.city);

        registerForContextMenu(countryList);

        //set adapter. add data to country list.
        ItemAdapter itemAdapter = new ItemAdapter(getActivity(), countries);
        countryList.setAdapter(itemAdapter);

        //go to city activity.
        countryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt = countryList.getItemAtPosition(position).toString();
                goToCity(txt);
            }
        });

        return view;
    }

    //go to city.
    public void goToCity(String txt){
        Intent intentCity = new Intent(getActivity(),CityActivity.class);
        intentCity.putExtra("EXTRA_MESSAGE",txt);
        startActivity(intentCity);
    }

    //context Menu. long pressing.
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getActivity().getMenuInflater().inflate(R.menu.popup_menu_coun,menu);
    }

    //inserting part.
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        //get click location.
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int pos = info.position;
        txt = countryList.getItemAtPosition(pos).toString();

        //inserting.
        if(item.getItemId() == R.id.add) {
//        switch (item.getItemId()){
//            case R.id.add: <-- error: constant expression required
            //check the country already existence.
            if (dbHelper.checkCountry(txt)) {
                //data addition.
                if (dbHelper.insertDataFavorite(txt)) {
                    Toast.makeText(getActivity(), txt + " added to favorite list", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), txt + " is not added to favorite list", Toast.LENGTH_SHORT).show();
                }
                txt = null;
            } else {
                Toast.makeText(getActivity(), txt + " is already added to favorite list", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (item.getItemId() == R.id.allnote) {
//            case R.id.allnote: <-- error: constant expression required
            Intent intentCity = new Intent(getActivity(), NotesPage.class);
            intentCity.putExtra("EXTRA_MESSAGE", txt);
            startActivity(intentCity);
            return true;
        }else {
//            default:
                return super.onContextItemSelected(item);
        }

    }
}