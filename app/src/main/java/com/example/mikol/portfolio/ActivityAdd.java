package com.example.mikol.portfolio;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityAdd extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add);

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            EditText editText = findViewById(R.id.editText);
            editText.setText(pref.getString("input", ""));

            EditText editText1 = findViewById(R.id.editText1);
            editText1.setText(pref.getString("input1", ""));
        }

    public void save(View view){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        EditText editText = findViewById(R.id.editText);
        EditText editText1 = findViewById(R.id.editText1);
        editor.putString("input", editText.getText().toString());
        editor.putString("input1", editText1.getText().toString());
        editor.apply();
    }
    }