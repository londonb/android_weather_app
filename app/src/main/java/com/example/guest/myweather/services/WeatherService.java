package com.example.guest.myweather.services;


import android.util.Log;

import com.example.guest.myweather.Constants;
import com.example.guest.myweather.models.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 4/25/16.
 */
public class WeatherService {
    public static final String TAG = WeatherService.class.getSimpleName();

    public static void findWeather(String location, Callback callback) {
        String WEATHER_API_KEY = Constants.WEATHER_API_KEY;

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Log.d(TAG, "THIS IS THE CONTENT:" + HttpUrl.parse(Constants.WEATHER_BASE_URL));

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WEATHER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.WEATHER_LOCATION_QUERY_PARAMETER, location);
        urlBuilder.addQueryParameter("appid", WEATHER_API_KEY);
        String url = urlBuilder.build().toString();
        Log.d("URL", url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Weather> processResults(Response response) {
        ArrayList<Weather> weathers = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()) {

                JSONObject openWeatherJSON = new JSONObject(jsonData);
                JSONArray forecastJSON = openWeatherJSON.getJSONArray("list");

                for(int i = 0; i < forecastJSON.length(); i++) {
                    JSONObject weatherJSON = forecastJSON.getJSONObject(i);
                    String minTemp = weatherJSON.getJSONObject("temp").getString("min");
                    String maxTemp = weatherJSON.getJSONObject("temp").getString("max");
                    String description = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("description");
                    long dateJSON = (weatherJSON.getLong("dt") * 1000);

                    String date;
                    SimpleDateFormat df = new SimpleDateFormat("EEEE");
                    date = df.format(dateJSON);

                    String weatherIcon = weatherJSON.getJSONArray("weather").getJSONObject(0).getString("icon");


                    Weather weather = new Weather(minTemp, maxTemp, description, date, weatherIcon);
                    weathers.add(weather);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weathers;
    }
}
