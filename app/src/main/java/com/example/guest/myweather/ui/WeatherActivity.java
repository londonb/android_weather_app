package com.example.guest.myweather.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.guest.myweather.R;
import com.example.guest.myweather.models.Weather;
import com.example.guest.myweather.services.WeatherService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {
    public static final String TAG = WeatherActivity.class.getSimpleName();
    public ArrayList<Weather> mWeathers = new ArrayList<>();
    @Bind(R.id.listView) ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        getWeather(location);
    }

    private void getWeather(String location) {
        final WeatherService weatherService = new WeatherService();

        WeatherService.findWeather(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mWeathers = weatherService.processResults(response);

                WeatherActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String[] weekForecast = new String[mWeathers.size()];
                        for (int i = 0; i < weekForecast.length; i++) {
                            weekForecast[i] = mWeathers.get(i).getDate();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(WeatherActivity.this, android.R.layout.simple_expandable_list_item_1, weekForecast);
                        mListView.setAdapter(adapter);

                        for (Weather weather : mWeathers) {
                            Log.d(TAG, "Min " + weather.getMinTemp());
                            Log.d(TAG, "Max " + weather.getMaxTemp());
                            Log.d(TAG, "Description: " + weather.getDescription());
                            Log.d(TAG, "Date " + weather.getDate());
                        }
                    }
                });
            }
        });
    }
}
