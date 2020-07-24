package com.example.appliccation01;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {

    private static String TAG ="FavoriteFragment";

    //declaration.
    ArrayList<String> countries;
    ArrayAdapter adapter;
    DatabaseHelper dbHelper;
    ListView countrylistView;
    View view;
    String txt;
    int empty;//checking empty of favorite list.

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        view = inflater.inflate(R.layout.fragment_favorite, container, false);

        countrylistView = (ListView) view.findViewById(R.id.Fav_countries);
        countries = new ArrayList<>();

        //view favorite countries to list.
        viewData();

        //delete data from favorite table.
        if (empty ==0){
            //context menu option.
            registerForContextMenu(countrylistView);
        }

        //go to city activity.
        countrylistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt = countrylistView.getItemAtPosition(position).toString();
                goToCity(txt);
            }
        });

        return view;
    }

    // Refresh current fragment
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh your fragment here
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            Log.i("IsRefresh", "Yes");
        }
    }

    //get list of countries.
    private void viewData() {
        Cursor cursor = dbHelper.viewDataFavorite();

        if(cursor.getCount() == 0){
            empty = 1;
            Toast.makeText(getActivity(), " Empty favorite list", Toast.LENGTH_SHORT).show();
        }
        else {
            empty = 0;
            //get data one by one.
            while (cursor.moveToNext()){
                countries.add(cursor.getString(1));
            }
            //add data to list.
            adapter = new ArrayAdapter<String>(
                    getContext(),
                    android.R.layout.simple_list_item_1,
                    countries
            );
            countrylistView.setAdapter(adapter);

        }

    }



    //context Menu. long pressing.
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getActivity().getMenuInflater().inflate(R.menu.popup_menu,menu);
    }

    //deleting part.
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        //get click location.
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int pos = info.position;
        txt = countrylistView.getItemAtPosition(pos).toString();

        //deleting.
        switch (item.getItemId()){
            case R.id.remove:
                if (dbHelper.deleteFavoriteCountry(txt)) {
                    Toast.makeText(getActivity(), txt + " deleted from favorite list", Toast.LENGTH_SHORT).show();
                    txt = null;
                }else {
                    Toast.makeText(getActivity(), txt + " is not in favorite list", Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.note:
                Intent intentCity = new Intent(getActivity(),NotesPage.class);
                intentCity.putExtra("EXTRA_MESSAGE",txt);
                startActivity(intentCity);
                return  true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    //go to city.
    public void goToCity(String txt){
        Intent intentCity = new Intent(getActivity(),CityActivity.class);
        intentCity.putExtra("EXTRA_MESSAGE",txt);
        startActivity(intentCity);
    }
}