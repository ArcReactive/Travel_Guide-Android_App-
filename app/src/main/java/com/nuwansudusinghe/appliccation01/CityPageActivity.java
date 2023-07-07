package com.nuwansudusinghe.appliccation01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appliccation01.R;

//@Author NuwanSudusinghe.
public class CityPageActivity extends AppCompatActivity {

    String cityName,
//            population,
            area,
            Link;
    TextView cityTxt,
//            populationTxt,
            areaTxt;
    DatabaseHelper dbHelper;
    Button wikipediaBtn,airportBtn, weatherBtn, hotelBtn, attractionBtn, restaurantBtn, transitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_page);

        //declaration.
        cityTxt = (TextView) findViewById(R.id.CityNameTextView);
//        populationTxt = (TextView) findViewById(R.id.populationTxtView);
//        areaTxt = (TextView)findViewById(R.id.areaTxtView);
        wikipediaBtn = findViewById(R.id.WikiBtn);
        airportBtn = findViewById(R.id.AirportBtn);
        weatherBtn = findViewById(R.id.WeatherBtn);
        hotelBtn = findViewById(R.id.HotelBtn);
        attractionBtn = findViewById(R.id.AttractionBtn);
        restaurantBtn = findViewById(R.id.RestaurentBtn);
        transitBtn = findViewById(R.id.TransitBtn);

        //set city name.
        Intent intent = getIntent();
        cityName = intent.getStringExtra("EXTRA_MESSAGE");
        cityTxt.setText(cityName);

        //handling null exception.
        initDatabaseHelper();
        //view data.
//        getDataView();
//        populationTxt.setText(population);
//        areaTxt.setText(area);

        //go to wikipedia.
        wikipediaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Link = "https://en.wikipedia.org/wiki/"+cityName;
                Intent wikipedia = new Intent(Intent.ACTION_VIEW);
                wikipedia.setData(Uri.parse(Link));
                startActivity(wikipedia);
//                Uri web = Uri.parse(Link);
//                Intent goToWiki = new Intent(Intent.ACTION_VIEW, web);
//                if(goToWiki.resolveActivity(getPackageManager())!=null){
//                    startActivity(goToWiki);
//                }

            }
        });

        //go to airport.
        airportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Link = "https://www.google.com/maps/search/"+cityName+"nearest+airport/@";
                Intent airport = new Intent(Intent.ACTION_VIEW);
                airport.setData(Uri.parse(Link));
                startActivity(airport);
            }
        });

        //go to weather.
        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Details-->", "INSIDE WEATHER BUTTON");
                Intent weIntent = new Intent(CityPageActivity.this,WeatherPage.class);
                weIntent.putExtra("EXTRA_MESSAGE",cityName);
                startActivity(weIntent);
            }
        });

        //go to hotels.
        hotelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Details-->", "INSIDE HOTEL BUTTON");
                Link = "https://www.google.com/maps/search/hotels+near+"+cityName;
                Intent hotels = new Intent(Intent.ACTION_VIEW);
                hotels.setData(Uri.parse(Link));
                startActivity(hotels);
            }
        });

        //go to attraction.
        attractionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Link = "https://www.google.com/maps/search/attraction+near+"+cityName;
                Intent attraction = new Intent(Intent.ACTION_VIEW);
                attraction.setData(Uri.parse(Link));
                startActivity(attraction);
            }
        });

        //go to restaurants.
        restaurantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Link = "https://www.google.com/maps/search/restaurants+near+"+cityName;
                Intent restaurant = new Intent(Intent.ACTION_VIEW);
                restaurant.setData(Uri.parse(Link));
                startActivity(restaurant);
            }
        });

        //go to transit.
        transitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Link = "https://www.google.com/maps/search/Transit+stations+near+"+cityName;
                Intent transit = new Intent(Intent.ACTION_VIEW);
                transit.setData(Uri.parse(Link));
                startActivity(transit);
            }
        });

    }

    //correct null point Error.
    private void initDatabaseHelper(){
        if(dbHelper == null){
            dbHelper = new DatabaseHelper(this);
        }
    }

//    private void getDataView() {
//
//        Cursor cursor = dbHelper.viewDataCityPage(cityName);
//        if(cursor.moveToFirst()){
//            population = cursor.getString(3);
//            area = cursor.getString(4);
//        }
//        cursor.close();
//    }


}