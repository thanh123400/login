package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.MenuItem;
import android.widget.Button;

import com.example.myapplication.databinding.ActivitySettingsBinding;

import java.util.Arrays;
import java.util.List;

public class Settings extends AppCompatActivity {

    private ActivitySettingsBinding mSettingsBinding;

    List<String> languageList = Arrays.asList("Select Language", "English", "Việt Nam");

    String defaultLanguage;

    String selectedLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //databinding
        mSettingsBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        setContentView(mSettingsBinding.getRoot());

        //ngôn ngữ
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languageList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSettingsBinding.spinnerLanguage.setAdapter(adapter);
        mSettingsBinding.spinnerLanguage.setSelection(0);

        SharedPreferences sharedPreferences = getSharedPreferences("Language", Context.MODE_PRIVATE);
        defaultLanguage = sharedPreferences.getString("Language", "");

        int defaultLanguageIndex = languageList.indexOf(defaultLanguage);
        mSettingsBinding.spinnerLanguage.setSelection(defaultLanguageIndex);

        mSettingsBinding.spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLanguage = languageList.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mSettingsBinding.Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!defaultLanguage.equals(selectedLanguage)) {
                    putLanguage(selectedLanguage);
                    Log.d("TAG", "Selected language: " + selectedLanguage);
                    restartApp();
                }
            }
        });
    }

    private void putLanguage(String language) {
        SharedPreferences sharedPreferences = getSharedPreferences("Language", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Language", language);
        editor.apply();
    }

    private void restartApp() {
        Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("ChangeLanguage", "L1");
            startActivity(intent);
            finish();
        }

    }
}