package com.sawaTech.material.feedbackSeekbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ammadtarar on 09/02/2017.
 */

public class DemoActivity extends AppCompatActivity {

    private FeedbackSeekbar seek;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seek_activity);
        seek = (FeedbackSeekbar) findViewById(R.id.seek);
    }
}
