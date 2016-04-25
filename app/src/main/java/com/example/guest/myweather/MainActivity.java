package com.example.guest.myweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.editText) EditText mEditText;
    @Bind(R.id.button) Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButton) {
            String location = mEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            intent.putExtra("location", location);
            Log.v(TAG, location);
            startActivity(intent);
        }
    }
}
