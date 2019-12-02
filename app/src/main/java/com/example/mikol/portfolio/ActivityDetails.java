package com.example.mikol.portfolio;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ActivityDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        TextView newtext = (TextView) findViewById(R.id.textView2);
        newtext.setText(intent.getStringExtra("name"));


        Button button1 = findViewById(R.id.button);
        button1.setOnClickListener((View.OnClickListener) this);
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View view)
    {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.button:
                fragment = new Fragment1();
                replaceFragment(fragment);
                break;

            case R.id.button2:
                fragment = new Fragment2();
                replaceFragment(fragment);

                break;

        }
    }
    public void replaceFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame1, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}


