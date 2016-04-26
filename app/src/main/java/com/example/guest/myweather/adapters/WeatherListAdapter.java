package com.example.guest.myweather.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.myweather.R;
import com.example.guest.myweather.models.Weather;
import com.example.guest.myweather.ui.WeatherDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 4/25/16.
 */
public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {
    private ArrayList<Weather> mWeathers = new ArrayList<>();
    private Context mContext;

    public WeatherListAdapter(Context context, ArrayList<Weather> weathers) {
        mContext = context;
        mWeathers = weathers;
    }

    @Override
    public WeatherListAdapter.WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        WeatherViewHolder viewHolder = new WeatherViewHolder (view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (WeatherListAdapter.WeatherViewHolder holder, int position) {
        holder.bindWeather(mWeathers.get(position));
    }

    @Override
    public int getItemCount() {
        return mWeathers.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.weatherImageView) ImageView mWeatherImageView;
        @Bind(R.id.dateTextView) TextView mDateView;
        @Bind(R.id.descriptionTextView) TextView mDescriptionView;
        @Bind(R.id.maxTextView) TextView mMaxTextView;
        @Bind(R.id.minTextView) TextView mMinTextView;
        private Context mContext;

        public WeatherViewHolder(View itemView) {
            super (itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                    public void onClick(View v) {
                    int itemPosition = getLayoutPosition();
                    Intent intent = new Intent(mContext, WeatherDetailActivity.class);
                    intent.putExtra("position", itemPosition + "");
                    intent.putExtra("weathers", Parcels.wrap(mWeathers));
                    mContext.startActivity(intent);
                }
            });
        }

        public void bindWeather(Weather weather) {
            mDateView.setText(weather.getDate());
            mDescriptionView.setText(weather.getDescription());
            mMaxTextView.setText(weather.getMaxTemp());
            mMinTextView.setText(weather.getMinTemp());
            Picasso.with(mContext).load(weather.getWeatherIcon()).into(mWeatherImageView);
        }
     }

}
