package com.nuwansudusinghe.appliccation01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.appliccation01.R;
import com.nuwansudusinghe.appliccation01.Retrofit.ApiClient;
import com.nuwansudusinghe.appliccation01.Retrofit.ApiInterface;
import com.nuwansudusinghe.appliccation01.Retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//@Author NuwanSudusinghe.
public class WeatherPage extends AppCompatActivity {

    String cityName;
    TextView cityText,tempText, descText, humidityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_page);

        //declaration.
        tempText = findViewById(R.id.TempTextView);
        cityText = findViewById(R.id.WcityTextView);
        descText = findViewById(R.id.WdescriptionTextView);
        humidityText = findViewById(R.id.HumTextView);

        //set city name.
        Intent intent = getIntent();
        cityName = intent.getStringExtra("EXTRA_MESSAGE");
        cityText.setText(cityName);

        //call weather.
        getWeatherData(cityName);

    }
//
    private void getWeatherData(String name){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiInterface.getWeatherData(name);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                tempText.setText("Temp"+" "+response.body().getMain().getTemp()+" C");
                descText.setText("Feels Like"+" "+response.body().getMain().getFeels_like());
                humidityText.setText("Humidity"+" "+response.body().getMain().getHumidity());


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }


}