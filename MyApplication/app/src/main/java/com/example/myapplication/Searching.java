package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.myapplication.databinding.ActivitySearchingBinding;

public class Searching extends AppCompatActivity {

    private ActivitySearchingBinding mSearchingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchingBinding = DataBindingUtil.setContentView(this, R.layout.activity_searching);
        setContentView(mSearchingBinding.getRoot());

        mSearchingBinding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Searching", "Button đã được ấn");
            }
        });
    }
}