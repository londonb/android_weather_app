package com.example.guest.myweather.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.myweather.R;
import com.example.guest.myweather.models.Weather;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherDetailFragment extends Fragment {
    @Bind(R.id.weekDayTextView) TextView mWeekDayTextView;
    @Bind(R.id.descriptionTextView2) TextView mDescriptionTextView2;
    @Bind(R.id.maxTextView2) TextView mMaxTextView2;
    @Bind(R.id.minTextView2) TextView mMinTextView2;
    @Bind(R.id.weatherImageView2) ImageView mWeatherImageView2;

    private Weather mWeather;

    public static WeatherDetailFragment newInstance(Weather weather) {
        WeatherDetailFragment weatherDetailFragment = new WeatherDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("weather", Parcels.wrap(weather));
        weatherDetailFragment.setArguments(args);
        return weatherDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeather = Parcels.unwrap(getArguments().getParcelable("weather"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mWeather.getWeatherIcon()).into(mWeatherImageView2);
        mWeekDayTextView.setText(mWeather.getDate());
        mDescriptionTextView2.setText(mWeather.getDescription());
        mMaxTextView2.setText(mWeather.getMaxTemp());
        mMinTextView2.setText(mWeather.getMinTemp());
        return view;
    }

}
